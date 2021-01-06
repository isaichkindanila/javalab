<#-- @ftlvariable name="data" type="ru.itis.javalab.queue.chain.dto.RequestDto" -->
<html lang="ru">
<head>
    <title>Документ</title>
</head>
<body>
<main>
    <div style="text-align: center;">
        <h1>Документ</h1>
    </div>
    <div>
        Выдан <b>${(data.sex.name() == "MALE")?then("мужчине", "женщине")}</b>,
        рост <i>${data.height}</i>, вес <i>${data.weight}</i>.
    </div>
</main>
</body>
</html>