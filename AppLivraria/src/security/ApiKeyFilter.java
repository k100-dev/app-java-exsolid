package security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ApiKeyFilter implements Filter {

    // Chave simples usada para autenticação
    private static final String API_KEY = "livraria123";

    // Nome do header esperado na requisição
    private static final String HEADER = "X-API-KEY";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Obtém a API Key enviada no header
        String apiKey = httpRequest.getHeader(HEADER);

        // Verifica se a chave enviada é válida
        if (API_KEY.equals(apiKey)) {

            // Permite continuar a requisição
            chain.doFilter(request, response);

        } else {

            // Bloqueia acesso caso a chave seja inválida
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Chave de API invalida");
        }
    }
}