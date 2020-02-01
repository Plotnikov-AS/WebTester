<#import "parts/page.ftlh" as p>

<@p.page>
    <div>Список вопросов</div>
    <#list questions as question>
        <div>
            ${question.id}
            ${question.question}
            ${question.answer}
        </div>
    </#list>
</@p.page>