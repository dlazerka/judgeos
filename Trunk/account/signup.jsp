<%--
  Created: 15.05.2006 17:50:59
--%>
<%@ include file="/headers.jspf"%>

<html:html locale="true">
	<head>
		<title>
			<fmt:message key=".title" />:
			<fmt:message key="account.signup"/>
		</title>
		<judgeos:base/>
	</head>
	<body>
		<%@include file="/top.jspf"%>
		<html:form action="/account/signup">
			<html:hidden property="dataAttached" value="true"/>

			<div align="center">
				<table style="width: auto;">
					<tr>
						<th colspan="3">
							<h3>
								<fmt:message key="account.signup" />
							</h3>
						</th>
					</tr>
					<tr>
						<td class="label"><fmt:message key="account.codename" /> *</td>
						<td>
							<html:text property="codename"/>
						</td>
						<td class="errorLabel">
							<html:messages id="codename"/>
							<html:errors property="codename"/>
						</td>
					</tr>
					<tr>
						<td class="label">
							<fmt:message key="account.password" />
							*
						</td>
						<td>
							<html:password property="password"/>
						</td>
						<td class="errorLabel">
							<html:errors property="password"/>
						</td>
					</tr>
					<tr>
						<td class="label">
							<fmt:message key="account.firstName" />
							*
						</td>
						<td>
							<html:text property="firstName"/>
						</td>
						<td class="errorLabel">
							<html:errors property="firstName"/>
						</td>
					</tr>
					<tr>
						<td class="label">
							<fmt:message key="account.lastName" />
							*
						</td>
						<td>
							<html:text property="lastName"/>
						</td>
						<td class="errorLabel">
							<html:errors property="lastName"/>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<br>
							<html:submit>
								<fmt:message key="account.signup" />
							</html:submit>
						</td>
					</tr>
				</table>
			</div>
		</html:form>
	</body>
</html:html>