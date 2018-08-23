package mas.config;
import mas.servlet.ImageServlet;
import mas.servlet.MainServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import java.awt.Rectangle;
import java.util.*;
import java.io.*;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = { })
public class ApplicationConfiguration {
    
    @Bean
    public Server getServer(){
       Server server = new Server(8080);
       ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(MainServlet.class, "/");
         context.addServlet(ImageServlet.class, "/image");
         return server;
    }
    @Bean
    public Rectangle getScreenSizeRectangle(){
        Properties prop = new Properties();
        String path="screenSize.properties";
        Rectangle rec=null;
        
        try(InputStream input = ApplicationConfiguration.class.getClassLoader().getResourceAsStream(path);){
            if(input==null){
	            System.out.println("Sorry, unable to find " + path);
    		    return null;
		    }
		    prop.load(input);
		    rec = new Rectangle(Integer.valueOf(prop.getProperty("screen.startpoint.x")), Integer.valueOf(prop.getProperty("screen.startpoint.y")),
		    Integer.valueOf(prop.getProperty("screen.startpoint.width")),Integer.valueOf(prop.getProperty("screen.startpoint.height")));
        } catch (IOException ex) {
    		ex.printStackTrace();
        }
		    
		    


         return rec;
    }
   
    
}
