document.addEventListener('DOMContentLoaded', function () {
    const tabButtons = document.querySelectorAll('[data-tab]');

    tabButtons.forEach(button => {
        button.addEventListener('click', function () {
            const tabName = this.getAttribute('data-tab')

            // Убираем active у всех кнопок
            tabButtons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');

            // Скрываем все панели: убираем active и show
            document.querySelectorAll('.tab-pane').forEach(pane => {
                pane.classList.remove('active', 'show');
            });

            // Показываем нужную панель: добавляем active и show
            const targetPane = document.getElementById(tabName);
            if (targetPane) {
                targetPane.classList.add('active', 'show');
            }
        });
    });
});