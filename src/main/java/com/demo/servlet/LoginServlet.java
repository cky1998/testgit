package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-03 16:46
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */

import com.demo.pojo.User;
import com.demo.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 先设置相应的字符编码
        request.setCharacterEncoding("utf-8");
        // 先获取用户输入的验证码数据
        String checkcode = request.getParameter("checkcode");
        // 比较两个验证码是否相同
        String imgCode = (String) request.getSession().getAttribute("checkcode");
        int uid = Integer.parseInt(request.getParameter("uid"));
        String pwd = request.getParameter("pwd");
        response.setContentType("text/html;charset=UTF-8");
        if (!checkcode.equalsIgnoreCase(imgCode)){
            request.getSession().setAttribute("errorInfo","验证码错误！");
            response.sendRedirect("login.jsp");
            return;
        }
//        if (!CaptchaUtil.ver(checkcode, request)) {
//            CaptchaUtil.clear(request);  // 清除session中的验证码
//            request.getSession().setAttribute("errorInfo","验证码错误！");
//            response.sendRedirect("login.jsp");
//            return;
//        }


        User user = new UserServiceImpl().login(uid,pwd);
        if (user!=null&&user.getPwd().equals(pwd)){
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("showGoodsServlet").forward(request,response);
        }else {
            String tip = "账号或密码错误，请重新登录！";
            response.getWriter().write(tip);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
