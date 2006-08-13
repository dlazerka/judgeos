<%@ include file="/taglibs.jspf"%>


<tiles:insert definition="base.dfn">
	<tiles:put name="title" type="string">
		<fmt:message key=".title"/>:
		<fmt:message key="account.accountInfo"/>
	</tiles:put>
	<tiles:put name="body" type="string">
		<div class="underConstruction">
			<fmt:message key="underConstruction"/>
		</div>
	</tiles:put>
</tiles:insert>
