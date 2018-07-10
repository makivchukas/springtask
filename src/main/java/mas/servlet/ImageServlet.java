package mas.servlet;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet
    {
        @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws  ServletException,
                                                            IOException
        {
        OutputStream out = response.getOutputStream();
		response.setContentType("image/jpeg");
        Rectangle rec = new Rectangle (
        Toolkit.getDefaultToolkit().getScreenSize());
        try{
                Robot robot = new Robot();
                BufferedImage img = robot.createScreenCapture(rec);        
        		ImageIO.write(img, "jpg", out);    		
        }catch(AWTException e){
            e.printStackTrace();
        }
    
		out.close();                
        }
    }
