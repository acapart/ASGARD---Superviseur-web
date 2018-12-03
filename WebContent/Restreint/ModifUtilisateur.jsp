<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modifier ${ user.firstName }</title>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		Modifictation de ${ user.firstName }
		<form method="post" action=ModifUtilisateur>
		
			<input type="hidden" id="id" name="id" value="${ user.id }" />
			
			<label for="nom">Nom : </label>
			<input type="text" id="firstname" name="firstname" value="${ user.firstName }" />
			
			<label for="email">Prénom : </label>
			<input type="text" id="lastname" name="lastname" value="${ user.lastName }" />
			
			<input type="submit" value="Valider"/>
		</form>
		
		
	
	</body>
</html>