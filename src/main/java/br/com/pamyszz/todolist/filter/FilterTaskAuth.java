package br.com.pamyszz.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.pamyszz.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_PREFIX = "Basic ";
    private static final int UNAUTHORIZED_STATUS = 401;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                    @NonNull HttpServletResponse response, 
                                    @NonNull FilterChain filterChain)
        throws ServletException, IOException {
        String servletPath = request.getServletPath();
        
        if (servletPath.startsWith("/tasks/")) {
            String authorization = request.getHeader(AUTHORIZATION_HEADER);

            if (authorization == null || !authorization.startsWith(BASIC_PREFIX)) {
                response.sendError(UNAUTHORIZED_STATUS, "Cabeçalho de autorização ausente ou inválido.");
                return;
            }

            String authEncoded = authorization.substring(BASIC_PREFIX.length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            String authString = new String(authDecode);

            String[] credentials = authString.split(":");
            if (credentials.length != 2) {
                response.sendError(UNAUTHORIZED_STATUS, "Formato das credenciais de autenticação inválido.");
                return;
            }

            String username = credentials[0];
            String password = credentials[1];

            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(UNAUTHORIZED_STATUS, "Nome de usuário inválido.");
                return;
            }

            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (passwordVerify.verified) {
                request.setAttribute("idUser", user.getId());
                filterChain.doFilter(request, response);
            } else {
                response.sendError(UNAUTHORIZED_STATUS, "Senha inválida.");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
