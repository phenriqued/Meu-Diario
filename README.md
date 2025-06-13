![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

<h1>My Diary 📝</h1>
<p> 
  O projeto <strong>My Diary</strong> é uma aplicação web Stateful, desenvolvida com o objetivo de fornecer aos usuários uma plataforma pessoal para registrar seus pensamentos e experiências diárias.
  
  <strong>Funcionalidades Principais:</strong>
  <ul>
    <li>Criação de Contas de Usuário: Permite que os usuários criem contas seguras para acessar o diário.</li>
    <li>Registro Diário: Os usuários podem criar, editar e excluir notas diárias, registrando seus pensamentos e eventos. </li>
    <li>Edição Limitada: As notas só podem ser editadas no mesmo dia em que foram criadas, garantindo a integridade do registro diário. </li>
    <li>Registro do dia: Cada nota é registrada com o dia de criação, fornecendo um histórico detalhado.</li>
    <li>Validação de Número: Envio de SMS para verificação de identidade.</li>
  </ul>
</p>
<h3>🛠 Tecnologias </h3>
<p>As seguintes ferramentas foram utilizadas para construir o projeto: 
  <ul>
    <li> <a href= https://spring.io/projects/spring-boot>Java Spring</a> </li> 
    <li> <a href= https://spring.io/projects/spring-security>Spring Security</a> </li> 
    <li><a href= https://dev.mysql.com/doc/>MySQL</a> </li>
    <li><a href= https://docs.docker.com/compose/>Docker</a> </li>
    <li><a href= https://www.thymeleaf.org>Thymeleaf</a> </li>
    <li><a href= https://www.twilio.com/en-us>Twilio API (envio de SMS)</a> </li>
    <li>HTML, CSS, JS</li>
  </ul>
</p>
<h2> 🚀 Começando </h2>
<p> 
Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.
</p>
Antes de começar, certifique-se de ter instalado:
  <ul>
    <li>Git</li>
    <li>Java 17+</li>
    <li>Docker</li>
  </ul>
</p>
<h3>📥 Clonando o Repositório</h3>
Abra o terminal e execute:
<pre><code> git clone https://github.com/phenriqued/Meu-Diario.git </code></pre>
<br>
<strong>⚙️ Configurando o Ambiente:</strong>
<p>
Configure o application.properties com suas credenciais do Twilio:
  <pre><code>twilio.accountSid=SEU_ACCOUNT_SID
twilio.authToken=SEU_AUTH_TOKEN
twilio.phoneNumber=SEU_PHONE_NUMBER
</code></pre>
</p>
<h2>🚧 Status do Projeto</h2>
<p>
✅ Primeira etapa finalizada <br>
🔄 Melhorias futuras planejadas: área de perfil, personalização de layout, exportação de notas.
</p>
