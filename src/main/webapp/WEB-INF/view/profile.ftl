<#include "base.ftl">

<#macro title>Профиль пользователя</#macro>

<#macro content>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-4 text-center">
                <#if user.imagePath??>
                    <img src="${user.imagePath}" alt="Фото профиля" class="img-fluid rounded shadow" style="max-width: 100%; height: auto; max-height: 300px;">
                <#else>
                    <div class="bg-light border rounded d-flex align-items-center justify-content-center" style="width: 100%; height: 300px;">
                        <span class="text-muted">Нет фото</span>
                    </div>
                </#if>
            </div>

            <div class="col-md-8">
                <h2>${user.name!""}</h2>
                <hr>

                <div class="mb-3">
                    <strong>Роль:</strong>
                    <span class="badge bg-secondary">
                    <#switch user.role>
                        <#case "USER">Пользователь<#break>
                        <#case "STUDENT">Студент<#break>
                        <#case "TEACHER">Преподаватель<#break>
                        <#default>—
                    </#switch>
                    </span>
                    <#if user.role == "STUDENT">
                        <strong>Группа:</strong>
                        <span class="badge bg-secondary">${group!""}</span>
                    </#if>
                </div>

                <div class="mb-3">
                    <strong>Email:</strong> ${user.login!""}
                </div>

                <div class="mb-3">
                    <strong>О себе:</strong>
                    <#if user.aboutInfo??>
                        <p>${user.aboutInfo}</p>
                    <#else>
                        <p class="text-muted">Информация отсутствует</p>
                    </#if>
                </div>
                <#if is_can_edit == "true">
                    <div class="mt-4">
                        <a href="${contextPath}/profile/edit?id=${user.id}" class="btn btn-outline-primary">
                            <i class="fas fa-edit"></i> Редактировать профиль
                        </a>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</#macro>