<#include "base.ftl">

<#macro title>Пользователи</#macro>

<#macro content>
    <div class="container mt-4">
        <h2>Все пользователи</h2>

        <ul class="nav nav-tabs" id="userTabs" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="students-tab" data-tab="students" type="button" role="tab">
                    Студенты
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="teachers-tab" data-tab="teachers" type="button" role="tab">
                    Преподаватели
                </button>
            </li>
        </ul>

        <div class="tab-content mt-3" id="userTabsContent">

            <div class="tab-pane fade show active" id="students" role="tabpanel">
                <#if students?has_content>
                    <table class="table table-striped table-bordered">
                        <thead class="table-light">
                        <tr>
                            <th>ФИО</th>
                            <th>Группа</th>
                            <th>Email</th>
                            <th>Фотография</th>
                            <th>Ссылка</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list students as student>
                            <tr>
                                <td>${student.name!""}</td>
                                <td>${student.group!""}</td>
                                <td>${student.login!""}</td>
                                <td><img src="${student.imagePath!""}"></td>
                                <td><a href="profile?id=${student.id}">Ссылка</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                <#else>
                    <p class="text-muted">Нет студентов для отображения.</p>
                </#if>
            </div>

            <div class="tab-pane fade" id="teachers" role="tabpanel">
                <#if teachers?has_content>
                    <table class="table table-striped table-bordered">
                        <thead class="table-light">
                        <tr>
                            <th>ФИО</th>
                            <th>Email</th>
                            <th>Фотография</th>
                            <th>Ссылка</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list teachers as teacher>
                            <tr>
                                <td>${teacher.name!""}</td>
                                <td>${teacher.login!""}</td>
                                <td><img src="${teacher.imagePath!""}"></td>
                                <td><a href="profile?id=${teacher.id}">Ссылка</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                <#else>
                    <p class="text-muted">Нет преподавателей для отображения.</p>
                </#if>
            </div>
        </div>
    </div>
    <script src="js/users.js"></script>

</#macro>