<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Speciality"%>



<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css"> 
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8"> 
<title>Направления подготовки</title>
<head>
<meta charset="UTF-8">
<title>Speciality</title>
</head>

<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
<h3>Список направлений подготовки бакалавриата</h3>
<div class="container">
		<div class="table-responsive card">
			<table class="table table-bordered">
				<thead class="thead-dark">
	<tr>
	<th scope="col">Код</th>
	<th scope="col">Направление подготовки бакалавра</th>
	<th scope="col">Профиль подготовки</th>
	<th scope="col">Редактировать</th>
	<th scope="col">Удалить</th>
	</tr>
	</thead>
	<tbody>
	
	<c:forEach var="speciality" items="${speciality}">
		<tr>
		<td>${speciality.getId()}</td>
		<td>${speciality.getNamespeciality()}</td>
		<td>${speciality.getProfile()}</td>
		<td width="20" id="td-edit">
							<a href='<c:url value="/editSpeciality?id=${speciality.getId()}" />'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Редактировать" src="images/edit.png"></a>
						</td>
						<td width="20" id="td-delete">
							<a href='<c:url value="/deleteSpeciality?id=${speciality.getId()}"/>'
							   role="button" class="btn btn-outline-secondary">
								<img alt="Удалить" src="images/delete.png"></a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<h3>Добавить направление</h3>
	<div class="container" id="container-form">
		<form method="POST" action="">
			<div class="row mb-3">
				<label for="namespeciality" class="col-sm-2 col-form-label">Направление</label>
				<div class="col-sm-10">
					<input type="text" name="namespeciality" class="form-control" id="staticNamespeciality" />
				</div>
			</div>
			
			<div class="row mb-3">
				<label for="profile" class="col-sm-2 col-form-label">Профиль подготовки</label>
				<div class="col-sm-10">
					<input type="text" name="profile" class="form-control" id="staticProfile" />
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