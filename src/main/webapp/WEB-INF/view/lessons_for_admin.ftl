<#include "base.ftl">

<#macro title>Предметы</#macro>

<#macro content>
    <form method="post" action="${contextPath}/admin/lessons">
        <button type="submit" value="add_lesson" name="submit" class="btn btn-primary me-2">Добавить предмет</button>
        <br>
        <#if lessons?has_content>
            <table class="table table-striped table-bordered">
                <thead class="table-light">
                <tr>
                    <th>Группа</th>
                    <th>Предмет</th>
                    <th>Преподаватель</th>
                    <th>Удаление</th>
                </tr>
                </thead>
                <tbody>
                <#list lessons as lesson>
                    <tr>
                        <td>
                            <input type="text" class="form-control lesson-group"
                                   data-id="${lesson.id}"
                                   name="group_${lesson.id}"
                                   value="${lesson.group!""}"
                                   required>
                            <div class="text-danger validation-msg-group" data-id="${lesson.id}"></div>
                        </td>
                        <td><input type="text" class="form-control" name="name_${lesson.id}" value="${lesson.lessonName!""}" required></td>
                        <td>
                            <input type="text" class="form-control lesson-teacher"
                                   data-id="${lesson.id}"
                                   name="teacher_${lesson.id}"
                                   value="${lesson.teacherName!""}"
                                   required>
                            <div class="text-danger validation-msg-teacher" data-id="${lesson.id}"></div>
                        </td>
                        <td><button type="submit" name="submit" value="delete_${lesson.id}" class="btn btn-outline-danger">Удалить</button></td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <button type="submit" name="submit" id="submitBtn" value="save" class="btn btn-primary me-2">Сохранить изменения</button>
        <#else>
            <p class="text-muted">Нет предметов для отображения.</p>
        </#if>
    </form>
    <script>
        const contextPath = "${contextPath}";
    </script>
    <script src="${contextPath}/js/check_lesson_valid.js"></script>

</#macro>