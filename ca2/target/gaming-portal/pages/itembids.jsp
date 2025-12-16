<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>

<h2>Bids for Item <s:property value="itemId"/></h2>

<p><s:property value="message"/></p>

<s:iterator value="bids">
    <div>
        Bidder: <s:property value="username"/> |
        Amount: <s:property value="amount"/>
    </div>
</s:iterator>

<p><s:a action="listitems">Back to Items</s:a></p>

</body>
</html>
