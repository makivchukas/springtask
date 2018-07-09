package mas.config;
import mas.servlet.ImageServlet;
import mas.servlet.MainServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

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
    
    
}
