//package forum.hub.api.controller;
//
//import forum.hub.api.infra.security.TokenService;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import forum.hub.api.infra.security.TokenService;
//
//@Component
//public class JwtTokenUtil {
//
//    private final TokenService tokenService;
//
//    public JwtTokenUtil(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }
//
//    public Long obterIdUsuarioDoToken() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.isAuthenticated()) {
//            // Recupera o token do contexto de segurança
//            String token = (String) authentication.getCredentials();
//
//            // Valida e extrai o ID do usuário do token utilizando o serviço de token
//            String email = tokenService.validateToken(token);
//            // Aqui você poderia fazer uma busca na base de dados a partir do e-mail ou login.
//
//            // Apenas como exemplo, retornando a própria string passada.
//            return Long.valueOf(email);
//        }
//
//        // Retorna null ou lança uma exceção se não conseguir obter o ID do usuário
//        throw new RuntimeException("Não foi possível obter o ID do usuário do token JWT");
//    }
//}
