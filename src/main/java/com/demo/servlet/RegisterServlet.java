package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-03 17:11
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */

import com.demo.pojo.User;
import com.demo.service.Impl.UserServiceImpl;
import com.demo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int uid = Integer.parseInt(request.getParameter("uid"));
        String uname = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        User user = new UserServiceImpl().findUser(uid);
        response.setContentType("text/html;charset=UTF-8");
        int num = 0;
        if (user==null){
            num = userService.addUser(uid,uname,pwd);
            if ((num!=0)){
                request.getRequestDispatcher("login.html").forward(request,response);
            }else {
                String tip = "注册失败！";
                response.getWriter().write(tip);
            }
        }else {
            String tip = "账号已存在！";
            response.getWriter().write(tip);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
