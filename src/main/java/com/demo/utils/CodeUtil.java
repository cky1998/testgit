package com.demo.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CodeUtil {
    private static int width = 100;// 定义图片的width
    private static int height = 35;// 定义图片的height
    private static int codeCount = 4;// 定义图片上显示验证码的个数
    private static int fontHeight = 20;
    private static int xx = 18;//坐标X
    private static  int codeY = 25;//坐标Y
    private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R','T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '6', '7', '8', '9' };
    private static Map<String, Object> codeMap;
    /**
     * 生成一个map集合
     * code为生成的验证码
     * codePic为生成的验证码BufferedImage对象
     * @return
     */
    private static Map<String,Object> generateCodeAndPic() {
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取一个绘制图层对象
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);

        // 画边框。
        // gd.setColor(Color.lightGray);
        gd.drawRect(0, 0, width - 1, height - 1);

        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        // 随机颜色的初始值
        int red = 0, green = 0, blue = 0;
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        // 创建map。用来保存验证码和验证码图片
        Map<String,Object> map  =new HashMap<String,Object>();
        //存放验证码
        map.put("code", randomCode.toString());  // toString（）将字符串缓冲区对象转换为String类型
        //存放生成的验证码BufferedImage对象
        map.put("codePic", buffImg);
        return map;
    }

    /**
     * 获取验证码
     * @return
     */
    public static String getCheckCode(){
        codeMap = generateCodeAndPic();
        return  (String) codeMap.get("code");
    }

    /**
     * 写入图片到输出流中
     * @param out
     * @throws IOException
     */
    public static void outputImage(OutputStream out) throws IOException {
        ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", out);
    }
}
//
//@RequestMapping("/code")
//@ResponseBody
//public void doGet(HttpServletRequest req, HttpServletResponse resp){
//	// 调用工具类生成的验证码和验证码图片
//	Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
//
//	// 将四位数字的验证码保存到Session中。
//	HttpSession session = req.getSession();
//	session.setAttribute("code", codeMap.get("code").toString());
//
//	// 禁止图像缓存。
//	resp.setHeader("Pragma", "no-cache");
//	resp.setHeader("Cache-Control", "no-cache");
//	resp.setDateHeader("Expires", -1);
//
//	resp.setContentType("image/jpeg");
//
//	// 将图像输出到Servlet输出流中。
//	ServletOutputStream sos;
//	try {
//		sos = resp.getOutputStream();
//		ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
//		sos.close();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//
// }
