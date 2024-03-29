<%@ include file="/taglibs.jspf" %>

<bean:define id="lastContests" name="lastContests" scope="request"/>


<tiles:insert definition="base.dfn">


	<tiles:put name="title" type="string">
		<fmt:message key=".title"/>
	</tiles:put>


	<tiles:put name="head" type="string">
		<style type="text/css">
			p.welcome {
				text-align: center;
				padding: 5pt;
				font-size: 15pt;
				color: #004;
			}

			div.contestsTitle {
				padding: 5pt;
				text-align: center;
				font-weight: bold;
				font-size: 15pt;
			}

			table.contests {
				font-size: 11pt;
				width: 40em;
				border: 1px black solid;
			}

			table.contests td, table.contests th {
				font-size: 11pt;
				border-top: 1px gray dotted;
				border-left: 1px gray dotted;
				border-right: 1px gray dotted;
				text-align: center;
			}

			table.contests td.name {
				text-align: right;
				padding: 3pt;
			}
		</style>
	</tiles:put>


	<tiles:put name="body" type="string">
		<p class="welcome">
			<fmt:message key=".welcomeMessage"/>
		</p>

		<table>
			<tr>
				<td align="center">
					<div>
						<div class="contestsTitle">
							<fmt:message key=".contests"/>
						</div>

						<table class="contests">
							<tr>
								<th><fmt:message key="contest.name"/></th>
								<th><fmt:message key="contest.start"/></th>
								<th><fmt:message key="contest.stop"/></th>
							</tr>

							<c:forEach items="${lastContests}" var="contest">
								<tr>
									<td class="name">
										<html:link action="/contest/?id=${contest.id}"
												   styleClass="name"
											>
											<c:out value="${contest.name}"/>
										</html:link>
									</td>


									<td>
										<judgeos:formatDate value="${contest.start}"/>
									</td>
									<td>
										<c:choose>
											<c:when test="${contest.stop==null}">
												&lt;
												<fmt:message key="contest.stillRunning"/>
												&gt;
											</c:when>
											<c:otherwise>
												<judgeos:formatDate value="${contest.stop}"/>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</td>
			</tr>
		</table>

	</tiles:put>


</tiles:insert>