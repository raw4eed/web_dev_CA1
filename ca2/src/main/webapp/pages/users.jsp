<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>

<h2>Users</h2>
<p><s:property value="message"/></p>
<s:iterator value="users">
    <div>
        <s:property value="username"/>
        <s:a action="viewuser">
            <s:param name="username" value="%{username}"/>
            View Profile
        </s:a>
    </div>
</s:iterator>
<p><s:a action="home">Back</s:a></p>
</body>
</html>
