<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Buscar por campo</title>
</head>
<body>
<form action="empleados" method="post">
    <input type="hidden" name="opcion" value="buscarPorCampo">
    <label for="campo">Campo de búsqueda:</label>
    <select name="campo" id="campo" required>
        <option value="">-- Seleccionar --</option>
        <option value="dni">DNI</option>
        <option value="nombre">Nombre</option>
        <option value="sexo">Sexo</option>
        <option value="categoria">Categoría</option>
        <option value="anyos">Años trabajados</option>
    </select><br/><br/>

    <label for="valor">Valor: </label>
    <input type="submit" value="BuscarPorCampo">
</form>

<c:if test="${not empty resultados}">
    <h2>Resultados de la búsqueda:</h2>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>DNI</th>
            <th>Sexo</th>
            <th>Categoría</th>
            <th>Años</th>
            <th>Sueldo</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="emp" items="${resultados}">
            <tr>
                <td><c:out value="${emp.nombre}"/></td>
                <td><c:out value="${emp.dni}"/></td>
                <td><c:out value="${emp.sexo}"/></td>
                <td><c:out value="${emp.categoria}"/></td>
                <td><c:out value="${emp.anyos}"/></td>
                <td><c:out value="${sueldo}"/></td>
                <td>
                    <a href="empleados?opcion=editar&dni=<c:out value="${emp.id}" />">Editar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
