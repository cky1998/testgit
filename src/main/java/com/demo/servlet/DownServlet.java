package com.demo.servlet; /**
 * @Program: Javademo
 * @ClassName: ${NAME}
 * @Author: *****
 * @Copyright ****个人所有
 * @Date: 2021-11-03 13:51
 * @Description: ${创建类时填写描述信息}
 * @Version: V1.0
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "DownServlet", value = "/DownServlet")
public class DownServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        request.setCharacterEncoding("utf-8");
        //获取用户要下载的文件名
        String filename = request.getParameter("filename");
        //根据文件名找到文件的真实资源(文件路径)
        ServletContext servletContext = request.getServletContext();
        String fname = servletContext.getRealPath("/downlord/");
        //凭借文件的全路径
        String downPath = fname + filename;
        //通过io流读取文件数据
        FileInputStream in = new FileInputStream(downPath);
        //中文文件名需要进行URL编码
        URLEncoder.encode(filename,"utf-8");
        //设置响应头，告诉浏览器处理数据
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        //获取输出流
        ServletOutputStream out = response.getOutputStream();
        byte[] buff = new byte[1024 * 1024];
        int readlen = 0;
        while ((readlen = in.read(buff)) != -1) {
            //读取一次就将缓冲数组写到输出流
            //写到浏览器
            out.write(buff,0,readlen);
        }
        out.close();
        in.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
