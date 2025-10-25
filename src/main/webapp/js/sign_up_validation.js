document.addEventListener('DOMContentLoaded', function() {
    const loginInput = document.getElementById('login');
    const passwordInput = document.getElementById('password');
    const loginStatus = document.getElementById('loginStatus');
    const passwordStatus = document.getElementById('passwordStatus');
    const submitBtn = document.getElementById('submitBtn');
    const registrationForm = document.getElementById('registrationForm');

    let isEmailValid = false;
    let isPasswordValid = false;

    if (loginInput && loginStatus) {
        loginInput.addEventListener('input', function() {
            const email = this.value;
            isEmailValid = email.endsWith('@kpfu.ru');

            loginStatus.textContent = isEmailValid ? 'Корпоративный email подтвержден' : 'Используйте email с доменом @kpfu.ru';
            loginStatus.className = isEmailValid ? 'form-text text-success' : 'form-text text-danger';

            updateSubmitButton();
        });
    }

    if (passwordInput && passwordStatus) {
        passwordInput.addEventListener('input', function() {
            const password = this.value;
            isPasswordValid = password.length >= 8;

            passwordStatus.textContent = isPasswordValid ? 'Пароль соответствует требованиям' : 'Пароль должен содержать не менее 8 символов';
            passwordStatus.className = isPasswordValid ? 'form-text text-success' : 'form-text text-danger';

            updateSubmitButton();
        });
    }

    function updateSubmitButton() {
        if (submitBtn) {
            submitBtn.disabled = !(isEmailValid && isPasswordValid);
        }
    }

    if (registrationForm) {
        registrationForm.addEventListener('submit', function(e) {
            if (!isEmailValid || !isPasswordValid) {
                e.preventDefault();
                alert('Пожалуйста, исправьте ошибки в форме перед отправкой');
            }
        });
    }
});