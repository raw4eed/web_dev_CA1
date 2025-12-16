<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>
<h2>Add Item</h2>
<s:form action="additem">
    <s:textfield name="title" label="Title"/>
    <s:textfield name="description" label="Description"/>
    <s:textfield name="startPrice" label="Start Price"/>
    <s:submit/>
</s:form>
<p><s:property value="message"/></p>
<p><s:a action="home">Back</s:a></p>
</body>
</html>
