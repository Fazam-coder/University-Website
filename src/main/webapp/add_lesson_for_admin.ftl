<#include "base.ftl">

<#macro title>Добавление предмета</#macro>

<#macro content>
    <div class="container mt-4">
        <h2>Добавить предмет</h2>
        <form method="post" action="${contextPath}/admin/add_lesson" id="addLessonForm">
            <div class="mb-3">
                <label for="group" class="form-label">Группа</label>
                <input type="text" class="form-control lesson-group" id="group" name="group" required>
                <div class="text-danger" id="group-error"></div>
            </div>

            <div class="mb-3">
                <label for="lesson_name" class="form-label">Название предмета</label>
                <input type="text" class="form-control" id="lesson_name" name="lesson_name" required>
                <div class="text-danger" id="lesson-error"></div>
            </div>

            <div class="mb-3">
                <label for="teacher_name" class="form-label">ФИО преподавателя</label>
                <input type="text" class="form-control lesson-teacher" id="teacher_name" name="teacher_name" required>
                <div class="text-danger" id="teacher-error"></div>
            </div>

            <div class="mb-3 text-danger" id="duplicate-error"></div>

            <button type="submit" id="submitBtn" class="btn btn-primary">Сохранить</button>
        </form>
    </div>

    <script>
        const contextPath = "${contextPath}";
    </script>
    <script src="${contextPath}/js/check_lesson_valid.js"></script>

    <script>
        $(document).ready(function () {
            $('#group, #lesson_name, #teacher_name').on('input', function () {
                const group = $('#group').val().trim();
                const lessonName = $('#lesson_name').val().trim();
                const teacher = $('#teacher_name').val().trim();

                $('#duplicate-error').text('');

                if (group && lessonName && teacher) {
                    $.get(contextPath + '/check_lesson_exist', {
                        group: group,
                        lesson_name: lessonName,
                        teacher: teacher
                    }).done(function (response) {
                        const msg = response.trim();
                        if (msg) {
                            $('#duplicate-error').text(msg);
                            $('#submitBtn').prop('disabled', true);
                        } else {
                            $('#submitBtn').prop('disabled', false);
                        }
                    });
                }
            });
        });
    </script>
</#macro>