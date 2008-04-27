package org.judgeos.controller.gwt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.judgeos.gwt.client.JudgeosService;
import org.judgeos.gwt.client.model.Results;

public class JudgeosServiceImpl extends RemoteServiceServlet implements JudgeosService {
	public Results getResults() {
		String s;
//		Session session = HibernateUtil.getCurrentSession();
//		s = (String) session.createQuery("select 'kuku'").uniqueResult();
//		session.close();
		return new Results();
	}
}
