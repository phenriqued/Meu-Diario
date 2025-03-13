
//Mostrar a senha
const senhaInput = document.getElementById('senha');
const mostrarSenhaButton = document.querySelector('.password-toggle');

mostrarSenhaButton.addEventListener('click', function() {
    if (senhaInput.type === 'password') {
        senhaInput.type = 'text';
        mostrarSenhaButton.innerHTML = '<img src="/image/Login/eye-icon.png" alt="Ocultar senha">';
    } else {
        senhaInput.type = 'password';
        mostrarSenhaButton.innerHTML = '<img src="/image/Login/eye-off-icon.png" alt="Mostrar senha">';
    }
});

// formatar número de celular
const celularInput = document.getElementById('phone');

celularInput.addEventListener('input', formatarCelular);

function formatarCelular(event) {
    let numero = event.target.value.replace(/\D/g, ''); // Remove caracteres não numéricos
    numero = numero.substring(0, 11); // Limita o número a 11 dígitos

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