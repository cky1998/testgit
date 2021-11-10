package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-04 10:27
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CookieServlet", value = "/Cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String t = formatter.format(date);
        System.out.println(t);
        Cookie cookies[] = request.getCookies();
        response.setContentType("text/html;charset=UTF-8");
        for (Cookie cookie : cookies) {
            if ("lasttime".equals(cookie.getName())){
                String time = cookie.getValue();
                Cookie cook = new Cookie("lasttime",URLEncoder.encode(t, "utf-8"));
                response.setHeader("lasttime", String.valueOf(cook));
                response.addCookie(cook);
                response.getWriter().write("欢迎回来，您上次访问的时间是"+ URLDecoder.decode(time, "utf-8"));
                break;
            }
            if (cookie.equals(cookies[cookies.length-1])&&!"lasttime".equals(cookie.getName())){
                Cookie cook = new Cookie("lasttime", URLEncoder.encode(t, "utf-8"));
                response.setHeader("lasttime", String.valueOf(cook));
                response.addCookie(cook);
                response.getWriter().write("您好，欢迎您首次访问!");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
