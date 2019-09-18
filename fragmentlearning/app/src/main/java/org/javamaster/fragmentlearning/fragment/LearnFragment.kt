package org.javamaster.fragmentlearning.fragment

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_learn.*
import org.javamaster.fragmentlearning.R
import org.javamaster.fragmentlearning.adapter.LearnAdapter
import org.javamaster.fragmentlearning.asyncTask.TopicsAsyncTask
import org.javamaster.fragmentlearning.common.App
import org.javamaster.fragmentlearning.consts.AppConsts
import org.javamaster.fragmentlearning.data.entity.Topics
import org.javamaster.fragmentlearning.enums.TopicsTypeEnum
import org.javamaster.fragmentlearning.ioc.DaggerAppComponent
import org.javamaster.fragmentlearning.listener.OperationListener
import org.javamaster.fragmentlearning.listener.SoftKeyBoardListener
import org.javamaster.fragmentlearning.service.LearnService
import org.litepal.LitePal
import javax.inject.Inject

/**
 * @author yudong
 * @date 2019/8/18
 */
class LearnFragment : Fragment() {
    @Inject
    lateinit var learnService: LearnService

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder().globalComponent(App.globalComponent).build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_learn, container, false)
        ButterKnife.bind(this, view)
        val search = view.findViewById<SearchView>(R.id.search)
        search.isIconified = true
        search.setIconifiedByDefault(false)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(activity, getString(R.string.app_completing), Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        SoftKeyBoardListener(activity!!, object : SoftKeyBoardListener.OnSoftKeyBoardChangeListener {
            override fun keyBoardShow(height: Int) {
                activity!!.findViewById<View>(R.id.include_tab).visibility = View.GONE
            }

            override fun keyBoardHide(height: Int) {
                val tab = activity!!.findViewById<View>(R.id.include_tab)
                tab.alpha = 0f
                tab.visibility = View.VISIBLE
                ObjectAnimator.ofFloat(tab, "alpha", 0f, 1f)
                    .apply {
                        duration = 300
                        start()
                    }
            }
        })
        return view
    }

    override fun onResume() {
        super.onResume()
        val listener = object : OperationListener<Pair<List<Topics>, Map<String, Int>>> {
            override fun success(t: Pair<List<Topics>, Map<String, Int>>) {
                swipe_refresh.isRefreshing = false
                LitePal.deleteAll(Topics::class.java)
                // 缓存到数据库
                LitePal.saveAll(t.first)
                initAdapter(t)
            }

            override fun fail(errorCode: Int, errorMsg: String) {
                swipe_refresh.isRefreshing = false
                if (errorCode != AppConsts.LOGIN_ERROR_CODE) {
                    Toast.makeText(activity, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
        val topicsList = LitePal.findAll(Topics::class.java)
        val map = LearnService.getTopicsProgressMap()
        if (topicsList.isNotEmpty()) {
            initAdapter(Pair(topicsList, map))
        } else {
            swipe_refresh.isRefreshing = true
            TopicsAsyncTask(learnService, listener).execute()
        }
        swipe_refresh.setOnRefreshListener {
            TopicsAsyncTask(learnService, listener).execute()
        }
    }

    @SuppressLint("InflateParams")
    private fun initAdapter(pair: Pair<List<Topics>, Map<String, Int>>) {
        val map = pair.first.groupBy { it.topicsType }
        val topicsPlaceholder: LinearLayout = view!!.findViewById(R.id.topics_placeholder)
        topicsPlaceholder.removeAllViews()
        map.forEach {
            val newView = LayoutInflater.from(activity).inflate(R.layout.fragment_learn_part, null, false)
            val topicsNameView: TextView = newView.findViewById(R.id.no_course_text)
            topicsNameView.text = getString(TopicsTypeEnum.getEnumByCode(it.key!!)!!.msg)
            val showMoreView: TextView = newView.findViewById(R.id.no_course_manage)
            showMoreView.text = getString(R.string.view_more)
            showMoreView.setOnClickListener {
                Toast.makeText(activity, R.string.app_completing, Toast.LENGTH_SHORT).show()
            }
            val topicsRecyclerView: RecyclerView = newView.findViewById(R.id.no_course_recycler_view)
            topicsRecyclerView.layoutManager = getLayoutManage()
            val learnAdapter = LearnAdapter(it.value, pair.second, true)
            topicsRecyclerView.adapter = learnAdapter
            topicsPlaceholder.addView(newView)
        }
    }

    private fun getLayoutManage(): LinearLayoutManager {
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        return linearLayoutManager
    }

    companion object {
        @JvmStatic
        fun newInstance() = LearnFragment()
    }
}
