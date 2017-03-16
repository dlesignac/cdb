package fr.ebiz.cdb.servlet;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AutowiredServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        AutowireCapableBeanFactory autowireCapableBeanFactory = WebApplicationContextUtils
                .getWebApplicationContext(config.getServletContext())
                .getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
    }

}
