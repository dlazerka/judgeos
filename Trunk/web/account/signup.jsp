<%@ include file="/taglibs.jspf" %>


<tiles:insert definition="base.dfn">
	<tiles:put name="title" type="string">
		<fmt:message key=".title"/>:
		<fmt:message key="account.signup"/>
	</tiles:put>
	<tiles:put name="body" type="string">

		<html:form action="/account/signup-submit">
			<html:hidden property="dataAttached" value="true"/>

			<div align="center">
				<table style="width: auto;">
					<tr>
						<th colspan="3">
							<h3>
								<fmt:message key="account.signup"/>
							</h3>
						</th>
					</tr>
					<tr>
						<td class="label">
							<fmt:message key="account.email"/>
							*
						</td>
						<td>
							<html:text property="email"/>
						</td>
						<td class="errorLabel">
							<html:messages id="email"/>
							<html:errors property="email"/>
						</td>
					</tr>
					<tr>
						<td class="label">
							<fmt:message key="account.password"/>
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
							<fmt:message key="account.fullName"/>
							*
						</td>
						<td>
							<html:text property="fullName"/><br>
							<span class="underField">e.g. John Smith</span>
						</td>
						<td class="errorLabel">
							<html:errors property="fullName"/>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<br>
							<html:submit>
								<fmt:message key="account.signup"/>
							</html:submit>
						</td>
					</tr>
				</table>
			</div>
		</html:form>
	</tiles:put>
</tiles:insert>