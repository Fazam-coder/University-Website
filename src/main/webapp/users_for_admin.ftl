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
        </ul>

        <div class="tab-content mt-3" id="userTabsContent">

            <div class="tab-pane fade show active" id="non-role" role="tabpanel">
                    <form method="post">
                        <#if nonRoles?has_content>
                        <table class="table table-striped table-bordered">
                            <thead class="table-light">
                            <tr>
                                <th>ФИО</th>
                                <th>Email</th>
                                <th>Фотография</th>
<#--                                <th>Ссылка</th>-->
                            </tr>
                            </thead>
                            <tbody>
                            <#list nonRoles as nonRole>
                                <tr>
                                    <td>${nonRole.name!""}</td>
                                    <td>${nonRole.login!""}</td>
                                    <td><img src="${nonRole.imagePath!""}"></td>
<#--                                    <td><a href="profile?id=${nonRole.id}">Ссылка</a></td>-->
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                        <button type="submit" name="submit" value="save" class="btn btn-primary me-2">Сохранить</button>
                        <#else>
                            <p class="text-muted">Нет пользователей без ролей</p>
                        </#if>
                    </form>
            </div>

            <div class="tab-pane fade" id="all" role="tabpanel">
                <#if allUsers?has_content>
                    <table class="table table-striped table-bordered">
                        <thead class="table-light">
                        <tr>
                            <th>ФИО</th>
                            <th>Email</th>
                            <th>Фотография</th>
<#--                            <th>Ссылка</th>-->
                            <th>Роль</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list allUsers as user>
                            <tr>
                                <td>${user.name!""}</td>
                                <td>${user.login!""}</td>
                                <td><img src="${user.imagePath!""}"></td>
<#--                                <td><a href="profile?id=${user.id}">Ссылка</a></td>-->
                                <td>${user.role!""}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                <#else>
                    <p class="text-muted">Нет пользователей</p>
                </#if>
            </div>
        </div>
    </div>
    <script src="${contextPath}/js/users.js"></script>

</#macro>