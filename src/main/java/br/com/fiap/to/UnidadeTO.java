package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class UnidadeTO {
    private Long codigo;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotBlank
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;

    @NotBlank
    private String telefone;

    @NotBlank
    private String horario;

    @NotBlank
    private String imagem;

    @NotBlank
    private String localizacao;

    public UnidadeTO() {
    }

    public UnidadeTO(Long codigo, String nome, String endereco, String cidade, String uf, String telefone, String horario, String imagem, String localizacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.telefone = telefone;
        this.horario = horario;
        this.imagem = imagem;
        this.localizacao = localizacao;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}

