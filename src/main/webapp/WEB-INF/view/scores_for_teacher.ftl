<#include "base.ftl">

<#macro title>Выставить баллы</#macro>

<#macro content>
    <div class="container mt-4">
        <#if scores?has_content>
            <h2>Выставить баллы по предмету: <strong>${lesson.lessonName!""} (${lesson.group!""})</strong></h2>
        <#else>
            <h2>Выставить баллы</h2>
            <div class="alert alert-warning">Нет студентов для оценки.</div>
            <a href="${contextPath}/teacher/lessons" class="btn btn-secondary">Назад к предметам</a>
            <#return>
        </#if>

        <form method="post" action="${contextPath}/teacher/scores" id="gradesForm">
            <input type="hidden" name="id" value="${lesson.id}">

            <table class="table table-striped table-bordered">
                <thead class="table-light">
                <tr>
                    <th>ФИО студента</th>
                    <th>Балл</th>
                </tr>
                </thead>
                <tbody>
                <#list scores as score>
                    <tr>
                        <td>${score.studentName!""}</td>
                        <td>
                            <#if score.id != 0>
                            <input type="number"
                                   class="form-control grade-input"
                                   name="score_${score.id}"
                                   value="${score.score!""}"
                                   min="0"
                                   max="50"
                                   placeholder="0-50">
                            <#else>
                                <input type="number"
                                       class="form-control grade-input"
                                       name="score_${score.studentName!""}"
                                       value="${score.score!""}"
                                       min="0"
                                       max="50"
                                       placeholder="0-50">
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>

            <button type="submit" name="submit" id="submitBtn" class="btn btn-success">Сохранить баллы</button>
            <a href="${contextPath}/teacher/lessons" class="btn btn-secondary">Назад к предметам</a>
        </form>
    </div>

    <script>
        const contextPath = "${contextPath}";
    </script>
    <script>
        $(document).ready(function() {
            $('.grade-input').on('input', function() {
                const value = $(this).val();
                const errorEl = $(this).next('.grade-error');
                errorEl.text('');

                if (value !== '' && (isNaN(value) || value < 0 || value > 50)) {
                    errorEl.text('Балл должен быть от 0 до 50');
                    $('#submitBtn').prop('disabled', true);
                } else {
                    let hasInvalid = false;
                    $('.grade-input').each(function() {
                        const val = $(this).val();
                        if (val !== '' && (isNaN(val) || val < 0 || val > 50)) {
                            hasInvalid = true;
                            return false;
                        }
                    });
                    $('#submitBtn').prop('disabled', hasInvalid);
                }
            });
        });
    </script>
</#macro>