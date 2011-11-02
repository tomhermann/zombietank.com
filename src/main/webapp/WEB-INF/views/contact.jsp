<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:if test="${not empty message}">
	<div class="${message.type.cssClass}">${message.text}</div>
</c:if>

<c:url value="/contact" var="contactUrl" />
<form:form action="${contactUrl}" method="post" modelAttribute="contactForm">
	<div>
		<span><form:errors path="name" /></span>
		<span><form:label path="name">Name: </form:label></span>
		<span><form:input path="name" /></span>
	</div>
	<div>
		<span><form:errors path="email" /></span>
		<span><form:label path="email">Email: </form:label></span>
		<span><form:input path="email" /></span>
	</div>
	<div>
		<span><form:errors path="subject" /></span>
		<span><form:label path="subject">Subject: </form:label></span>
		<span><form:input path="subject" /></span>
	</div>
	<div>
		<span><form:errors path="message" /></span>
		<span><form:label path="message">Message: </form:label></span>
		<span><form:textarea path="message" cols="60"/></span>
	</div>
	
	<p><button type="submit">Send</button></p>
</form:form>