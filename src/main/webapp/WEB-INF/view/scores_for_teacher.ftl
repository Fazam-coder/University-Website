<#include "base.ftl">

<#macro title>Выставить баллы</#macro>

<#macro content>
    <div class="container mt-4">
        <#if scores?has_content>
            <#assign lesson = scores[0].lesson>
            <h2>Выставить баллы по предмету: <strong>${lesson.lessonName!""} (${lesson.group!""})</strong></h2>
        <#else>
            <h2>Выставить баллы</h2>
            <div class="alert alert-warning">Нет студентов для оценки.</div>
            <a href="${contextPath}/teacher/lessons" class="btn btn-secondary">Назад к предметам</a>
            <#return>
        </#if>

        <form method="post" action="${contextPath}/teacher/assign_grades" id="gradesForm">
            <input type="hidden" name="lessonId" value="${lesson.id}">

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
                            <input type="number"
                                   class="form-control grade-input"
                                   name="score_${score.id}"
                                   value="${score.score!""}"
                                   min="0"
                                   max="50"
                                   placeholder="0-50">
                            <div class="text-danger grade-error" data-score-id="${score.id}"></div>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>

            <button type="submit" id="submitBtn" class="btn btn-success">Сохранить баллы</button>
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
                const scoreId = $(this).siblings('.grade-error').data('score-id');
                const errorEl = $(`.grade-error[data-score-id="${scoreId}"]`);
                errorEl.text('');

                if (value !== '' && (isNaN(value) || value < 0 || value > 50)) {
                    errorEl.text('Балл должен быть от 0 до 100');
                    $('#submitBtn').prop('disabled', true);
                } else {
                    // Проверяем все поля
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