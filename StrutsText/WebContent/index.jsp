<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<fmt:setBundle basename="message"  var='ms'></fmt:setBundle>

<h3><fmt:message bundle="${ms}" key='index.welcome'></fmt:message></h3>




<h3><a href="<c:url value="/secure/login.jsp"/>">Login</a></h3>
<h3><a href="<c:url value="/pages/product.jsp"/>">Poduct</a></h3>

</body>
</html>