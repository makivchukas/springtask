package mas;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Scanner;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.imageio.ImageIO;

public class Application {
    public static void main(String[] args)throws Exception{
        System.out.println("Start");
        Server server = new Server(8080);
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase(".");
        
        
       ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
       
       context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(HelloServlet.class, "/");
         context.addServlet(ImageServlet.class, "/image");

        server.start();
        server.join();
       try{ getInterfacesAddress();
       }catch(SocketException e) {
           e.printStackTrace();
       }

                System.out.println("Stop");

    } 
    
    public static void getInterfacesAddress()throws SocketException{
                Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
        for (; n.hasMoreElements();){
            NetworkInterface e = n.nextElement();
            System.out.println(e);
            
            Enumeration<InetAddress> a = e.getInetAddresses();
            for (; a.hasMoreElements();) {
                InetAddress addr = a.nextElement();
                String getHostAddress = addr.getHostAddress();
                System.out.println(getHostAddress);
//                if (checkIPv4(getHostAddress)) {
//                    combo.addItem(addr.getHostAddress() + " # " + e.getDisplayName().toString());
//                }
            }
        }
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
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            Scanner reader = new Scanner(new FileReader(new File("index.html")));
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
