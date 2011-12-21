<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:if test="${not empty success}">
	<div class="alert-message success">${success}</div>
</c:if>

<c:url value="/contact" var="contactUrl" />

<form:form action="${contactUrl}" method="post" modelAttribute="contactForm" class="form-stacked">
	<c:set var="nameErrors"><form:errors path="name"/></c:set>
	<c:set var="emailErrors"><form:errors path="email"/></c:set>
	<c:set var="subjectErrors"><form:errors path="subject"/></c:set>
	<c:set var="messageErrors"><form:errors path="message"/></c:set>

	<fieldset>
		<div class="clearfix<c:if test="${not empty nameErrors}"> error</c:if>">
			<form:label path="name">Name:</form:label>
			<form:input path="name" />
			<form:errors path="name" cssClass="help-inline" />
		</div>
		<div class="clearfix<c:if test="${not empty emailErrors}"> error</c:if>">
			<form:label path="email">Email:</form:label>
			<form:input path="email"/>
			<form:errors path="email" cssClass="help-inline" />
		</div>
		<div class="clearfix<c:if test="${not empty subjectErrors}"> error</c:if>">
			<form:label path="subject">Subject:</form:label>
			<form:input path="subject"/>
			<form:errors path="subject" cssClass="help-inline" />
		</div>
		<div class="clearfix<c:if test="${not empty messageErrors}"> error</c:if>">
			<form:label path="message">Message:</form:label>
			<form:textarea path="message" cols="60"/>
			<form:errors path="message" cssClass="help-inline" />
		</div>
	</fieldset>
	<button type="submit" class="btn">Send</button>
</form:form>