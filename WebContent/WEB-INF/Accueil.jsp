<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>
    
     <%@ include file="menu.jsp" %>
    
     <form method="post" action="Accueil">
            <fieldset>
                <legend>Superviseur Asgard</legend>
                
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
                <%-- Vérification de la présence d'un objet utilisateur en session --%>
                <c:if test="${!empty sessionScope.sessionUtilisateur}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <p class="succes">Bienvenue ${sessionScope.sessionUtilisateur.nom}</p>
                </c:if>
            </fieldset>
        </form>
    </body>
</html>