package forum.hub.api.controller;


import forum.hub.api.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var topico = new Topico(dados);
        topicoRepository.save(topico);
        var uri= uriBuilder.path("/medicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));

    }

    @GetMapping
    public  Page<DadosListagemTopico> listar(
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id,@RequestBody @Valid DadosAtualizacaoTopico dados) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        var topico = topicoOptional.get();
        topico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Optional<Topico> topicoOptional=topicoRepository.findById(id);
        if (!topicoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }



}
