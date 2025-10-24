<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Buscar Sueldo Empleado</title>
</head>
<body>
<h1>Buscar Sueldo Empleado</h1>
<form action="empleados" method="post">
    <input type="hidden" name="opcion" value="buscar">
    <table border="1">
        <tr>
            <td>DNI:</td>
            <td><input type="text" name="dni" required maxlength="9"></td>
        </tr>
    </table>
    <input type="submit" value="Buscar">

    <c:if test="${request.getAttribute('error') == null}">
        <p>
            El empleado <c:out value="${nombre}"></c:out> con DNI <c:out value="dni"></c:out> tiene un
            salario de <c:out value="${sueldo}"></c:out> euros.
        </p>
    </c:if>

    <c:if test="${request.getAttribute('error') != null}">
        <p>ERROR: <c:out value="${error}"></c:out></p>
    </c:if>
</form>
</body>
</html>