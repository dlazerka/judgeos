<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<logic:notPresent role="roleName"><!-- todo implement roles -->
	<logic:redirect forward="root"/>
</logic:notPresent>

<html:html locale="true">
	<head>
		<title>
			<bean:message key=".title" />:
			<bean:message key="profile.title" />
		</title>
	</head>
	<body>
		<%@include file="/header.jsp"%>

	</body>
</html:html>
