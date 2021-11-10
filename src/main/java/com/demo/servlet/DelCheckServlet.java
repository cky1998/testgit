package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-08 19:47
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

@WebServlet(name = "DelCheckServlet", value = "/DelCheckServlet")
public class DelCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String str = request.getParameter("array");
        System.out.println(str);
        String[] s = str.split(",");
        int rel = 0;
        GoodsService goodsService = new GoodsServiceImpl();
        for (int i = 0; i < s.length; i++){
            rel = goodsService.delGood(Integer.parseInt(s[i]));
        }
        request.getRequestDispatcher("showGoodsServlet").forward(request,response);
//        int[] num = new int[str.length()];
//        System.out.println(num.length);
//        int[] rel = new int[num.length];
//        int i = 0;
//        for (String s : num) {
//            GoodsService goodsService = new GoodsServiceImpl();
//            rel[i] = goodsService.delGood(Integer.parseInt(s));
//            i++;
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
