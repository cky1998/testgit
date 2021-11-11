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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "AddServlet", value = "/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String gname = request.getParameter("gname");
        Part gimg = request.getPart("gimg");
        String imgName = gimg.getSubmittedFileName();
//        String gimg = "";
        double gprice =Double.parseDouble(request.getParameter("gprice"));
        long gstock = Long.parseLong(request.getParameter("gstock"));
        String maintainDate = request.getParameter("maintainDate");
        String classify = request.getParameter("classify");
        String gdescribe = request.getParameter("gdescribe");
        //获取项目真实路径
        String realPath =  getServletContext().getRealPath("");
        //获取日期
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int monthValue = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        //拼接为路径
        String datapath = File.separator + year + File.separator + monthValue + File.separator + dayOfMonth;
        //创建文件夹
        File uploadDir = new File(realPath,"/upload"+datapath);
        if (!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        //处理文件名，保证唯一
        UUID uuid = UUID.randomUUID();
        long time = System.currentTimeMillis();
        imgName = uuid + "" + time + imgName;
        System.out.println(imgName);
        //拼接文件路径
        String imgpath = uploadDir.getAbsolutePath()+ File.separator + imgName;
        String imgpath2 = "upload"+datapath+ File.separator + imgName;
        gimg.write(imgpath);
        GoodsService goodsService = new GoodsServiceImpl();
        int rel = goodsService.addGood(gname,imgpath2,gprice,gstock,maintainDate,classify,gdescribe);
        if (rel!=0){
            response.sendRedirect(getServletContext().getContextPath()+"/jsp/dataList.jsp");
        }else {
            String tip = "添加失败";
            response.getWriter().write(tip);
        }
    }
}
