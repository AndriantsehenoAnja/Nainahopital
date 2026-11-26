
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fiche Patient</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            padding: 30px;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #2563eb;
        }

        .info {
            padding: 12px;
            border-bottom: 1px solid #eee;
        }

        .label {
            font-weight: bold;
            color: #555;
        }

        .value {
            float: right;
            color: #222;
        }

        .back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background: #2563eb;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Fiche du patient</h2>

    <?php if (!empty($patient)): ?>

        <div class="info">
            <span class="label">ID :</span>
            <span class="value"><?= esc($patient['id'] ?? '') ?></span>
        </div>

        <div class="info">
            <span class="label">Nom :</span>
            <span class="value"><?= esc($patient['nom'] ?? '') ?></span>
        </div>

        <div class="info">
            <span class="label">Prénom :</span>
            <span class="value"><?= esc($patient['prenom'] ?? '') ?></span>
        </div>

        <div class="info">
            <span class="label">Email :</span>
            <span class="value"><?= esc($patient['email'] ?? '') ?></span>
        </div>

        <div class="info">
            <span class="label">Téléphone :</span>
            <span class="value"><?= esc($patient['telephone'] ?? '') ?></span>
        </div>

    <?php else: ?>

        <p style="color:red; text-align:center;">
            Aucun patient trouvé.
        </p>

    <?php endif; ?>

    <a href="<?= base_url('/') ?>" class="back">Retour</a>
        
</div>

</body>
</html>
