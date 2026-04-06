from locust import User, task, between
import random
import string
import time


class LivroUser(User):

    wait_time = between(1, 2)

    def gerar_nomelivro(self):
        return "Livro" + ''.join(random.choices(string.ascii_letters, k=5))

    @task
    def fluxo(self):
        titulo = self.gerar_nome_livro()

        inicio = time.time()
        time.sleep(random.uniform(0.05, 0.2))
        fim = time.time()

        tempo = (fim - inicio) * 1000

        self.environment.events.request.fire(
            request_type="SIMULADO",
            name="POST /livros",
            response_time=tempo,
            response_length=0,
            exception=None
        )