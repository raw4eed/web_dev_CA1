<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>

<h2>My Profile</h2>

<p><s:property value="message"/></p>

<div>
    Username: <s:property value="u.username"/><br/>
    Email: <s:property value="u.email"/>
</div>

<p><s:a action="home">Back</s:a></p>

</body>
</html>
