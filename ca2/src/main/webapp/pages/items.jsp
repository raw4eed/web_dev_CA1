<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>

<h2>Items</h2>

<p><s:property value="message"/></p>

<s:iterator value="items">
    <div>
        <b><s:property value="title"/></b><br/>
        <s:property value="description"/><br/>
        Start Price: <s:property value="startPrice"/><br/>
        Seller: <s:property value="sellerUsername"/><br/>
        <s:a action="bid">
            <s:param name="itemId" value="%{id}"/>
            Make Bid
        </s:a>

        <s:a action="itembids">
            <s:param name="itemId" value="%{id}"/>
            View Bids
        </s:a>
    </div>
    <hr/>
</s:iterator>

<p><s:a action="home">Back</s:a></p>
</body>
</html>
