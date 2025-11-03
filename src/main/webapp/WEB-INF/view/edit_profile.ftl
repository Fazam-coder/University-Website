<#include "base.ftl">

<#macro title>Редактировать профиль</#macro>

<#macro content>
    <div class="container mt-4">
        <h2>Редактировать профиль</h2>
        <form method="post" action="${contextPath}/profile/edit" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${user.id}">

            <div class="mb-3">
                <label for="name" class="form-label">ФИО</label>
                <input type="text" class="form-control" id="name" name="name" value="${user.name!""}" required>
            </div>

            <div class="mb-3">
                <label for="aboutInfo" class="form-label">О себе</label>
                <textarea class="form-control" id="aboutInfo" name="aboutInfo" rows="4">${user.aboutInfo!""}</textarea>
            </div>

            <div class="mb-3">
                <label for="image" class="form-label">Фото профиля</label>
                <input type="file" id="image" name="image">
            </div>

            <button type="submit" class="btn btn-primary">Сохранить</button>
            <a href="${contextPath}/profile?id=${user.id}" class="btn btn-secondary">Отмена</a>
        </form>
    </div>
</#macro>