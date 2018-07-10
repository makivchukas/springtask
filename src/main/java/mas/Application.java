package mas;

import mas.config.ApplicationConfiguration;

import org.eclipse.jetty.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args)throws Exception{
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfiguration.class);
        ctx.refresh();
        System.out.println("Start");        
        Server server =ctx.getBean(Server.class);
        server.start();
        server.join();
        ctx.close();
    }
}
