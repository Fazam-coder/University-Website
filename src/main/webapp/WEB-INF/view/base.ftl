<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #header {
            padding: 0.75rem 1rem;
            background-color: #f8f9fa;
            border-bottom: 1px solid #dee2e6;
        }
        #header h3 {
            margin-bottom: 0;
            color: #0d6efd;
            text-decoration: none;
        }
        #header h3:hover {
            color: #0a58ca;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div id="header" class="d-flex align-items-center">
    <div class="me-auto">
        <a href="${contextPath}/users" class="h3 text-decoration-none">
            <h3 class="mb-0">КФУ ИТИС</h3>
        </a>
    </div>

    <div class="ms-auto d-flex gap-2">
        <a href="${contextPath}/profile" class="btn btn-outline-secondary">Профиль</a>
        <a href="${contextPath}/logout" class="btn btn-outline-danger">Выйти</a>
    </div>
</div>

<div id="content" class="container mt-4">
    <@content></@content>
</div>

</body>
</html>