package com.p2p.conf;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.p2p.entity.User;
import com.p2p.utils.CookieUtil;
import com.p2p.utils.RedisService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@Component
public class LoginFilter extends ZuulFilter {

    @Autowired
    private WhiteConfig whiteConfig;

    @Autowired
    private RedisService redisService;

    //过滤器
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    //是否执行过滤
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String uri=request.getRequestURI();
        List<String> whiteList = whiteConfig.getWhiteList();

        if (whiteList==null||whiteList.size()==0){
            return true;
        }

        for (String w : whiteList) {
            if (uri.endsWith(w)){
                return false;
            }
        }
        return true;
    }

    //执行过滤的逻辑
    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletResponse response = context.getResponse();
        HttpServletRequest request = context.getRequest();
        String uri = request.getRequestURI();

        boolean isok = true;
        String tk = CookieUtil.getCookieValue(request,CookieUtil.COOKIE_NAME_TOKEN);
        if (tk==null||"".equals(tk)){
            /*try {
                response.sendRedirect("");
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            isok = false;
        }
        User user = redisService.get(tk,User.class);
        if (user==null){

            isok=false;
        }
        if (!isok){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
        }
        return null;
    }
}
