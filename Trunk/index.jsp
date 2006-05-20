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
		<style type="text/css">
			div.lastContests {
				width: 450pt;
				position: absolute;
				right: 30pt;
			}
			div.lastContestsTitle {
				padding: 5pt;
				text-align: center;
				font-weight: bold;
				font-size: 15pt;
			}
			table.lastContests {
				width: 100%;
				border-collapse: collapse;
				border: 1px black solid;
			}
			table.lastContests td, table.lastContests th {
				font-size: 12pt;
				border-top: 1px black dashed;
				border-left: 1px black solid;
				border-right: 1px black solid;
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

		<p>
		<bean:message key=".welcomeMessage" />
		</p>


		<div class="lastContests">
			<div class="lastContestsTitle">
				<bean:message key=".lastContests"/>
			</div>

			<%-- just for example of sql stl and won't be used again --%>
			<sql:setDataSource var="judgeos" dataSource="jdbc/judgeosDB"/>
			<sql:query var="lastContests" dataSource="${judgeos}" >
				SELECT
					codename,
					name,
					startTime AS startTime,
					stopTime AS stopTime,
					CASE WHEN (startTime::DATE = 'now') THEN 'hot' END AS isHot
				FROM contest
				ORDER BY startTime DESC
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
						<html:link href="contest/info.jsp?codename=${fn:escapeXml(contest.codename)}"
								styleClass="name"
						>
							<c:out value="${contest.name}"/>
						</html:link>
					</td>
					<td>
						<fmt:formatDate value="${contest.startTime}" type="both"
								timeZone="UTC"
								pattern="yyyy-MM-dd HH:mm:ss"
						/>
					</td>
					<td>
						<fmt:formatDate value="${contest.startTime}" type="both"
							    pattern="yyyy-MM-dd HH:mm:ss"
						/>
					</td>
				</tr>
			</c:forEach>
			</table>
		</div>
	</div></body>
</html:html>
