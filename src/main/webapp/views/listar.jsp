<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listar Empleados</title>
</head>
<body>
<h1>Listar Empleados</h1>
<table border="1">
    <tr>
        <td>Nombre</td>
        <td>DNI</td>
        <td>Sexo</td>
        <td>A�os trabajados</td>
        <td>Categor�a</td>
    </tr>
    <c:forEach var="empleado" items="${lista}">
        <tr>
            <td>
                <a href="empleados?opcion=meditar&id=<c:out value="${ empleado.id}"></c:out>">
                    <c:out value="${ empleado.id}"></c:out>
                </a>
            </td>
            <td><c:out value="${ empleado.nombre}"></c:out></td>
            <td><c:out value="${ empleado.dni}"></c:out></td>
            <td><c:out value="${ empleado.sexo}"></c:out></td>
            <td><c:out value="${ empleado.anyos}"></c:out></td>
            <td><c:out value="${ empleado.categoria}"></c:out></td>
            <td>
                <a href="empleados?opcion=eliminar&id=<c:out value="${ empleado.id}"></c:out>">
                    Eliminar
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
