package xyz.alexhaoge.zhuanglang.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest sRequest, 
        HttpServletResponse sResponse, Object obj) throws Exception{
        
        if (HttpMethod.OPTIONS.toString().equals(sRequest.getMethod())) {
            sResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }
            
        Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated() || subject.isRemembered();
    }

    // private boolean begingWith(String page, String[] requiredAuthPages) {
    //     boolean result = false;
    //     for (String requiredAuthPage : requiredAuthPages) {
    //         if(StringUtils.startsWithIgnoreCase(page, requiredAuthPage)) {
    //             result = true;
    //             break;
    //         }
    //     }
    //     return result;
    // }
}