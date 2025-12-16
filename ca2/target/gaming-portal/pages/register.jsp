<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>

<h2>Register</h2>

<s:form action="register">
    <s:textfield name="username" label="Username"/>
    <s:password name="password" label="Password"/>
    <s:textfield name="email" label="Email"/>
    <s:submit/>
</s:form>

<p><s:property value="message"/></p>

<p><s:a action="home">Back</s:a></p>

</body>
</html>
