package org.judgeos.controller.gwt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.judgeos.gwt.client.TestService;
import org.judgeos.model.HibernateUtil;
import org.hibernate.Session;

public class TestServiceImpl extends RemoteServiceServlet implements TestService {
	public String getTest(int param) {
		String s;
		Session session = HibernateUtil.getCurrentSession();
//		s = (String) session.createQuery("select 'kuku'").uniqueResult();
		session.close();
		s = param + "";
		return "String " + s;
	}
}
