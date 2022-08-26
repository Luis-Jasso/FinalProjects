<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<title>Save Customer</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/add-producto-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Producto Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Producto</h3>

		<form:form action="saveProducto" modelAttribute="producto"
			method="POST">

			<!-- need to associate this data with producto id -->
			<form:hidden path="id" />

			<table>
				<tbody>
					<tr>
						<td><label>Nombre:</label></td>
						<td><form:input path="nameProduct" /></td>
					</tr>

					<tr>
						<td><label>Descripcion:</label></td>
						<td><form:input path="descripcion" /></td>
					</tr>

					<tr>
						<td><label>Stock:</label></td>
						<td><form:input path="stock" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>

		<div style=""></div>

		<p>
			<a href="${pageContext.request.contextPath}/producto/list">Back
				to List</a>
		</p>

	</div>

</body>

</html>










