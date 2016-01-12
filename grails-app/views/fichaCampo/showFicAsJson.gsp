<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Ficha / JSON</title>
    <link type="text/css" href="${resource(dir: 'menu', file: 'menu.css')}" rel="stylesheet" />
    <script type="text/javascript" src="${resource(dir: 'menu', file: 'menu.js')}"></script>
</head>
<body>
    <div id="menu">
        <ul class="menu">
            <li><a href="${createLink(uri: '/')}"><span>Principal</span></a></li>
            <li><g:link class="create" action="create"><span>Crear Ficha</span></g:link></li>
            <li><g:link class="list" action="index"><span>Fichas de Investigación de Campo</span></g:link></li>
            <li><g:link class="list" action="show" id="${id}"><span>Ficha !</span></g:link></li>
        </ul>
    </div>
    <pre>
    ${ficAsJson}
    </pre>
</body>
</html>
