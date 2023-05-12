<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="domain.Qualification"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">


<title>Редактирование квалификаций</title>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Qualifications Edit</title>
</head>
<body>
<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div class="container-fluid">
<h3>Список квалификаций</h3>
<div class="container">
		<div class="table-responsive card">
			<table class="table table-bordered">
				<thead class="thead-dark">
				<tr>
					<th scope="col">Код</th>
					<th scope="col">Квалификация</th>
					</tr>
	</thead>
	<tbody>
	<c:forEach var="qualification" items="${qualification}">
		<tr>
		<td>${qualification.getId()}</td>
		<td>${qualification.getNamequalification()}</td>
		  </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
	<h3>Редактирование наименование квалификаций</h3>
	<div class="container" id="container-form">
        <form method="POST" action="">
            <div class="row mb-3">
                <label for="id" class="col-sm-2 col-form-label">Код</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" readonly
                           value="${qualificationEdit[0].getId()}" />
                </div>
            </div>
		
		
			<div class="row mb-3">
				<label for="namequalification" class="col-sm-2 col-form-label">Квалификация</label>
				<div class="col-sm-10">
					<input type="text" name="namequalification" class="form-control" id="staticNamequalification" />
				</div>
			</div>
	<div class="col text-center">
                <button type="submit" class="btn btn-primary" id="btn-1">Редактировать</button>
                <a href='<c:url value="/qualification" />' role="button" id="btn-2" class="btn btn-secondary">Отменить/Возврат</a>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>