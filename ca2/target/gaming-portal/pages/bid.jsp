<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>

<h2>Make a Bid</h2>

<s:form action="bid">
    <s:hidden name="itemId"/>
    Item Id: <s:property value="itemId"/><br/>
    <s:textfield name="amount" label="Amount"/>
    <s:submit/>
</s:form>

<p><s:property value="message"/></p>

<p><s:a action="listitems">Back to Items</s:a></p>

</body>
</html>
