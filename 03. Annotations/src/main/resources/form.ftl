<form method="${method}" action="${action}">
    <#list fields as field>
    <label>
        <input type="${field.type}" name="${field.name}" placeholder="${field.placeholder}">
    </label>
    </#list>
</form>