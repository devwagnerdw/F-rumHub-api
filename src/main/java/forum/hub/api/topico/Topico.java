package forum.hub.api.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private Date dataCriacao;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    @Enumerated(EnumType.STRING)
     private Curso curso;
     private int respostas;

    public Topico(DadosCadastroTopico dados) {
        this.titulo= dados.titulo();
        this.mensagem=dados.mensagem();
        this.autor=dados.autor();
        this.curso=dados.curso();
        this.dataCriacao = new Date();
        this.status = Status.ABERTO;
        this.respostas=0;
    }


    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }

        if (dados.curso() != null) {
            this.curso = dados.curso();
        }


    }
}
