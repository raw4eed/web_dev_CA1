<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>

<h2>Login</h2>

<s:form action="login">
    <s:textfield name="username" label="Username"/>
    <s:password name="password" label="Password"/>
    <s:submit/>
</s:form>

<p><s:property value="message"/></p>

<p><s:a action="home">Back</s:a></p>

</body>
</html>
