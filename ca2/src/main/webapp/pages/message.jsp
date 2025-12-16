<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>

<h2>Message</h2>
<p><s:property value="message"/></p>
<p><s:a action="home">Back</s:a></p>
</body>
</html>
