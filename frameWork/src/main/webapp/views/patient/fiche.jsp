<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="java.util.List, model.Patient" %>
<%!
    // échappement HTML pour éviter les injections (XSS)
    private static String h(Object o) {
        if (o == null) return "";
        return o.toString().replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    String ctx = request.getContextPath();
    Patient patient = (Patient) request.getAttribute("patient");
    String erreur = (String) request.getAttribute("erreur");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Patients</title>
    <style>
        body { font-family: sans-serif; margin: 2rem; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ccc; padding: .5rem .75rem; text-align: left; }
        th { background: #f3f3f3; }
        .erreur { background: #fde8e8; color: #9b1c1c; padding: .6rem 1rem; margin-bottom: 1rem; }
        .actions form { display: inline; }
        .btn { padding: .3rem .7rem; text-decoration: none; border: 1px solid #888; background: #fff; cursor: pointer; }
        .btn-danger { border-color: #c00; color: #c00; }
        .btn-primary { background: #1d4ed8; color: #fff; border-color: #1d4ed8; }
    </style>
</head>
<body>

<h1>Fiche de patient</h1>

<% if (erreur != null) { %>
    <div class="erreur"><%= h(erreur) %></div>
<% } %>

<% if (patient == null) { %>

    <p>Aucun patient enregistré.</p>

<% } else { %>

    <p>ID : <%= patient.getId() %></p>
    <p>Nom : <%= h(patient.getNom()) %></p>
    <p>Prénom : <%= h(patient.getPrenom()) %></p>
    <p>Email : <%= h(patient.getEmail()) %></p>
    <p>Téléphone : <%= h(patient.getTelephone()) %></p>
    <img src="http://localhost:8000/<%= patient.getId() %>.png" alt="">
<% } %>
</body>
</html>
