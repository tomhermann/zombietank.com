<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
  <head>
    <title>Login</title>
  </head>
 
  <body>
    <h1>Login</h1>
 
    <c:if test="${not empty param.error}">
		<div class="alert-message error"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>. Please try again.</div>
    </c:if>
 
    <form name="login" action="<c:url value='/login/authenticate'/>" method="POST" class="form-stacked">
    	<fieldset>
			<div class="clearfix">
				<label for="j_username">User:</label>
				<input type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
			</div>
			<div class="clearfix">
				<label for="j_password">Password:</label>
				<input type='password' name='j_password' />
			</div>
			<div class="clearfix">
				<label for="_spring_security_remember_me">
                    <input type="checkbox" name="_spring_security_remember_me">
                    <span>Remember me</span>
                </label>
			</div>
    	</fieldset>
    	<button type="submit" class="btn">Send</button>
    </form>
  </body>
</html>
