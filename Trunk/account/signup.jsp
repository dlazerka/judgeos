<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ include file="/headers.jspf"%>

<html:html locale="true">
	<head>
		<title>
			<bean:message key=".title" />:
			<bean:message key="account.signup.title"/>
		</title>
		<judgeos:base/>
		<style type="text/css">
			.left {
				text-align: right;
			}
			.errors {
				color: red;
			}
		</style>
	</head>
	<body>
		<%@include file="/top.jspf"%>
		<html:form action="/account/signup">
			<html:hidden property="dataAttached" value="true"/>

			<table>
				<tr>
					<th colspan="2">
						<h3>
							<bean:message key="account.signup" />
						</h3>
					</th>
					<th></th>
				</tr>
				<tr>
					<td class="left"><bean:message key="account.codename" /> *</td>
					<td>
						<html:text property="codename"/>
					</td>
					<td class="errors">
						<html:errors property="codename" name="org.judgeos.controller.SignUpForm.ERROR_KEY"/>
					</td>
				</tr>
				<tr>
					<td class="left"><bean:message key="account.password" /> *</td>
					<td>
						<html:password property="password"/>
					</td>
					<td class="errors">
						<html:errors property="password" name="org.judgeos.controller.SignUpForm.ERROR_KEY"/>
					</td>
				</tr>
				<tr>
					<td class="left"><bean:message key="account.firstName" /> *</td>
					<td>
						<html:text property="firstName"/>
					</td>
					<td class="errors">
						<html:errors property="firstName" name="org.judgeos.controller.SignUpForm.ERROR_KEY"/>
					</td>
				</tr>
				<tr>
					<td class="left"><bean:message key="account.lastName" /> *</td>
					<td>
						<html:text property="lastName"/>
					</td>
					<td class="errors">
						<html:errors property="lastName" name="org.judgeos.controller.SignUpForm.ERROR_KEY"/>
					</td>
				</tr>
				<tr>
					<td class="left"></td>
					<td>
						<html:submit>
							<bean:message key="account.signup.submit" />
						</html:submit>
					</td>
					<td></td>
				</tr>
			</table>
		</html:form>
	</body>
</html:html>