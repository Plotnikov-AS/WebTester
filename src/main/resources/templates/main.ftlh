<#import "parts/page.ftlh" as p>

<@p.page>
    <div>Список вопросов</div>
    <#list questions as question>
        <div>
            ${question.id}
            ${question.question}
        </div>
    </#list>
    <a href="/test/1">Start test</a>
</@p.page>