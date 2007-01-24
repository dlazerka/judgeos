<%@ include file="/taglibs.jspf" %>

<c:if test="${contest != null}">
	<bean:define id="contest" name="contest" scope="request"/>
</c:if>


<tiles:insert definition="base.dfn">
	<tiles:put name="title" type="string">
		<fmt:message key=".title"/>:
		<fmt:message key="contest.contestInfo" />
	</tiles:put>
	<tiles:put name="head" type="string">
		<style type="text/css">
			table.contestInfo {
				width: auto;
			}

			td.label {
				padding-right: 1em;
			}

			td.value {
				text-align: left;
			}
		</style>
	</tiles:put>
	<tiles:put name="body" type="string">
		<div align="center">
			<table class="contestInfo">
				<tr>
					<th colspan="2">
						<h3>
							<fmt:message key="contest.contestInfo"/>
						</h3>
					</th>
				</tr>
				<tr>
					<td class="label">
						<fmt:message key="contest.name"/>
						:
					</td>
					<td class="value">
						<c:out value="${contest.name}"/>
					</td>
				</tr>
				<tr>
					<td class="label">
						<fmt:message key="contest.start"/>
						:
					</td>
					<td class="value">
						<judgeos:formatDate value="${contest.start}"/>
					</td>
				</tr>
				<tr>
					<td class="label">
						<fmt:message key="contest.stop"/>
						:
					</td>
					<td class="value">
						<c:choose>
							<c:when test="${contest.stop != null}">
								<judgeos:formatDate value="${contest.stop}"/>
							</c:when>
							<c:otherwise>
								&lt;
								<fmt:message key="contest.stillRunning"/>
								&gt;
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label">
						Created By:
					</td>
					<td class="value">
						<html:link action="/account/profile?id=${contest.owner.id}">
							<c:out value="${contest.owner.fullName}"/>
						</html:link>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="font-size: 15pt;text-align:center;">
						<html:link action="/contest/particicipate?id=${contest.id}">
							<fmt:message key="contest.participate"/>
						</html:link>
					</td>
				</tr>
			</table>
		</div>
	</tiles:put>
</tiles:insert>

