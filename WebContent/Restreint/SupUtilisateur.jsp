<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		<p>Voulez vous vraiment supprimer l'utilisateur ${ usr.name }</p>
		<p><a href="/Test/SupUtilisateur?sup=true&id=${ user.id }">Oui</a>/<a href="/Test/VoirUtilisateur">Non</a></p>
	</body>
</html>