<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ include file="/headers.jsp"%>

<html:html locale="true">
	<head>
		<title>
			<bean:message key=".title" />
		</title>
		<judgeos:base/>
	</head>
	<body>

		<%@ include file="/top.jsp"%>

		<html:link page="/account/">
			<bean:message key=".account" />
		</html:link>


	</body>
</html:html>
