<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ include file="/headers.jspf"%>

<logic:notPresent scope="session" name="account">
	<logic:redirect forward="login" />
</logic:notPresent>
<logic:present scope="session" name="account">
	<logic:redirect forward="root" />
</logic:present>


<html:html locale="true">
	<head>
		<title>
			<fmt:message key=".title" />:
			<fmt:message key="account.title" />
		</title>
		<judgeos:base/>
	</head>
	<body>
		<%@include file="/top.jspf"%>
	</body>
</html:html>
