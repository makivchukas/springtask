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

import java.util.*;
import java.io.*;

public class ImageServlet extends HttpServlet{
    private  Rectangle rec;
    public ImageServlet(){
        Properties prop = new Properties();
        String path="screenSize.properties";
        try(InputStream input = ImageServlet.class.getClassLoader().getResourceAsStream(path);){
		    prop.load(input);
		    rec = new Rectangle(Integer.valueOf(prop.getProperty("screen.startpoint.x")), Integer.valueOf(prop.getProperty("screen.startpoint.y")),
		    Integer.valueOf(prop.getProperty("screen.startpoint.width")),Integer.valueOf(prop.getProperty("screen.startpoint.height")));
            System.out.println(rec);
        } catch (IOException ex) {
    		ex.printStackTrace();
        }
    }
        
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws  ServletException, IOException
    {
        OutputStream out = response.getOutputStream();
		response.setContentType("image/png");
//        Rectangle rec = new Rectangle (
//        Toolkit.getDefaultToolkit().getScreenSize());
        try{
            Robot robot = new Robot();
            BufferedImage img = robot.createScreenCapture(rec);        
    		ImageIO.write(img, "png", out);    		
        }catch(AWTException e){
            e.printStackTrace();
        }
		out.close();                
    }
}
