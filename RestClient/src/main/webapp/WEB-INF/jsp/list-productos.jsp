<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>
<title>List Productos</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Productos Relationship Manager</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<!-- put new button: Add Producto -->

			<input type="button" value="Add Productor"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />

			<!--  add our html table here -->

			<table>
				<tr>
					<th>Nombre</th>
					<th>Descripcion</th>
					<th>Stock</th>
					<th>Action</th>
				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="themProducto" items="${productos}">

					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/producto/showFormForUpdate">
						<c:param name="productoId" value="${themProducto.id}" />
					</c:url>

					<!-- construct an "delete" link with producto id -->
					<c:url var="deleteLink" value="/producto/delete">
						<c:param name="productoId" value="${themProducto.id}" />
					</c:url>
					<c:if></c:if>

					<tr>
						<td>${themProducto.nameProduct}</td>
						<td>${themProducto.descripcion}</td>
						<td>${themProducto.stock}</td>

						<td>
							<!-- display the update link --> <a href="${updateLink}">Update</a>
							| <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this producto?'))) return false">Delete</a>
						</td>

					</tr>

				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>









