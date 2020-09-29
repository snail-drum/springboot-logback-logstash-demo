package com.snaildrum.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // doing something
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith("application/json")) {
            MyHttpServletRequest myHttpServletRequest = new MyHttpServletRequest(request);
            ServletInputStream inputStream = myHttpServletRequest.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null){
                builder.append(line);
            }
            String jsonStr = builder.toString();
            System.out.println(jsonStr);
            if (!"".equals(jsonStr)) {
                myHttpServletRequest.setAttribute("jsonStr", jsonStr);
            }
            chain.doFilter(myHttpServletRequest, resp);
        } else {
            chain.doFilter(request, resp);
        }
    }

    @Override
    public void destroy() {
        // doing something
    }
}
