package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.*;

public class IntegranteTO {
    private Long codigo;

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private Integer idade;

    @NotBlank
    private String imagem;

    @NotBlank
    private String github;

    public IntegranteTO() {
    }

    public IntegranteTO(Long codigo, String nome, Integer idade, String imagem, String github) {
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
        this.imagem = imagem;
        this.github = github;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
}

