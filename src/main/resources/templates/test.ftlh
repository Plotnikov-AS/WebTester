<#import "parts/page.ftlh" as p>

<@p.page>
    Page with test<br>
    ${question.question}<br>
    <form action="/test/${questionId}" method="post">
        <#if question.selectable>
            <#list answerOptions as answerOption>
                <label><input type="checkbox" name="answer" value="${answerOption}">${answerOption}</label><br>
            </#list>
        <#else>
            <input type="text" name="answer" placeholder="Введите ответ"><br>
        </#if>
        <button type="submit" name="answer">Send</button>
    </form>
</@p.page>