<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>

<h2>My Bids</h2>

<p><s:property value="message"/></p>

<s:iterator value="bids">
    <div>
        Item Id: <s:property value="itemId"/> |
        Amount: <s:property value="amount"/>
    </div>
</s:iterator>

<p><s:a action="home">Back</s:a></p>

</body>
</html>
