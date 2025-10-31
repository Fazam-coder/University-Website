<#include "base.ftl">

<#macro title>Управление пользователями</#macro>

<#macro content>
    <div class="container mt-4">
        <h2>Управление пользователями</h2>

        <ul class="nav nav-tabs" id="userTabs" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="non-role-tab" data-tab="non-role" type="button" role="tab">
                    Пользователи без роли
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="all-tab" data-tab="all" type="button" role="tab">
                    Все пользователи
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="students-tab" data-tab="students" type="button" role="tab">
                    Студенты (для добавления в группы)
                </button>
            </li>
        </ul>

        <div class="tab-content mt-3" id="userTabsContent">

            <div class="tab-pane fade show active" id="non-role" role="tabpanel">
                    <form method="post" action="${contextPath}/admin/users">
                        <#if nonRoles?has_content>
                        <table class="table table-striped table-bordered">
                            <thead class="table-light">
                            <tr>
                                <th>ФИО</th>
                                <th>Email</th>
                                <th>Ссылка</th>
                                <th>Роль</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list nonRoles as nonRole>
                                <tr>
                                    <td>${nonRole.name!""}</td>
                                    <td>${nonRole.login!""}</td>
                                    <td><a href="${contextPath}/profile?id=${nonRole.id}">Ссылка</a></td>
                                    <td>
                                        <select name="role_${nonRole.id}" class="form-select form-select-sm">
                                            <option value="USER" selected>USER</option>
                                            <option value="STUDENT">STUDENT</option>
                                            <option value="TEACHER">TEACHER</option>
                                        </select>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                        <button type="submit" name="submit" value="save_roles" class="btn btn-primary me-2">Сохранить</button>
                        <#else>
                            <p class="text-muted">Нет пользователей без ролей</p>
                        </#if>
                    </form>
            </div>

            <div class="tab-pane fade" id="all" role="tabpanel">
                <#if allUsers?has_content>
                    <form method="post" action="${contextPath}/admin/users">
                        <table class="table table-striped table-bordered">
                            <thead class="table-light">
                            <tr>
                                <th>ФИО</th>
                                <th>Email</th>
                                <th>Ссылка</th>
                                <th>Роль</th>
                                <th>Удаление</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list allUsers as user>
                                <tr>
                                    <td>${user.name!""}</td>
                                    <td>${user.login!""}</td>
                                    <td><a href="${contextPath}/profile?id=${user.id}">Ссылка</a></td>
                                    <td>${user.role!""}</td>
                                    <td><button type="submit" name="submit" value="delete_${user.id}" class="btn btn-outline-danger">Удалить</button> </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </form>
                <#else>
                    <p class="text-muted">Нет пользователей</p>
                </#if>
            </div>

            <div class="tab-pane fade" id="students" role="tabpanel">
                <form method="post" action="${contextPath}/admin/users">
                    <#if students?has_content>
                        <table class="table table-striped table-bordered">
                            <thead class="table-light">
                            <tr>
                                <th>ФИО</th>
                                <th>Email</th>
                                <th>Ссылка</th>
                                <th>Группа</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list students as student>
                                <tr>
                                    <td>${student.name!""}</td>
                                    <td>${student.login!""}</td>
                                    <td><a href="${contextPath}/profile?id=${student.id}">Ссылка</a></td>
                                    <td>
                                        <input type="text" class="form-control" name="group_${student.id}" value="${student.group!""}">
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                        <button type="submit" name="submit" value="save_groups" class="btn btn-primary me-2">Сохранить</button>
                    <#else>
                        <p class="text-muted">Нет студентов без групп</p>
                    </#if>
                </form>
            </div>
        </div>
    </div>
    <script src="${contextPath}/js/users.js"></script>

</#macro>