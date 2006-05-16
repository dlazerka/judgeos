<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html locale="true">
	<head>
		<title>
			<bean:message key=".title" />
		</title>
	</head>
	<body>

		<%@include file="/header.jsp"%>

		<html:link page="/profile/">
			<bean:message key=".profile" />
		</html:link>

	</body>
</html:html>
