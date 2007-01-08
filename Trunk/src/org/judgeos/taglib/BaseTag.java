package org.judgeos.taglib;

import org.apache.struts.Globals;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * see doStartTag()
 */
public class BaseTag extends TagSupport {
	/**
	 * The message resources for this package.
	 */
	protected static MessageResources messages =
		MessageResources.getMessageResources(Constants.Package + ".LocalStrings");

	/**
	 * Produces something like "&lt;base href="http://mydomain/judgeos/" to refer to
	 * the root of current request context.
	 *
	 * @return EVAL_BODY_INCLUDE
	 * @throws JspException
	 */
	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

		String tag = "<base href=\"" +
			RequestUtils.createServerUriStringBuffer(
				request.getScheme(),
				request.getServerName(),
				request.getServerPort(),
				request.getContextPath()) +
			"/\">";

		JspWriter out = pageContext.getOut();
		try {
			out.write(tag);
		} catch (IOException e) {
			pageContext.setAttribute(Globals.EXCEPTION_KEY, e, PageContext.REQUEST_SCOPE);
			throw new JspException(messages.getMessage("common.io", e.toString()));
		}

		return EVAL_BODY_INCLUDE;
	}
}
