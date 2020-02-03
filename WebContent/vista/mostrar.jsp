<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar Departamentos</title>
</head>
<body>
	<h1>Lista  Departamento</h1>
	<table>
		<tr>
			<td><a href="AdminArticulo?action=index" >Ir al menú</a> </td>
		</tr>
	</table>
	
	<table border="1" width="100%">
		<tr>

		 <td> CODIGO</td>
		 <td> NOMBRE</td>
		 <td>LOCALIDAD</td>
	
		 <td colspan=2>ACCIONES</td>
		</tr>
		<c:forEach var="departamento" items="${lista}">
			<tr>
				<td><c:out value="${departamento.deptno}"/></td>
				<td><c:out value="${departamento.dnombre}"/></td>
				<td><c:out value="${departamento.loc}"/></td>
			
				<td><a href="AdminArticulo?action=showedit&id=<c:out value="${departamento.deptno}" />">Editar</a></td>
				<td><a href="AdminArticulo?action=eliminar&id=<c:out value="${departamento.deptno}"/>">Eliminar</a> </td>				
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>