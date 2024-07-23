package forum.hub.api.controller;


import forum.hub.api.domain.comentario.*;
import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comentar")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/{id}")
    public ResponseEntity<DadosCadastroComentario> cadastrar(
            @PathVariable Long id,
            @RequestBody @Valid DadosCadastroComentario dados,
            UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal Usuario logado
    ) {
        Comentario comentario = comentarioService.cadastrar(id, dados, logado);

        URI uri = uriBuilder.path("/comentar/{id}").buildAndExpand(comentario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCadastroComentario(comentario));
    }
}