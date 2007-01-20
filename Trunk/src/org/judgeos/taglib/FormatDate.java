package org.judgeos.taglib;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.judgeos.model.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Date;

/**
 * see doStartTag()
 */
public class FormatDate extends SimpleTagSupport {
	/**
	 * The message resources for this package.
	 */
	protected static MessageResources messages =
		MessageResources.getMessageResources(org.apache.struts.taglib.html.Constants.Package + ".LocalStrings");

	private Date value;

	public void doTag() throws JspException {

		String dateString = Constants.defaultDateTimeFormat.format(value);

		JspWriter out = getJspContext().getOut();
		try {
			out.write(dateString);
		} catch (IOException e) {
			getJspContext().setAttribute(Globals.EXCEPTION_KEY, e, PageContext.REQUEST_SCOPE);
			throw new JspException(messages.getMessage("common.io", e.toString()));
		}
	}

	public void setValue(Date value) {
		this.value = value;
	}
}
