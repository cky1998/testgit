package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-08 11:52
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

@WebServlet(name = "AddServlet", value = "/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String gname = request.getParameter("gname");
        String gimg = "";
        double gprice =Double.parseDouble(request.getParameter("gprice"));
        long gstock = Long.parseLong(request.getParameter("gstock"));
        String maintainDate = request.getParameter("maintainDate");
        String classify = request.getParameter("classify");
        String gdescribe = request.getParameter("gdescribe");
        GoodsService goodsService = new GoodsServiceImpl();
        int rel = goodsService.addGood(gname,gimg,gprice,gstock,maintainDate,classify,gdescribe);
        System.out.println(rel);
        if (rel!=0){
            response.sendRedirect(getServletContext().getContextPath()+"/jsp/dataList.jsp");
        }else {
            String tip = "添加失败";
            response.getWriter().write(tip);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
