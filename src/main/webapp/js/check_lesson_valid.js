$(document).ready(function () {
    $(document).on('input', '.lesson-group, .lesson-teacher', function () {
        let group, teacher;
        let groupErrorEl, teacherErrorEl;

        if ($(this).closest('#addLessonForm').length) {
            group = $('#group').val()?.trim() || '';
            teacher = $('#teacher_name').val()?.trim() || '';
            groupErrorEl = $('#group-error');
            teacherErrorEl = $('#teacher-error');
        } else {
            const $row = $(this).closest('tr');
            group = $row.find('.lesson-group').val()?.trim() || '';
            teacher = $row.find('.lesson-teacher').val()?.trim() || '';
            groupErrorEl = $row.find('.validation-msg-group');
            teacherErrorEl = $row.find('.validation-msg-teacher');
        }

        groupErrorEl.text('');
        teacherErrorEl.text('');

        $.get(contextPath + '/check_lesson_valid', { group: group, teacher: teacher })
            .done(function (response) {
                const msg = response.trim();
                if (msg !== "Все хорошо") {
                    if ($(this).hasClass('lesson-group') || $(this).attr('id') === 'group') {
                        groupErrorEl.text(msg);
                    } else {
                        teacherErrorEl.text(msg);
                    }
                    $('#submitBtn').prop('disabled', true);
                } else {
                    $('#submitBtn').prop('disabled', false);
                }
            }.bind(this))
            .fail(function () {
                if ($(this).hasClass('lesson-group') || $(this).attr('id') === 'group') {
                    groupErrorEl.text('Ошибка проверки');
                } else {
                    teacherErrorEl.text('Ошибка проверки');
                }
            }.bind(this));
    });
});