<%@ include file="/taglibs.jspf" %>


<tiles:insert definition="base.dfn">
	<tiles:put name="title" type="string">
		<fmt:message key=".title"/>:
		<fmt:message key="contest.contestInfo"/>
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
		<judgeos:useContest var="contest"/>
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
						<fmt:formatDate value="${contest.start}" type="both"
						                timeZone="UTC"
						                pattern="yyyy-MM-dd HH:mm:ss zzz"
							/>
					</td>
				</tr>
				<tr>
					<td class="label">
						<fmt:message key="contest.stop"/>
						:
					</td>
					<td class="value">
						<fmt:formatDate value="${contest.stop}" type="both"
						                timeZone="UTC"
						                pattern="yyyy-MM-dd HH:mm:ss zzz"
							/>
					</td>
				</tr>
				<tr>
					<td class="label">
						Created By:
					</td>
					<td class="value">
						<html:link action="/account?codename=${contest.ownerCodename}">
							<c:out value="${contest.ownerFirstName}"/>
							<c:out value="${contest.ownerLastName}"/>
						</html:link>
					</td>
				</tr>
			</table>
		</div>
	</tiles:put>
</tiles:insert>

