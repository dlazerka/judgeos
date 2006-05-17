<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ include file="/headers.jsp"%>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/header.css">

<table width="100%">
	<tr>
		<td align="left">
			<div class="header_logo">
				<bean:message key=".logo" />
			</div>
		</td>
		<td align="right">
			<logic:present scope="session" name="account">
				<bean:message key="top.welcome"/>
				<b><judgeos:account parameter="codename"/></b>.

				<html:link action="account/logout.do">
					<bean:message key="top.logout"/>
				</html:link>
			</logic:present>
			<logic:notPresent scope="session" name="account">
				<bean:message key="top.notLogged"/>
				<html:link href="account/login.jsp">
					<bean:message key="top.login"/>
				</html:link>
				<html:link href="account/signup.jsp">
					<bean:message key="top.signup"/>
				</html:link>
			</logic:notPresent>
		</td>
	</tr>
</table>
<hr>