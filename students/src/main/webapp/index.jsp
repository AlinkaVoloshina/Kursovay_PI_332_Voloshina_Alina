<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
<link rel="stylesheet" type="text/css" 
href="css/style.css">
	<meta http-equiv="Content-Type" content="text/html" 
charset="UTF-8">
	<title>Главная страница</title>
</head>
	<body>
	<jsp:include page="/WEB-INF/jspf/header.jsp" />
<div id="main">
	
		<h2>Функции системы</h2>
<nav>
<ul>
<li><a href="/students/group">Группы</a>
<li><a href="/students/speciality">Направление подготовки</a>
<li><a href="/students/qualification">Квалификация</a>
<li><a href="/students/formeducation">Форма обучения</a>
</ul>
</nav>
			</div>
			<jsp:include page="/WEB-INF/jspf/footer.jsp" />
	</body>
</html>