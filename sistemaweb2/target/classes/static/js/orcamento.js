document.getElementById('enviarEmailBtn').addEventListener('click', function () {
    // Obtenha os valores do formulário
    const clienteNome = document.querySelector('#cliente option:checked').textContent;
    const clienteEmail = document.getElementById('cliente').value; // ID do cliente
    const descricao = document.getElementById('descricao').value;
    const status = document.querySelector('#status option:checked').textContent;
    const valorTotal = document.getElementById('valorTotal').value;

    if (!clienteEmail || !descricao || !status || !valorTotal) {
        alert('Por favor, preencha todos os campos obrigatórios!');
        return;
    }

    // Crie o objeto de orçamento
    const orcamento = {
        cliente: {
            nome: clienteNome,
            email: clienteEmail,
        },
        descricao: descricao,
        status: status,
        valorTotal: parseFloat(valorTotal),
    };

    // Envie o e-mail usando a função fetch
    fetch('/orcamento/enviar-email', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(orcamento),
    })
        .then(response => response.text())
        .then(data => {
            alert(data); // Mostra a mensagem de confirmação
            // Submete o formulário após o envio do e-mail
            document.querySelector('form').submit();
        })
        .catch(error => {
            alert('Erro ao enviar e-mail: ' + error.message);
        });
});
