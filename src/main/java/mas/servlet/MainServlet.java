package mas.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.*;


public class MainServlet  extends HttpServlet{
    
            @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                                                            IOException
        {

            InputStream stream=MainServlet.class.getClassLoader().getResourceAsStream("webapp/index.html");
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
