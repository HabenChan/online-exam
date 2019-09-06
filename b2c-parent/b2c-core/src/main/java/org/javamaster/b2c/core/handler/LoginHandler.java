package org.javamaster.b2c.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javamaster.b2c.core.enums.BizExceptionEnum;
import org.javamaster.b2c.core.model.Result;
import org.javamaster.b2c.core.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author yudong
 * @date 2019/6/10
 */
@Component
public class LoginHandler {
    private static Logger logger = LoggerFactory.getLogger(LoginHandler.class);
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UsersService usersService;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException {
        logger.info("username:{} login in,login time:{}", request.getParameter("username")
                , format.format(LocalDateTime.now()));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Map<String, Object> map = new HashMap<>(10);
        map.put("success", true);
        map.put("data", usersService.findUsersByUsername(request.getParameter("username")));
        response.getWriter().print(objectMapper.writeValueAsString(map));
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException exception) throws IOException {
        logger.error("username:{} login failed,login time:{}", request.getParameter("username")
                , format.format(LocalDateTime.now()), exception);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().print(objectMapper.writeValueAsString(new Result<>(BizExceptionEnum.INVALID_USER.getErrorCode(),
                BizExceptionEnum.INVALID_USER.getErrorMsg())));
    }

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException {
        logger.info("username:{} logout,logout time:{}", ((UserDetails) authentication.getPrincipal()).getUsername()
                , format.format(LocalDateTime.now()));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().print(objectMapper.writeValueAsString(new Result<>("登出成功")));
    }

    public void onAuthenticationEntryPoint(HttpServletRequest request, HttpServletResponse response,
                                           AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(HttpStatus.UNAUTHORIZED.toString());
    }
}
