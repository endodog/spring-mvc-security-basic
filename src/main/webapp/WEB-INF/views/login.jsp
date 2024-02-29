<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<form method="post">
  <label>User name:</label><br>
  <input type="text" name="username"/><br>
  <label>Password:</label><br>
  <input type="password" name="password"/><br><br>
  <input type="submit" value="Login">
  <%--  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
  <br><br>
</form>

</body>
</html>
