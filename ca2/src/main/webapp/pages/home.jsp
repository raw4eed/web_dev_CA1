<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<body>

<div>
    Logged in as <s:property value="#session.currentUser" />
</div>
<h2>Home</h2>
<ul>
    <li><s:a action="login">Login</s:a></li>
    <li><s:a action="register">Register</s:a></li>
    <li><s:a action="logout">Logout</s:a></li>
    <li><s:a action="viewprofile">My Profile</s:a></li>
    <li><s:a action="listusers">View All Users</s:a></li>
    <li><s:a action="additem">Add Item</s:a></li>
    <li><s:a action="listitems">View Items</s:a></li>
    <li><s:a action="mybids">My Bids</s:a></li>
</ul>
</body>
</html>
