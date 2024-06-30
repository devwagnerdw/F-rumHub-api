package forum.hub.api.controller;


import forum.hub.api.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroTopico dados){
        topicoRepository.save(new Topico(dados));

    }

    @GetMapping
    public Page<DadosListagemTopico> listar(
            @RequestParam(required = false) Curso curso,
            @RequestParam(required = false) Integer ano,
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {

        if (curso != null && ano != null) {
            return topicoRepository.findByCursoAndAno(curso, ano, paginacao).map(DadosListagemTopico::new);
        } else if (curso != null) {
            return topicoRepository.findByCurso(curso, paginacao).map(DadosListagemTopico::new);
        } else if (ano != null) {
            return topicoRepository.findByAno(ano, paginacao).map(DadosListagemTopico::new);
        } else {
            return topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }



}
