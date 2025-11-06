<#include "base.ftl">

<#macro title>Одногруппники</#macro>

<#macro content>
    <h2>Мои одногруппники. Группа: <strong>${group}</strong></h2>
    <#if students?has_content>
        <table class="table table-striped table-bordered">
            <thead class="table-light">
            <tr>
                <th>ФИО</th>
                <th>Email</th>
                <th>Ссылка</th>
            </tr>
            </thead>
            <tbody>
            <#list students as student>
                <tr>
                    <td>${student.name!""}</td>
                    <td>${student.login!""}</td>
                    <td><a href="${contextPath}/profile?id=${student.id}">Ссылка</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <div class="alert alert-warning">Нет одногруппников.</div>
    </#if>
</#macro>