<#-- @ftlvariable name="data" type="ru.itis.javalab.queue.chain.dto.RequestDto" -->
<html lang="ru">
<head>
    <title>Справка</title>
</head>
<body>
<main>
    <div style="text-align: center;">
        <h1>Справка</h1>
    </div>
    <div>
        Выдана <b>${(data.sex.name() == "MALE")?then("мужчине", "женщине")}</b>,
        вес <i>${data.weight}</i>, рост <i>${data.height}</i>.
    </div>
</main>
</body>
</html>