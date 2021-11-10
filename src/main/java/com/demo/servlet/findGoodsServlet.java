package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-08 15:51
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "findGoodsServlet", value = "/findGoodsServlet")
public class findGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 在这里实现功能
        // 创建一个map，用来保存要查询的数据
        Map<String, Object> param = new HashMap<>();
        String gname = request.getParameter("gname");
        if (!gname.isEmpty()){
            param.put("gname",gname);
        }
        String gprice = request.getParameter("gprice");
        if (!gprice.isEmpty()){
            double price = Double.parseDouble(gprice);
            param.put("gprice",price);
        }
        String gstock = request.getParameter("gstock");
        if (!gstock.isEmpty()){
            int stock = Integer.parseInt(gstock);
            param.put("gstock",stock);
        }
        // 从服务层获取数据
        List<Goods> goodsList = new GoodsServiceImpl().findGoodsWithParam(param);
        // 将数据保存到session域对象中
        request.getSession().setAttribute("goodsList",goodsList);
        // 获取数据后，再重定向到商品显示页面中
        response.sendRedirect(getServletContext().getContextPath()+"/jsp/dataList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
