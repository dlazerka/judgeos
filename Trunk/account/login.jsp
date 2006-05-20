<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ include file="/headers.jspf"%>

<html:html locale="true">
	<head>
		<title>
			<bean:message key=".title" />:
			<bean:message key="account.login.title"/>
		</title>
		<judgeos:base/>
	</head>
	<body>
		<%@include file="/top.jspf"%>
		<h3>
			<bean:message key="account.login" />
		</h3>
		<html:form action="/account/login">
			<html:hidden property="dataAttached" value="true"/>

			<bean:message key="account.codename" />
			<html:text property="codename" errorKey="account.login.codename"/>
			<html:errors property="codename" name="org.judgeos.controller.LogInForm.ERROR_KEY"/>
			<br />

			<bean:message key="account.password" />
			<html:password property="password"/>
			<html:errors property="password" name="org.judgeos.controller.LogInForm.ERROR_KEY"/>
			<br />

			<html:submit>
				<bean:message key="account.login.submit" />
			</html:submit>
		</html:form>
	</body>
</html:html>