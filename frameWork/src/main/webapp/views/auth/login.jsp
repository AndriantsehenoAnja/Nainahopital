
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Connexion</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <style>
        body {
            background: linear-gradient(135deg, #667eea, #764ba2);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: Arial, sans-serif;
        }

        .login-card {
            width: 100%;
            max-width: 420px;
            padding: 40px;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 35px rgba(0,0,0,0.2);
        }

        .login-title {
            text-align: center;
            font-weight: bold;
            margin-bottom: 10px;
            color: #333;
        }

        .login-subtitle {
            text-align: center;
            color: #888;
            margin-bottom: 30px;
        }

        .form-control {
            padding: 12px;
            border-radius: 8px;
        }

        .btn-login {
            background: #667eea;
            color: white;
            padding: 12px;
            border-radius: 8px;
            border: none;
            width: 100%;
            font-weight: bold;
        }

        .btn-login:hover {
            background: #5365d5;
            color: white;
        }
    </style>
</head>

<body>

<div class="login-card">

    <h2 class="login-title">Bienvenue</h2>

    <p class="login-subtitle">
        Connectez-vous à votre compte
    </p>

    <% if (request.getAttribute("erreur") != null) { %>

        <div class="alert alert-danger">
            <%= request.getAttribute("erreur") %>
        </div>

    <% } %>

    <form action="${pageContext.request.contextPath}/login"
          method="POST">

        <div class="mb-3">

            <label class="form-label">Adresse email</label>

            <input type="email"
                   name="email"
                   class="form-control"
                   placeholder="exemple@gmail.com"
                   value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                   required>

        </div>

        <div class="mb-4">

            <label class="form-label">Mot de passe</label>

            <input type="password"
                   name="mdp"
                   class="form-control"
                   placeholder="Votre mot de passe"
                   required>

        </div>

        <button type="submit" class="btn btn-login">
            Se connecter
        </button>

    </form>

</div>

</body>
</html>
