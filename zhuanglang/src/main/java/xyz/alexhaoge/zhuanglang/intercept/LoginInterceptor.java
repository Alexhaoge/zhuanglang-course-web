package xyz.alexhaoge.zhuanglang.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import xyz.alexhaoge.zhuanglang.pojo.Teacher;

public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest sRequest, 
        HttpServletResponse sResponse, Object obj) throws Exception{
        
        HttpSession session = sRequest.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
            "index",
        };
        String uri = sRequest.getRequestURI();
        String page = uri.replace(contextPath+"/", "");
        if(begingWith(page, requireAuthPages)){
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            if(teacher==null) {
                sResponse.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWithIgnoreCase(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}