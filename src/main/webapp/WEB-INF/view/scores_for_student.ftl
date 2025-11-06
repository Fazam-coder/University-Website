<#include "base.ftl">

<#macro title>Мои оценки и статистика</#macro>

<#macro content>
    <div class="container mt-4">
        <h2>Мои оценки и статистика</h2>
        <ul class="nav nav-tabs" id="scoresTab" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" data-tab="my-scores" type="button">Мои оценки</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" data-tab="group-rank" type="button">Место в группе</button>
            </li>
        </ul>
        <div class="tab-content" id="scoresTabContent">

            <div class="tab-pane fade show active" id="my-scores" role="tabpanel">
                <h3 class="mt-4">Мои оценки</h3>

                <#if myScores?has_content>
                    <#assign scoresWithGrades = myScores?filter(s -> s.score??)>
                    <#assign scoresWithoutGrades = myScores?filter(s -> !(s.score??))>

                    <#if scoresWithGrades?has_content>
                        <h4>С оценками</h4>
                        <table class="table table-striped table-bordered">
                            <thead class="table-light">
                            <tr>
                                <th>Предмет</th>
                                <th>Балл</th>
                                <th>Преподаватель</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list scoresWithGrades as score>
                                <tr>
                                    <td>${score.lesson.lessonName!""}</td>
                                    <td>${score.score!""}</td>
                                    <td>${score.lesson.teacherName!""}</td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <div class="alert alert-info">
                            Нет предметов с оценками.
                        </div>
                    </#if>

                    <#if scoresWithoutGrades?has_content>
                        <h4>Без оценок</h4>
                        <table class="table table-striped table-bordered">
                            <thead class="table-light">
                            <tr>
                                <th>Предмет</th>
                                <th>Преподаватель</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list scoresWithoutGrades as score>
                                <tr>
                                    <td>${score.lesson.lessonName!""}</td>
                                    <td>${score.lesson.teacherName!""}</td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <div class="alert alert-info">
                            Нет предметов без оценок.
                        </div>
                    </#if>

                <#else>
                    <div class="alert alert-info">
                        У вас пока нет оценок.
                    </div>
                </#if>
            </div>

            <div class="tab-pane fade" id="group-rank" role="tabpanel">
                <h3 class="mt-4">Место в группе</h3>

                <#if lessons?has_content>
                    <form method="get" action="${contextPath}/student/lessons_and_scores" id="lessonForm">
                        <input type="hidden" name="tab" id="currentTab" value="group-rank">
                        <div class="mb-3">
                            <label for="lessonId" class="form-label">Выберите предмет:</label>
                            <select name="lessonId" id="lessonId" class="form-select" onchange="this.form.submit()">
                                <option value="">-- Выберите предмет --</option>
                                <#list lessons as lesson>
                                    <option value="${lesson.id}" <#if selectedLessonId?? && selectedLessonId == lesson.id>selected</#if>>
                                        ${lesson.lessonName} (${lesson.group})
                                    </option>
                                </#list>
                            </select>
                        </div>
                    </form>

                    <#if selectedLessonId??>
                        <#if scores?has_content>
                            <h4 class="mt-4">Статистика по предмету: <strong>${selectedLesson.lessonName!""}</strong> (${selectedLesson.group!""})</h4>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title">Ваша оценка</h5>
                                            <p class="card-text display-4 text-primary">
                                                <#if myScore??>
                                                    ${myScore}
                                                <#else>
                                                    —
                                                </#if>
                                            </p>
                                            <p class="text-muted">Максимум: 50</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title">Ваше место в группе</h5>
                                            <p class="card-text display-4 text-success">
                                                <#if myRank??>
                                                    ${myRank}
                                                <#else>
                                                    —
                                                </#if>
                                            </p>
                                            <p class="text-muted">из ${totalStudentsInGroup} студентов</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <h4 class="mt-4">Таблица оценок в группе</h4>
                            <table class="table table-striped table-bordered">
                                <thead class="table-light">
                                <tr>
                                    <th>Место</th>
                                    <th>ФИО</th>
                                    <th>Оценка</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list scores as score>
                                    <tr <#if score.studentName == currentStudentName>class="table-primary"</#if>>
                                        <td>${score.rank}</td>
                                        <td>${score.studentName}</td>
                                        <td>${score.score!"—"}</td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>

                        <#else>
                            <div class="alert alert-info">
                                Нет оценок по этому предмету.
                            </div>
                        </#if>
                    <#else>
                        <div class="alert alert-info">
                            Выберите предмет, чтобы увидеть статистику.
                        </div>
                    </#if>
                <#else>
                    <div class="alert alert-warning">
                        Нет доступных предметов.
                    </div>
                </#if>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const urlParams = new URLSearchParams(window.location.search);
            const activeTab = urlParams.get('tab') || 'my-scores';

            const activeButton = document.querySelector('button[data-tab="' + activeTab + '"]');

            if (activeButton) {
                document.querySelectorAll('button[data-tab]').forEach(btn => {
                    btn.classList.remove('active');
                });
                document.querySelectorAll('.tab-pane').forEach(pane => {
                    pane.classList.remove('active', 'show');
                });
                activeButton.classList.add('active');
                const targetPane = document.getElementById(activeButton.getAttribute('data-tab'));
                if (targetPane) {
                    targetPane.classList.add('active', 'show');
                }
            }
        });
    </script>

    <script src="${contextPath}/js/users.js"></script>
</#macro>