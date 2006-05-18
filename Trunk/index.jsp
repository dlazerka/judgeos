<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ include file="/headers.jspf"%>

<html:html locale="true">
	<head>
		<title>
			<bean:message key=".title" />
		</title>
		<judgeos:base/>
	</head>
	<body>

		<%@ include file="/top.jspf"%>

		<html:link page="/account/">
			<bean:message key=".account" />
		</html:link>

		<jsp:useBean id="contests" class="org.judgeos.model.LastContests" />
		<logic:iterate id="contest" name="contests" property="contests" type="org.judgeos.model.Contest">
			<bean:define id="contestCodename" name="contest" property="codename" />
			<bean:define id="contestDescription" name="contest" property="description" />

			<a href="contest/info.do?codename=<%=contestCodename%>">
				<%=contestDescription%>
			</a>

			<html:link href="contest/info.do?codename=<%=contestCodename%>">
				<%=contestDescription%>
			</html:link>
		</logic:iterate>

	</body>
</html:html>
