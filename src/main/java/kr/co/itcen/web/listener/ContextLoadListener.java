package kr.co.itcen.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ContextLoadListener
 *
 */
public class ContextLoadListener implements ServletContextListener {

    
    public void contextDestroyed(ServletContextEvent servletContextEvent)  {
    	String contextConfigLocation = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
    	System.out.println(contextConfigLocation);
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("MySite2 Applications Start");
    }
	
}
