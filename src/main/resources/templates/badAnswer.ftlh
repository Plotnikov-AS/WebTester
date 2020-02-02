<#import "parts/page.ftlh" as p>

<@p.page>
    Bad answer...<br>
    <#if nextQuestion??>
        <a href="/test/${nextQuestion.id}">Next question</a>
    <#else>
        Test is finished.<br>
        <a href="/main">Main page</a>
    </#if>
</@p.page>