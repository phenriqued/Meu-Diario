
//Mostrar a senha
const senhaInput = document.getElementById('password');
const mostrarSenhaButton = document.querySelector('.password-toggle');

const passwordInputsGroups = document.querySelectorAll('.form-group.password-input');
passwordInputsGroups.forEach(group => {
    const senhaInput = group.querySelector('input[type="password"]'); // Pega o input dentro deste grupo
    const mostrarSenhaButton = group.querySelector('.password-toggle'); // Pega o botão dentro deste grupo

    if (senhaInput && mostrarSenhaButton) {
        mostrarSenhaButton.addEventListener('click', function() {
            if (senhaInput.type === 'password') {
                senhaInput.type = 'text';
                mostrarSenhaButton.innerHTML = '<img src="/image/Login/eye-icon.png" alt="Ocultar senha">';
            } else {
                senhaInput.type = 'password';
                mostrarSenhaButton.innerHTML = '<img src="/image/Login/eye-off-icon.png" alt="Mostrar senha">';
            }
        });
    }
});

// formatar número de celular
const celularInput = document.getElementById('phoneNumber');
const celularConfirmationInput = document.getElementById('phoneNumberConfirmation');
celularInput.addEventListener('input', formatarCelular);

function formatarCelular(event) {
    let numero = event.target.value.replace(/\D/g, '');
    numero = numero.substring(0, 11);
    let numeroFormatado = '';
    if (numero.length > 0) {
        numeroFormatado = `(${numero.substring(0, 2)}`;
        if (numero.length > 2) {
            numeroFormatado += `) ${numero.substring(2, 7)}`;
            if (numero.length > 7) {
                numeroFormatado += `-${numero.substring(7, 11)}`;
            }
        }
    }
    event.target.value = numeroFormatado;
}
if (celularInput) {
    celularInput.addEventListener('input', formatarCelular);
}
if (celularConfirmationInput) {
    celularConfirmationInput.addEventListener('input', formatarCelular);
}