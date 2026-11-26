<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="model.Patient" %>
<%!
    private static String h(Object o) {
        if (o == null) return "";
        return o.toString().replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    String ctx = request.getContextPath();
    Patient p = (Patient) request.getAttribute("patient");
    String erreur = (String) request.getAttribute("erreur");
    boolean edition = (p.getId() != null);
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title><%= edition ? "Modifier" : "Nouveau" %> patient</title>
    <style>
        body { font-family: sans-serif; margin: 2rem; max-width: 480px; }
        label { display: block; margin-top: .9rem; font-weight: bold; }
        input { width: 100%; padding: .45rem; box-sizing: border-box; }
        .erreur { background: #fde8e8; color: #9b1c1c; padding: .6rem 1rem; margin-bottom: 1rem; }
        .btn { padding: .45rem 1rem; text-decoration: none; border: 1px solid #888; background: #fff; cursor: pointer; display: inline-block; }
        .btn-primary { background: #1d4ed8; color: #fff; border-color: #1d4ed8; }
        .barre { margin-top: 1.5rem; }
    </style>
</head>
<body>
    <h1><%= edition ? "Modifier le patient #" + p.getId() : "Nouveau patient" %></h1>

    <% if (erreur != null) { %>
        <div class="erreur"><%= h(erreur) %></div>
    <% } %>

    <form method="post" action="<%= ctx %>/patients/save">
        <% if (edition) { %>
            <input type="hidden" name="patient.id" value="<%= p.getId() %>">
        <% } %>

        <label for="nom">Nom *</label>
        <input id="nom" type="text" name="patient.nom" maxlength="50" required value="<%= h(p.getNom()) %>">

        <label for="prenom">Prénom *</label>
        <input id="prenom" type="text" name="patient.prenom" maxlength="90" required value="<%= h(p.getPrenom()) %>">

        <label for="dn">Date de naissance</label>
        <input id="dn" type="date" name="patient.dateNaissance" value="<%= h(p.getDateNaissance()) %>">

        <label for="email">Email</label>
        <input id="email" type="email" name="patient.email" maxlength="150" value="<%= h(p.getEmail()) %>">

        <label for="tel">Téléphone</label>
        <input id="tel" type="text" name="patient.telephone" maxlength="20" value="<%= h(p.getTelephone()) %>">

        <div class="barre">
            <button type="submit" class="btn btn-primary">Enregistrer</button>
            <a class="btn" href="<%= ctx %>/patients">Annuler</a>
        </div>
    </form>
</body>
</html>
