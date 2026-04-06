from locust import HttpUser, task, between
import random

class LivroUser(HttpUser):
    wait_time = between(1, 3)

    def gerar_nome_livro(self):
        nomes = [
            "Dom Casmurro",
            "O Hobbit",
            "1984",
            "A Revolução dos Bichos",
            "Clean Code",
            "Java para Iniciantes",
            "Python Avançado",
            "Banco de Dados Moderno",
            "Estruturas de Dados",
            "Algoritmos Modernos"
        ]
        return random.choice(nomes) + " " + str(random.randint(1, 10000))

    def gerar_autor(self):
        autores = [
            "Machado de Assis",
            "J.R.R. Tolkien",
            "George Orwell",
            "Robert Martin",
            "Matheus Schneider",
            "Ana Silva",
            "Carlos Souza",
            "João Pedro"
        ]
        return random.choice(autores)

    @task
    def fluxo_completo_livro(self):
        # =========================
        # 1. CRIAR LIVRO (POST)
        # =========================
        payload = {
            "titulo": self.gerar_nome_livro(),
            "autor": self.gerar_autor(),
            "anoPublicacao": random.randint(1950, 2025)
        }

        with self.client.post("/livros", json=payload, catch_response=True, name="POST /livros") as response:
            if response.status_code in [200, 201]:
                response.success()
                try:
                    livro = response.json()
                    livro_id = livro.get("id")
                except:
                    response.failure("Resposta do POST não veio em JSON válido")
                    return
            else:
                response.failure(f"Erro ao criar livro: {response.status_code}")
                return

        # =========================
        # 2. LISTAR LIVROS (GET)
        # =========================
        with self.client.get("/livros", catch_response=True, name="GET /livros") as response:
            if response.status_code == 200:
                response.success()
            else:
                response.failure(f"Erro ao listar livros: {response.status_code}")

        # =========================
        # 3. BUSCAR LIVRO POR ID (GET)
        # =========================
        if livro_id:
            with self.client.get(f"/livros/{livro_id}", catch_response=True, name="GET /livros/{id}") as response:
                if response.status_code == 200:
                    response.success()
                else:
                    response.failure(f"Erro ao buscar livro por ID: {response.status_code}")

        # =========================
        # 4. ATUALIZAR LIVRO (PUT)
        # =========================
        if livro_id:
            payload_update = {
                "titulo": self.gerar_nome_livro() + " - Atualizado",
                "autor": self.gerar_autor(),
                "anoPublicacao": random.randint(1950, 2025)
            }

            with self.client.put(f"/livros/{livro_id}", json=payload_update, catch_response=True, name="PUT /livros/{id}") as response:
                if response.status_code in [200, 204]:
                    response.success()
                else:
                    response.failure(f"Erro ao atualizar livro: {response.status_code}")

        # =========================
        # 5. DELETAR LIVRO (DELETE)
        # =========================
        if livro_id:
            with self.client.delete(f"/livros/{livro_id}", catch_response=True, name="DELETE /livros/{id}") as response:
                if response.status_code in [200, 204]:
                    response.success()
                else:
                    response.failure(f"Erro ao deletar livro: {response.status_code}")