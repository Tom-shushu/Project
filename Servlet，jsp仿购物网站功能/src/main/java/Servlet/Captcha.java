package Servlet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Captcha {
	private static String Captcha = "";
	private char[] captcha = {'a','b','c','d','e','f','g','h','i','j',
			'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G',
			'H','I','G','K','L','M','M','O',
			'P','Q','R','S','T','U','V','W','X','Y','Z'};
	public void upImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setHeader("Pragma", "No-cache");  
		response.setHeader("Cache-Control", "No-cache");  
		response.setDateHeader("Expires", 0);

	
		 int width = 100;
		 int height = 40;
		 int imageType = BufferedImage.TYPE_INT_RGB;
		 
		 BufferedImage image = new BufferedImage(width, height, imageType);
		//3.填充背景
		 Graphics g = image.getGraphics();
		 Color bgk = new Color(255,255,255);
		 g.setColor(bgk);
		 g.fillRect(0, 0, width, height);
		 //4.画出字符
		 String str = "";
		 for (int i = 0; i < 4; i++) {	 
			 //随机得到四个字母
			 char a = captcha[(int)(Math.random()*captcha.length)];
			 str+=a;
		}
		 HttpSession session = request.getSession();
		 session.setAttribute("YANZHENGMA", str);
		 
		 int red = (int)(Math.random()*256);
		 int green = (int)(Math.random()*256);
		 int blue = (int)(Math.random()*256);
		 
		 Color strColor = new Color(red,green,blue);
		 Font s = new Font("",Font.ITALIC, 25);
		 g.setColor(strColor);
		 g.setFont(s);
		 g.drawString(str,width/2-35,height/2+8);
		 //4.添加干扰
		 for (int i = 0; i < 155; i++) {
			 int red1 = (int)(Math.random()*256);
			 int green1 = (int)(Math.random()*256);
			 int blue1 = (int)(Math.random()*256);
			 Color pointColor = new Color(red1, green1, blue1);
			 int x1 = (int)(Math.random()*99);
			 int y1 = (int)(Math.random()*49);
			 int x2 = (int)(Math.random()*99);;
			 int y2 = (int)(Math.random()*49);;
			 int round = (int)(Math.random()*50);
			 g.setColor(pointColor);
			 if(i>130) {
			 if(round%2==0) {
				 g.drawLine(x1, y1, x1, y1);
			 }else {
				 g.drawLine(x1, y1, x2, y2);
			 }
			 }else {
				 g.drawLine(x1, y1, x1, y1);
			 }
		}
		 //5.生成图片
		 try {
			ImageIO.write(image, "jpg",response.getOutputStream() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
