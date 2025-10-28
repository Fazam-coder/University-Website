<html lang="en">
<#include "base.ftl">

<#macro title>Sign Up</#macro>

<script>
    $(document).on("input", "#login", function () {
    var formData = {
        name: $('#name').val(),
        login: $('#login').val(),
        password: $('#password').val(),
    };

    $.get("check-login?login=" + formData.login.trim(), function (response) {
        if (response === "true") {
            $("#loginExists").text("Логин занят")
            $('#submitBtn').prop('disabled', true);
        } else {
            $("#loginExists").text("")
            $('#submitBtn').prop('disabled', false);
        }
    })

    });</script>

<#macro content>

    <form method="post" action="sign_up" class="container mt-4" style="max-width: 500px;" id="registrationForm">
        <h3 class="mb-4">Регистрация</h3>

        <div class="mb-3">
            <label for="name" class="form-label">Ваше ФИО:</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Введите ваше полное имя" required>
        </div>

        <div class="mb-3">
            <label for="login" class="form-label">Email:</label>
            <input type="email" class="form-control" id="login" name="login"
                   placeholder="Должен оканчиваться на @kpfu.ru" required>
            <div class="form-text">Используйте корпоративную почту @kpfu.ru</div>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password"
                   placeholder="Создайте надежный пароль" required minlength="6">
        </div>

        <div class="mb-3">
            <span id="loginStatus" class="form-text"></span>
        </div>
        <div class="mb-3">
            <span id="loginExists" class="form-text text-danger"></span>
        </div>
        <div class="mb-3">
            <span id="passwordStatus" class="form-text"></span>
        </div>

        <button type="submit" id="submitBtn" class="btn btn-primary w-100">Зарегистрироваться</button>
    </form>

    <script src="${contextPath}/js/sign_up_validation.js"></script>

</#macro>


</html>