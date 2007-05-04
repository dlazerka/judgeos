<%@ include file="/taglibs.jspf" %>

<bean:define id="contestMember" name="contestMember" scope="session"/>
<bean:define id="gwtModuleName" value="org.judgeos.gwt.ControlPanel" scope="request"/>
<bean:define id="gwtModulePath" value="gwt/${gwtModuleName}" scope="request"/>


<tiles:insert definition="base.dfn">
	<tiles:put name="title" type="string">
		<fmt:message key=".title"/>:
		<c:out value="${contestMember.contest.name}"/>
	</tiles:put>
	<tiles:put name="head" type="string">
		<style type="text/css">
		</style>
		<meta name="gwt:module" content="gwt/org.judgeos.gwt.ControlPanel/org.judgeos.gwt.ControlPanel">
		<script type="text/javascript" language="javascript" src="gwt/org.judgeos.gwt.ControlPanel/gwt.js"></script>
		<link type="text/css" rel="stylesheet" href="css/ControlPanel.css">
	</tiles:put>
	<tiles:put name="body" type="string">
		<div id="body"></div>
	</tiles:put>
</tiles:insert>

