package xyz.alexhaoge.zhuanglang.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class URLPathMatchingFilter extends PathMatchingFilter{
    
    private static final Logger LOG = LoggerFactory.getLogger(URLPathMatchingFilter.class);

    private static String[] urlAuthNotRequired = {"/api/library/", "/api/resources", "/api/login", "/api/logout", "/api/upload/", "/api/register", "/index.html"};

    @Override
    protected boolean onPreHandle(ServletRequest request,
        ServletResponse response,
        Object mappedValue) throws Exception
    {
        HttpServletRequest hRequest = (HttpServletRequest) request;
        HttpServletResponse hResponse = (HttpServletResponse) response;

        if (HttpMethod.OPTIONS.toString().equals((hRequest).getMethod())) {
            hResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        String requestAPI = getPathWithinApplication(request);
        LOG.info("API requested: {}", requestAPI);

        Subject subject = SecurityUtils.getSubject();

        for (String urlANR : urlAuthNotRequired) {
            if (requestAPI.startsWith(urlANR)) {
                LOG.info("This api does not need authentication!");
                return true;
            }
        }
        boolean flag = subject.isAuthenticated() || subject.isRemembered();
        LOG.info("This api need authentication, get shiro subject authenticated or remembered: {}, ", flag);
        return flag;
    }
}