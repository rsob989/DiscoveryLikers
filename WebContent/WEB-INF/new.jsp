<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weekop - add</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
	
	<jsp:include page="fragment/navbar.jspf"/>
	
	<div class="container">
		<div class="col-md-8 col-md-offset-2">
      		<form class="form-signin" action="add" method="post">
        		<h2 class="form-signin-heading">Add new discovery</h2>
        		<input name="inputName" type="text" class="form-control" placeholder="Discovery name" required autofocus>
        		<input name="inputUrl" type="url" class="form-control" placeholder="URL" required autofocus>
        		<textarea name="inputDescription" rows="5" class="form-control" placeholder="Description" required autofocus></textarea>
        		<input class="btn btn-lg btn-primary btn-block" type="submit" value="Add!"/>
      		</form>
      	</div>
	</div>
	
	<jsp:include page="fragment/footer.jspf"/>	
	
	<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>




    
  

