package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-04 16:10
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */

import com.demo.utils.CodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckcodeServlet", value = "/checkcode")
public class CheckcodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获得验证码
        String checkCode = CodeUtil.getCheckCode();
        // 将验证码保存到session中，让登录的servlet可以使用
        request.getSession().setAttribute("checkcode",checkCode);
        // 获得图片
        CodeUtil.outputImage(response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
