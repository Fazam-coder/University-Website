<html lang="en">
<#include "base.ftl">

<#macro title>Login</#macro>

<#macro content>

    <form method="post" action="/login" class="container mt-4">
        <div class="mb-3">
            <label for="login" class="form-label">Логин или email:</label>
            <input type="text" class="form-control" id="login" name="login" placeholder="Type your login here">
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>

        <div class="mb-3">
            <button type="submit" name="submit" value="login" class="btn btn-primary me-2">Login</button>
            <br>
            <span class="me-2">Вы не зарегистрированы?</span>
            <button type="submit" name="submit" value="Sign Up" class="btn btn-outline-secondary">Sign Up</button>
        </div>
    </form>

</#macro>


</html>