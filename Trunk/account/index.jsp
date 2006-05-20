<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ include file="/headers.jspf"%>

<logic:notPresent scope="session" name="account">
	<logic:redirect forward="login" />
</logic:notPresent>


<html:html locale="true">
	<head>
		<title>
			<bean:message key=".title" />:
			<bean:message key="account.title" />
		</title>
		<judgeos:base/>
	</head>
	<body>
		<%@include file="/top.jspf"%>
	</body>
</html:html>
