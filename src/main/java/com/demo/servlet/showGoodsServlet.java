package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-08 09:15
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */

import com.demo.pojo.Goods;
import com.demo.service.Impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "showGoodsServlet", value = "/showGoodsServlet")
public class showGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 在这里实现功能
        // 从服务层获取数据
        List<Goods> goodsList = new GoodsServiceImpl().findAll();
        // 将数据保存到session域对象中
        request.getSession().setAttribute("goodsList",goodsList);
        // 获取数据后，再重定向到商品显示页面中
        response.sendRedirect(getServletContext().getContextPath()+"/jsp/dataList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
