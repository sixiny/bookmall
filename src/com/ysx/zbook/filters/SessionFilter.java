package com.ysx.zbook.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ysx
 * @Date: 2022/08/03/19:58
 * @Description:  訪問白名單 利用Filter去做  設置訪問白名單
 */
//@WebFilter(urlPatterns = {"*.do", "*.html"},
//        initParams = {
//                @WebInitParam(name = "bai", value = "/pro24/page.do?operate=page&page=/user/login,/pro24/user.do?null")
//        }) //對所有這些請求做出回應
public class SessionFilter implements Filter {
    List<String> baiList = null ;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String bai = config.getInitParameter("bai"); //白名單
        String[] baiArr = bai.split(",");
        baiList = Arrays.asList(baiArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest ;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //http://localhost:8080/pro25/page.do?operate=page&page=user/login
        System.out.println("request.getRequestURI() = " + request.getRequestURI());  //查看访问地址
        System.out.println("request.getQueryString() = " + request.getQueryString());

        String uri = request.getRequestURI() ;
        String queryString = request.getQueryString() ;
        String str = uri + "?" + queryString ;
//        System.out.println(baiList);
//        System.out.println(str);

        if(baiList.contains(str)){
            filterChain.doFilter(request,response);
        }else{
            HttpSession session = request.getSession() ;
            Object currUserObj = session.getAttribute("currUser");

            if(currUserObj==null){
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
