package mas;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Application {
    public static void main(String[] args)throws Exception{
        System.out.println("Start");        
        Server server = new Server(8080);

       ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
                context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(HelloServlet.class, "/");
         context.addServlet(ImageServlet.class, "/image");
        
        // Start things up!
        server.start();

        server.join();        
    } 
    
    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet
    {
        @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                                                            IOException
        {
            File indexHtml = new File("index.html");
            InputStream stream=Application.class.getClassLoader().getResourceAsStream("webapp/index.html");
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
//            Scanner reader = new Scanner(new FileReader(new File("index.html")));
            Scanner reader = new Scanner(stream);
            while(reader.hasNextLine()){
                response.getWriter().println(reader.nextLine());
            }
            reader.close();
            
        }
    }
        @SuppressWarnings("serial")
    public static class ImageServlet extends HttpServlet
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
}
