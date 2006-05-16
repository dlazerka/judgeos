package org.judgeos.taglib;

import org.judgeos.model.Account;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: uzver
 * Date: 16.05.2006
 * Time: 11:06:22
 */
public class AccountTag extends SimpleTagSupport {
	private String parameter;

	public void doTag() throws JspException, IOException {
		Account a = (Account) getJspContext().getAttribute("account", PageContext.SESSION_SCOPE);
		if (a == null) {
			throw new JspException("Session scope does not have property \"account\"");
		}
		getJspContext().getOut().print(a.getParameter(parameter));
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getParameter() {
		return parameter;
	}
}
