package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-08 11:44
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */

import com.demo.service.GoodsService;
import com.demo.service.Impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delServlet", value = "/delServlet")
public class delServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int gid = Integer.parseInt(request.getParameter("gid"));
        GoodsService goodsService = new GoodsServiceImpl();
        int rel = goodsService.delGood(gid);
        System.out.println(rel);
        request.getRequestDispatcher("showGoodsServlet").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
