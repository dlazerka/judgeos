<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ include file="/headers.jspf"%>
<html:html locale="true">
	<head>
		<title>
			<fmt:message key=".title" />
		</title>
		<judgeos:base/>
		<style type="text/css">
			p.welcome {
				text-align: center;
				padding: 5pt;
				font-size: 15pt;
				color: #004;
			}
			div.lastContestsTitle {
				padding: 5pt;
				text-align: center;
				font-weight: bold;
				font-size: 15pt;
			}
			table.lastContests {
				font-size: 11pt;
				width: 40em;
				border: 1px black solid;
			}
			table.lastContests td, table.lastContests th {
				font-size: 11pt;
				border-top: 1px gray dotted;
				border-left: 1px gray dotted;
				border-right: 1px gray dotted;
				text-align: center;
			}
			table.lastContests td.name {
				text-align: right;
				padding: 3pt;
			}
			table.lastContests tr.hot {
				height: 40pt;
				background-color: #faa;
				padding: 3pt;
			}
		</style>
	</head>
	<body><div id="root">
		<%@ include file="/top.jspf"%>

		<p class="welcome">
			<fmt:message key=".welcomeMessage" />
		</p>

		<table>
			<tr>
				<td align="center">
					<div>
						<div class="lastContestsTitle">
							<fmt:message key=".lastContests"/>
						</div>

						<%-- just for example of sql stl and won't be used again --%>
						<sql:setDataSource var="judgeosDataSource" dataSource="jdbc/judgeosDB"/>
						<sql:query var="lastContests" dataSource="${judgeosDataSource}" >
							SELECT codename, name, start, stop,
								CASE WHEN (start::DATE = 'now') THEN 'hot' END AS isHot
							FROM contest
							ORDER BY start DESC
							LIMIT 10
						</sql:query>

						<table class="lastContests">
							<tr>
								<th><fmt:message key="contest.name"/></th>
								<th><fmt:message key="contest.start"/></th>
								<th><fmt:message key="contest.stop"/></th>
							</tr>
						<c:forEach items="${lastContests.rows}" var="contest">
							<tr class="${contest.isHot}">
								<td class="name">
									<html:link href="contest/info.jsp?codename=${contest.codename}"
											styleClass="name"
									>
										<c:out value="${contest.name}"/>
									</html:link>
								</td>
								<td>
									<fmt:formatDate value="${contest.start}" type="both"
											timeZone="UTC"
											pattern="yyyy-MM-dd HH:mm:ss"
									/>
								</td>
								<td>
									<fmt:formatDate value="${contest.stop}" type="both"
											timeZone="UTC"
											pattern="yyyy-MM-dd HH:mm:ss"
									/>
								</td>
							</tr>
						</c:forEach>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div></body>
</html:html>
