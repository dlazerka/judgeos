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
		<bean:message key=".title" />:
		<bean:message key="profile.signup.title"/>
	</title>

	</head>
	<body>
		<%@include file="/header.jsp"%>
		<h3>
			<bean:message key="profile.login" />
		</h3>
		<html:form action="/profile/login">
			<bean:message key="profile.username" />
			<html:text property="username" errorKey="profile.login.username"/>
			<html:errors property="username" name="org.judgeos.LogInForm.ERROR_KEY"/>
			<br />

			<bean:message key="profile.password" />
			<html:password property="password"/>
			<html:errors property="password" name="org.judgeos.LogInForm.ERROR_KEY"/>
			<br />

			<html:submit>
				<bean:message key="profile.login.submit" />
			</html:submit>
		</html:form>
	</body>
</html:html>