package dominio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(nullable = false)
    private Boolean inscricao;

    @ElementCollection
    private List<String> contatos = new ArrayList<String>();

    public Cliente(Boolean inscricao) {
        this.inscricao = inscricao;
    }

    public Cliente(String nome, Boolean inscricao, List<String> contatos) {
        this.nome = nome;
        this.inscricao = inscricao;
        this.contatos = contatos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getInscricao() {
        return inscricao;
    }

    public void setInscricao(Boolean inscricao) {
        this.inscricao = inscricao;
    }

    public List<String> getContatos() {
        return contatos;
    }

    public void setContatos(List<String> contatos) {
        this.contatos = contatos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", inscricao='" + inscricao + '\'' +
                ", contatos=" + contatos +
                '}';
    }
}
