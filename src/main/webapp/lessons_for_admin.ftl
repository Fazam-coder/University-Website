<#include "base.ftl">

<#macro title>Предметы</#macro>

<#macro content>
    <form method="post" action="${contextPath}/admin/lessons">
        <button type="submit" value="add_lesson">Добавить предмет</button>
        <#if lessons?has_content>
            <table class="table table-striped table-bordered">
                <thead class="table-light">
                <tr>
                    <th>Группа</th>
                    <th>Предмет</th>
                    <th>Преподаватель</th>
                </tr>
                </thead>
                <tbody>
                <#list lessons as lesson>
                    <tr>
                        <td><input type="text" class="form-control" name="group_${lesson.id}" value="${lesson.group!""}"></td>
                        <td><input type="text" class="form-control" name="name_${lesson.id}" value="${lesson.lessonName!""}"></td>
                        <td><input type="text" class="form-control" name="teacher_${lesson.id}" value="${lesson.teacherName!""}"></td>
                        <td><button type="submit" name="submit" value="delete_${lesson.id}" class="btn btn-outline-danger">Удалить</button></td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <button type="submit" value="save">Сохранить изменения</button>
        <#else>
            <p class="text-muted">Нет студентов для отображения.</p>
        </#if>
    </form>

</#macro>