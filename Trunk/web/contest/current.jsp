<%@ include file="/taglibs.jspf" %>

<bean:define id="contestMember" name="contestMember" scope="session"/>


<tiles:insert definition="base.dfn">
	<tiles:put name="title" type="string">
		<fmt:message key=".title"/>:
		<c:out value="${contestMember.contest.name}"/>
	</tiles:put>
	<tiles:put name="head" type="string">
		<style type="text/css">
		</style>
	</tiles:put>
	<tiles:put name="body" type="string">
		<div align="center">
			in development
		</div>
	</tiles:put>
</tiles:insert>

