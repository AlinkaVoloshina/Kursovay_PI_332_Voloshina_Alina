<%@ page language="java" contentType="text/html"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Group"%>
<%@ page import="domain.Speciality"%>
<%@ page import="domain.Qualification"%>
<%@ page import="domain.FormEducation"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">

<title>Удаление  группы </title>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<title>Group Delete</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">

<h3>Список групп</h3>
<table>
	<thead><div class="container">
		<div class="table-responsive card">
			<table class="table table-bordered">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Код</th>
						<th scope="col">Факультет</th>
						<th scope="col">Наименование группы</th>
						<th scope="col">Курс</th>
						<th scope="col">Количество студентов</th>
						<th scope="col">Количество подгрупп</th>
						<th scope="col">Направление подготовки</th>
						<th scope="col">Квалификация</th>
						<th scope="col">Форма обучения</th>
						</tr>
	</thead>
	<tbody>
	<c:forEach var="group" items="${group}">
	<tr>
		<td>${group.getId()}</td>
		<td>${group.getFaculty()}</td>
		<td>${group.getName()}</td>
		<td>${group.getCourse()}</td>
		<td>${group.getCountStudent()}</td>
		<td>${group.getCountSubgrroup()}</td>
		<td>${group.getSpeciality()}</td>
		<td>${group.getQualification()}</td>
		<td>${group.getFormEducation()}</td>
		</tr>
						 </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
<h3>Удаление  групп</h3>

<div class="container" id="container-form">
		<form method="POST" action="">
		<div class="row mb-3">
                <label for="id" class="col-sm-2 col-form-label">Код</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" readonly
                           value="${groupDelete[0].getId()}" />
		   </div>
            </div>
            
			<div class="row mb-3">
				<label for="faculty" class="col-sm-2 col-form-label">Факультет</label>
				<div class="col-sm-10">
					<input type="text" name="faculty" class="form-control" id="staticFaculty"
					 value="${groupDelete[0].getFaculty()}" />
				</div>
			</div>
			
<div class="row mb-3">
				<label for="name" class="col-sm-2 col-form-label">Наименование группы</label>
				<div class="col-sm-10">
					<input type="text" name="name" class="form-control" id="staticName" 
					 value="${groupDelete[0].getName()}"/>
				</div>
			</div>
	<div class="row mb-3">
				<label for="course" class="col-sm-2 col-form-label">Курс</label>
				<div class="col-sm-10">
					<input type="text" name="course" class="form-control" id="staticCourse"
					 value="${groupDelete[0].getCourse()}" />
				</div>
			</div>
			
	<div class="row mb-3">
				<label for="countStudent" class="col-sm-2 col-form-label">Количество студентов</label>
				<div class="col-sm-10">
					<input type="text" name="countStudent" class="form-control" id="staticCountStudent"
					value="${groupDelete[0].getCountStudent()}" />
				</div>
			</div>
			
				<div class="row mb-3">
				<label for="countSubgrroup" class="col-sm-2 col-form-label">Количество подгрупп</label>
				<div class="col-sm-10">
					<input type="text" name="countSubgrroup" class="form-control" id="staticCountSubgrroup" 
					value="${groupDelete[0].getCountSubgrroup()}" />
				</div>
			</div>
	
	<div class="row mb-3">
				<label for="speciality" class="col-sm-2 col-form-label">Направление подготовки бакалавра</label>
				<div class="col-sm-10">
					<select name = "speciality" class="form-control" id="staticSpeciality" >
						<option>Выберите направление подготовки</option>
						<c:forEach var="speciality" items="${speciality}">
							<option value="${speciality}">
								<c:out value="${speciality.getNamespeciality()} ${speciality.getProfile()}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
	
	<div class="row mb-3">
				<label for="qualification" class="col-sm-2 col-form-label">Квалификация</label>
				<div class="col-sm-10">
					<select name = "qualification" class="form-control" id="staticQualification" >
						<option>Выберите квалификацию</option>
						<c:forEach var="qualification" items="${qualification}">
							<option value="${qualification}">
								<c:out value="${qualification.getNamequalification()} "></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
	<div class="row mb-3">
				<label for="formeducation" class="col-sm-2 col-form-label">Форма обучения</label>
				<div class="col-sm-10">
					<select name = "formeducation" class="form-control" id="staticFormEducation">
						<option>Выберите форму обучения</option>
						<c:forEach var="formeducation" items="${formeducation}">
							<option value="${formeducation}">
								<c:out value="${formeducation.getNameform()}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
	<div class="col text-center">
                <button type="submit" class="btn btn-primary" id="btn-1">Удалить</button>
                <a href='<c:url value="group" />' role="button" id="btn-2" class="btn btn-secondary">Отменить/Возврат</a>			</div>
		</form>
	</div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>