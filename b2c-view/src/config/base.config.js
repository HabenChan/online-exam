/**
 * @authro yudong
 * @date 2018/12/7
 */
export const AXIOS_TIMEOUT = 3000
export const APP_CONTEXT = '/onlineExam'
export const LOGIN_URL = '/core/login'
export const LOGOUT_URL = '/core/logout'
export const GET_MENUS_LIST = '/core/menus/getMenusList'
export const CREATE_MENUS = '/core/menus/createMenus'
export const EDIT_MENUS = '/core/menus/editMenus'
export const DEL_MENUS = '/core/menus/delMenus'
export const GET_USERS_MENUS_LIST = '/core/menus/getUsersMenusList'
export const GET_AUTHORITIES_MENUS_LIST = '/core/menus/getAuthoritiesMenusList'
export const UPLOAD_FILE = '/core/files/uploadFile'
export const V_UPLOAD_FILE = '/core/files/vUploadFile'
export const V_DEL_FILE = '/core/files/vDelFile'
export const DOWNLOAD_FILE = '/core/files/downloadFile'

export const FIND_TOPICS_LIST = '/core/topics/findTopicsList'
export const CREATE_TOPICS = '/core/topics/createTopics'
export const EDIT_TOPICS = '/core/topics/editTopics'
export const DEL_TOPICS = '/core/topics/delTopics'

export const FIND_SECTIONS_LIST = '/core/sections/findSectionsList'
export const CREATE_SECTIONS = '/core/sections/createSections'
export const EDIT_SECTIONS = '/core/sections/editSections'
export const DEL_SECTIONS = '/core/sections/delSections'

export const FIND_KNOWLEDGES_LIST = '/core/knowledges/findKnowledgesList'
export const CREATE_KNOWLEDGES = '/core/knowledges/createKnowledges'
export const EDIT_KNOWLEDGES = '/core/knowledges/editKnowledges'
export const DEL_KNOWLEDGES = '/core/knowledges/delKnowledges'

export const FIND_KNOWLEDGE_POINTS_LIST = '/core/knowledgePoints/findKnowledgePointsList'
export const CREATE_KNOWLEDGE_POINTS = '/core/knowledgePoints/createKnowledgePoints'
export const EDIT_KNOWLEDGE_POINTS = '/core/knowledgePoints/editKnowledgePoints'
export const DEL_KNOWLEDGE_POINTS = '/core/knowledgePoints/delKnowledgePoints'

export const FIND_QUESTIONS_LIST = '/core/questions/findQuestionsList'
export const CREATE_QUESTIONS = '/core/questions/createQuestions'
export const EDIT_QUESTIONS = '/core/questions/editQuestions'
export const DEL_QUESTIONS = '/core/questions/delQuestions'
export const DOWNLOAD_QUESTIONS_TEMPLATE = '/core/questions/downloadQuestionsTemplate'
export const BATCH_IMPORT_QUESTIONS = '/core/questions/batchImportQuestions'
export const FIND_OPTIONS_LIST = '/core/questions/findOptionsList'

export const FIND_CERTS_EXAMPLE_PDF = '/core/certs/findCertsExamplePdf'
export const FIND_CERTS_LIST = '/core/certs/findCertsList'
export const CREATE_CERTS = '/core/certs/createCerts'
export const EDIT_CERTS = '/core/certs/editCerts'
export const DEL_CERTS = '/core/certs/delCerts'

export const FIND_EXAMS_LIST = '/core/exams/findExamsList'
export const CREATE_EXAMS = '/core/exams/createExams'
export const EDIT_EXAMS = '/core/exams/editExams'
export const DEL_EXAMS = '/core/exams/delExams'
export const ADD_OR_EDIT_ASSOCIATE_QUESTIONS = '/core/exams/addOrEditAssociateQuestions'
export const FIND_ASSOCIATE_QUESTIONS = '/core/exams/findAssociateQuestions'

export const GET_PROPERTY_SOURCE_NAMES = '/actuator/env'
export const GET_MAPPINGS = '/actuator/mappings'
export const GET_CONFIGPROPS = '/actuator/configprops'
export const GET_CONDITIONS = '/actuator/conditions'
export const GET_BEANS = '/actuator/beans'
export const GET_LOGGERS = '/actuator/loggers'
export const GET_HEAPDUMP = '/actuator/heapdump'
export const GET_JPS = '/actuator/jps'
export const GET_JINFO = '/actuator/jinfo'
export const GET_PRINTFLAGSFINAL = '/actuator/printFlagsFinal'
export const GET_METRICS = '/actuator/metrics'
export const GET_THREADS = '/actuator/threaddump'

export const FIND_USERS = '/admin/users/findUsers'
export const CHANGE_USERSE_NABLED = '/admin/users/changeUsersEnabled'
export const EDIT_USERS = '/admin/users/editUsers'
export const CREATE_USERS = '/admin/users/createUsers'
export const UPDATE_USERS_PASSWORD = '/admin/users/updateUsersPassword'
export const DELETE_USERS = '/admin/users/deleteUsers'
export const FIND_USERS_AUTHORITIES = '/admin/authorities/findUsersAuthorities'
export const FIND_AUTHORITIES = '/admin/authorities/findAuthorities'
export const AUTH_USERS = '/admin/authorities/authUsers'
export const UN_AUTH_USERS = '/admin/authorities/unAuthUsers'
export const CHANGE_AUTHORITIES_MENUS = '/admin/authorities/changeAuthoritiesMenus'
export const CREATE_AUTHORITIES = '/admin/authorities/createAuthorities'
export const EDIT_AUTHORITIES = '/admin/authorities/editAuthorities'
export const DEL_AUTHORITIES = '/admin/authorities/delAuthorities'

export const GENERATE_QRCODE_CONTENT = '/public/qrCode/generateQrCodeContent'
export const CHECK_SCAN_STATUS = '/public/qrCode/checkScanStatus'
