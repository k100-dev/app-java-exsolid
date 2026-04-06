# 📊 Relatório de Teste de Carga – App Livraria

## 📌 Contexto
Foi realizado um teste de carga utilizando a ferramenta **Locust** com o objetivo de simular múltiplos usuários acessando a API do sistema **App Livraria**.

O teste teve como foco avaliar o comportamento da aplicação sob concorrência, medindo **tempo de resposta**, **taxa de falhas**, **requisições por segundo (RPS)** e **estabilidade geral da API**.

---

## 🧪 Metodologia
Durante a execução do teste, foram simuladas operações principais da API relacionadas ao gerenciamento de livros, incluindo:

- **Criação de livros (POST)**
- **Consulta de livros (GET)**

Além disso, o cenário foi preparado para utilização com **autenticação via API Key**, conforme a arquitetura definida para a aplicação.

A ferramenta Locust foi executada localmente, com monitoramento em tempo real via interface web.

---

## ⚙️ Configuração do Teste
A proposta inicial do cenário de carga foi estruturada em três níveis:

- **10 usuários** → carga leve  
- **50 usuários** → carga média  
- **100 usuários** → carga alta  

No entanto, com base nas evidências coletadas nos prints do Locust, a execução registrada neste relatório apresentou o seguinte cenário efetivamente validado:

- **Usuários simultâneos ativos:** **10**
- **Spawn rate:** **2 usuários por segundo**
- **Host configurado:** `http://localhost`
- **Operação principal executada:** `POST /livros`

---

## 📈 Resultados Obtidos
Com base nos dados coletados diretamente da interface do Locust, foram observados os seguintes resultados:

### Estatísticas da operação `POST /livros`
- **Total de requisições:** **101**
- **Total de falhas:** **101**
- **Taxa de falha:** **100%**
- **Current RPS:** **1.6**
- **Current Failures/s:** **1.6**

### Tempos de resposta
- **Tempo mediano:** **4100 ms**
- **95º percentil:** **4100 ms**
- **99º percentil:** **4100 ms**
- **Tempo médio:** **4088,16 ms**
- **Tempo mínimo:** **4074 ms**
- **Tempo máximo:** **4106 ms**

---

## Análise dos Gráficos

### 1. Requisições por Segundo (RPS)
O gráfico de **Total Requests per Second** indicou uma média de aproximadamente:

- **1,4 a 1,6 requisições por segundo**

Apesar da taxa de envio estar funcionando, todas as requisições executadas resultaram em falha.

### 2. Tempo de Resposta
O gráfico de **Response Times (ms)** mostrou tempos de resposta estáveis, porém elevados, permanecendo próximos de:

- **4 segundos por requisição**

Esse comportamento indica lentidão significativa mesmo em um cenário de carga considerado leve.

### 3. Número de Usuários
O gráfico de **Number of Users** confirmou que a carga foi aplicada corretamente, atingindo:

- **10 usuários simultâneos**

Isso demonstra que o Locust executou o cenário conforme esperado, e que o gargalo está relacionado à API testada ou à configuração da operação.

---

## ⚠️ Problemas Observados
Durante a execução do teste, foram identificados os seguintes problemas:

### 1. Falha total das requisições
Todas as chamadas para o endpoint:

```http
POST /livros
```

retornaram falha durante a execução do cenário analisado.

### 2. Alto tempo de resposta
Mesmo com apenas **10 usuários simultâneos**, a aplicação apresentou tempos médios próximos de **4 segundos**, o que é elevado para uma operação simples de criação de recurso.

### 3. Impossibilidade de validação de carga real
Como houve **100% de falha**, não foi possível medir corretamente a capacidade real de escalabilidade da aplicação nesta execução.

---

## Interpretação Técnica dos Resultados
Embora a proposta inicial do teste considerasse que:

- o sistema seria estável até **50 usuários**
- apresentaria aumento de latência em **100 usuários**
- e começaria a degradar acima de **120 usuários**

os dados efetivamente obtidos nesta execução demonstram que a aplicação **não suportou adequadamente nem mesmo o cenário leve de 10 usuários**, ao menos para a operação testada.

Isso sugere que o principal problema neste momento **não está apenas na escalabilidade**, mas possivelmente em fatores como:

- erro funcional no endpoint
- payload incompatível com a API
- falha de autenticação via API Key
- exceções internas no backend
- problemas de persistência ou banco de dados
- erro de mapeamento no controller ou DTO

---

## Evidências de Execução
Os logs exibidos na interface do Locust confirmaram que o ambiente de teste foi iniciado corretamente, com os seguintes eventos:

- Inicialização do Locust com sucesso
- Abertura da interface web local
- Crescimento gradual da carga até **10 usuários**
- Spawn completo dos usuários simulados

Isso indica que a ferramenta de teste funcionou corretamente e que os erros observados estão associados ao comportamento da aplicação testada.

---

## 🧠 Conclusão
Com base nos resultados obtidos, conclui-se que o sistema **App Livraria**, na execução analisada, apresentou **falhas críticas já em baixa concorrência**, especialmente na operação de criação de livros.

Os principais pontos observados foram:

- **100% de falha nas requisições**
- **tempo médio superior a 4 segundos**
- **baixo throughput**
- **ausência de sucesso mesmo com carga leve**

Dessa forma, **não foi possível validar a estabilidade real da aplicação sob carga**, pois o sistema apresentou comportamento inadequado antes mesmo de atingir cenários de média ou alta concorrência.

Apesar disso, o teste foi extremamente importante para identificar gargalos e pontos de correção no sistema.

---

## Melhorias Futuras
Com base nos resultados, recomenda-se a adoção das seguintes melhorias:

### Melhorias técnicas
- Revisar o endpoint `POST /livros`
- Validar o formato do payload enviado
- Confirmar o envio correto da **API Key**
- Verificar logs internos da aplicação backend
- Investigar possíveis erros de banco de dados
- Melhorar o tempo de resposta das operações
- Aplicar otimizações de processamento
- Implementar **cache**, quando aplicável
- Reforçar o tratamento de erros da API

### Melhorias no processo de teste
Após correção funcional da aplicação, recomenda-se executar novamente o teste com os seguintes cenários:

- **10 usuários**
- **50 usuários**
- **100 usuários**

Também é recomendado:

- separar medições por endpoint
- testar individualmente **GET**, **POST**, **PUT** e **DELETE**
- gerar métricas comparativas entre versões da API

---

## Conclusão Final
O teste de carga demonstrou que a aplicação ainda precisa de ajustes importantes antes de ser considerada estável sob concorrência.

A execução permitiu identificar que o problema atual está mais relacionado à **falha operacional da API** do que necessariamente ao limite de escalabilidade da infraestrutura.

Portanto, o próximo passo recomendado é **corrigir a operação testada**, validar o funcionamento em carga leve e, somente após isso, avançar para testes mais robustos com carga média e alta.