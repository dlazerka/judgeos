<%@ include file="/taglibs.jspf"%>

<fmt:message key=".title" var="title"/>

<tiles:insert definition="base.dfn">
  <tiles:put name="title" value="${title}" type="string"/>
  <tiles:put name="body-content" value="my-body.jspf" type="page"/>
</tiles:insert>
