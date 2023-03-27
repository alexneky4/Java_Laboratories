<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${catalog.name}</title>
</head>
<body>
<h1>The catalog's name is : ${catalog.name}</h1>
<ul>
    <#list catalog.docs as doc>
        <li>
            <h2>Document title =  ${doc.title} with id  ${doc.id}</h2>
            <p>Location: ${doc.location}</p>
            <ul>
                <#list doc.tags as key, value>
                    <li>Tag ${key}: ${value}</li>
                </#list>
            </ul>
        </li>
    </#list>
</ul>
</body>
</html>