<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.judgeos.model.Account"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/header.css">

<table width="100%">
	<tr>
		<td align="left">
			<div class="header_logo">
				<bean:message key=".logo" />
			</div>
		</td>
		<td align="right">
			<%
				Account account = (Account)request.getSession().getAttribute("account");
				if (account != null) {
			%>
			You are logged in as <b><%= account.getUsername()%>.</b>
			<% } else { %>
			You are not logged in.
			<%
				}
			%>
		</td>
	</tr>
</table>


<hr>