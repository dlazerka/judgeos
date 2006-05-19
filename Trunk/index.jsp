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
		<style type="text/css">
			div.lastContests {
				position: absolute;
				width: 300pt;
				right: 30pt;
				border: 1px black solid;
				padding: 5pt;
			}
			div.lastContestsTitle {
				background-color: #eef;
				color: #001;
				padding: 5pt;
				text-align: center;
				font-weight: bold;
				font-size: 20pt;
			}
			a.contestCodename {
				font-size: 15pt;
			}
		</style>
		<%@ include file="/top.jspf"%>

		<html:link page="/account/">
			<bean:message key=".account" />
		</html:link>

		<sql:setDataSource
				var="example"
				driver="org.postgresql.Driver"
				url="jdbc:postgresql://localhost:5432/judgeos"
				user="judgeos"
				password="judgeos"
		/>
		<sql:query var="contests" dataSource="${example}" >
			SELECT * FROM contest
		</sql:query>

		<c:forEach items="${contests.rows}" var="contest">
			${contest.codename} <%-- TODO ! contests and hot contests list --%>
		</c:forEach>

		<div class="lastContests">
			<div class="lastContestsTitle">
				<bean:message key=".contests"/>
			</div>

			<jsp:useBean id="lastContests" class="org.judgeos.model.LastContests" />
			<table>
			<c:forEach items="${lastContests.contests}" var="contest">
				<tr>
					<td>
						<html:link action="/contest/info?codename=${fn:escapeXml(contest.codename)}" styleClass="contestCodename">
							${fn:escapeXml(contest.codename)}
						</html:link>
					</td>
				</tr>
			</c:forEach>
			</table>
		</div>
	</div></body>
</html:html>
