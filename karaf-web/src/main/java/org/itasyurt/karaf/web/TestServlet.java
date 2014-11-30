package org.itasyurt.karaf.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itasyurt.karaf.dao.common.CommonDao;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		CommonDao dao = context.getBean(CommonDao.class);
		TestBean tb = context.getBean(TestBean.class);
		resp.setStatus(HttpServletResponse.SC_OK);
	}
}
