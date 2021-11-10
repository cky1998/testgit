package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-08 20:52
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

@WebServlet(name = "UpdateGoodsServlet", value = "/UpdateGoodsServlet")
public class UpdateGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        String gid = request.getParameter("gid");
        String gname = request.getParameter("gname");
        String gprice = request.getParameter("gprice");
        String gstock = request.getParameter("gstock");
        String maintainDate = request.getParameter("maintainDate");
        // String classify = request.getParameter("classify");
        String gdescribe = request.getParameter("gdescribe");
        // 封装为一个对象
        Goods goods = new Goods(gname,
                Double.parseDouble(gprice),
                Integer.parseInt(gstock),
                maintainDate, null, gdescribe);

        goods.setGid(Integer.parseInt(gid));
        // 调用服务层数据库的操作方法
        boolean res = new GoodsServiceImpl().updateGoodsBy(goods);
        // 请求转发到查询serlvet
        // 不是直接响应回浏览器，需要刷新数据，而数据的刷新(加载)是showGoodsServlet完成
        // 需要请求转发到showGoodsServlet
        //request.setAttribute("gname","");
        //request.setAttribute("gprice","");
        //request.setAttribute("gstock","");
        request.getRequestDispatcher("showGoodsServlet").forward(request,response);
    }
}
