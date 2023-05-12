<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.FormEducation"%>





<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Форма обучения</title>
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		
<title>FormEducation</title>

</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
	<h3>Список форм обучения</h3>
	<div class="container">
		<div class="table-responsive card">
			<table class="table table-bordered">
				<thead class="thead-dark">
				<tr>
					<th scope="col">Код</th>
					<th scope="col">Форма обучения</th>
					<th scope="col">Редактировать</th>
					<th scope="col">Удалить</th>
				</tr>
				</thead>
				<tbody>

<c:forEach var="formeducation" items="${formeducation}">
<tr>
		<td>${formeducation.getId()}</td>
		<td>${formeducation.getNameform()}</td>	
		<td width="20" id="td-edit">
							<a href='<c:url value="/editFormeducation?id=${formeducation.getId()}" />'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Редактировать" src="images/edit.png"></a>
								</td>
						<td width="20" id="td-delete">
							<a href='<c:url value="/deleteFormeducation?id=${formeducation.getId()}"/>'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Удалить" src="images/delete.png"></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

<h3>Наименование формы обучения</h3>
<div class="container" id="container-form">
		<form method="POST" action="">
			<div class="row mb-3">
				<label for="nameform" class="col-sm-2 col-form-label">Форма обучения</label>
				<div class="col-sm-10">
					<input type="text" name="nameform" class="form-control" id="nameform" />
				</div>
			</div>

<div class="col text-center">
				<button type="submit" class="btn btn-primary" id="btn-1">Добавить</button></p>
			</div>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>