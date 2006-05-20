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

	<body><div id="root">
		<%@ include file="/top.jspf"%>

		<judgeos:useContest var="contest" />

		<table>
			<tr>
				<td class="left">
					Name:
				</td>
				<td>
					<c:out value="${contest.name}"/>
				</td>
			</tr>
			<tr>
				<td class="left">
					Created By:
				</td>
				<td>
				</td>
			</tr>
		</table>
	</div></body>
</html:html>
