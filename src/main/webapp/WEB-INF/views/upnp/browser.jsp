<%@ page session="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<h1>UPnP Browser</h1>

<ul>
	<li><a href="<c:url value="/upnp/search/all" />">Search for all UPnP devices</a></li>
	<li><a href="<c:url value="/upnp/devices" />">Discovered devices</a></li>
</ul>