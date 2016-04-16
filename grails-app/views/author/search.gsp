<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>
    <g:each in="${result}" var="author">
        <p>${author.name}</p>
    </g:each>
</body>
</html>