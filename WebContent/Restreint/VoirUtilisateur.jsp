<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Tous les utilisateurs</title>
	</head>
	
	<body>
	<%@ include file="menu.jsp" %>
		<p class="succes">Bienvenue </p>
		<form method="post" action="VoirUtilisateur">
			<p>Ajouter un utilisateur</p>
			<label for="firstname">Nom :</label>
			<input type="text" id="firstname" name="firstname"/>
			
			<label for="lastname">Prénom :</label>
			<input type="text" id="lastname" name="lastname"/>
			
			<label for="profile">Profil :</label>
			<select id="profile" name="profile">
				<option>Développeur</option>
				<option>Femme de ménage</option>
				<option>Téchnicien</option>
				<option>Comptable</option>
				<option>Secrétaire</option>
			</select>
			
			<input type="submit" value="Valider"/>
		</form>
		<c:forEach items="${ users }" var="user" varStatus="status">
		    <p>N°<c:out value="${ status.count }" /> : <a href ="/Test/ModifUtilisateur?id=${ user.id }"><c:out value="${ user.firstName }" /> <c:out value="${ user.lastName }" />!</a>  <a href="/Test/SupUtilisateur?id=${ user.id }">x</a></p>
		</c:forEach>
	</body>
</html>