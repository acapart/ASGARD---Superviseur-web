<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un utilisateur</title>
</head>
<body>

	<%@ include file="menu.jsp"%>

<form method="post" action="Inscription">
            <fieldset>
                <legend>Ajout d'un utilisateur</legend>
                <p>Vous pouvez ajouter un utilisateur via ce formulaire.</p>

                <label for="firstname">Nom <span class="requis">*</span></label>
                <input type="text" id="firstname" name="firstname" value="<c:out value="${utilisateur.firstname}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['firstname']}</span>
                <br />

                <label for="name">Prénom <span class="requis">*</span></label>
                <input type="text" id="name" name="name" value="<c:out value="${utilisateur.name}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['name']}</span>
                <br />

                <label for="profile">Profil <span class="requis">*</span></label>
                <input type="text" id="profile" name="profile" value="<c:out value="${utilisateur.profile}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['name']}</span>
                <br />

                <input type="submit" value="Inscription" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
            </fieldset>
        </form>
</body>
</html>