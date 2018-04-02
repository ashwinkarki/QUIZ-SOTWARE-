package com.home.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MappingFilter",urlPatterns = {"/dashboard"})
public class MappingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;

        HttpSession session=request.getSession();

        String loginURI=request.getContextPath()+"/";
        String mainPage=request.getContextPath()+"/dashboard";

        boolean loggedIn=(session!=null)&&(session.getAttribute("usession")!=null);
        boolean loginRequest=request.getRequestURI().equals(loginURI);
        boolean isMainPage=request.getRequestURI().equals(mainPage);

        boolean isStaticResource=request.getRequestURI().startsWith("/css/");
        boolean isJsResource=request.getRequestURI().startsWith("/js/");
        boolean isVendorResource=request.getRequestURI().startsWith("/vendor/");
        boolean isImagesResource=request.getRequestURI().startsWith("/images/");
        boolean isFontsResource=request.getRequestURI().startsWith("/fonts/");

if(loginRequest || loggedIn||isMainPage||isStaticResource||isJsResource
        ||isVendorResource||isImagesResource||isFontsResource){
        chain.doFilter(request,response);
        }
else{
    response.sendRedirect(loginURI);
}
    }

    @Override
    public void destroy() {

    }
}
