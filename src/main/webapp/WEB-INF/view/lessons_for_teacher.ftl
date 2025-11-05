<#include "base.ftl">

<#macro title>Выставление баллов</#macro>

<#macro content>
    <div class="container mt-4">
        <h2>Выставление баллов</h2>
        <form method="post" action="${contextPath}/teacher/lessons">+
            <#list lessons as lesson>
                <br>
                <label for="lesson_name" class="form-label">${lesson.group} ${lesson.lessonName}</label>
                <button type="submit" class="btn btn-primary" name="submit" value="edit_scores_${lesson.id}">
                    Выставить баллы
                </button>
                <br>
            </#list>
        </form>
    </div>
</#macro>