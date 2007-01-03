package org.judgeos.taglib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.judgeos.model.Contest;
import org.judgeos.model.HibernateUtil;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;


/**
 * Fetchs last contests from DB.
 */
public class ListContests extends SimpleTagSupport {
	private String var;
	private Integer limit;
	private JspContext jspContext;
	private Log log = LogFactory.getFactory().getInstance(ListContests.class.getName());

	public void setJspContext(JspContext jspContext) {
		this.jspContext = jspContext;
	}

	private List fetchContests() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		session.createCriteria(Contest.class)
			.addOrder(Order.desc("start"))
			.setMaxResults(limit);
		List contests = session.createQuery(
			"from Contest as contest left join fetch contest.owner as owner "
		).list();
		session.close();

		return contests;
	}

	public void doTag() throws JspException, IOException {
		try {
			jspContext.setAttribute(var, this.fetchContests(), PageContext.PAGE_SCOPE);
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setLimit(Integer limit) {
		System.out.println(limit);
		this.limit = limit;
	}
}
