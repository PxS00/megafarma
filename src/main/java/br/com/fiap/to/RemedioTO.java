package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class RemedioTO {
    private Long codigo;
    @NotBlank
    private String nome;
    @NotNull
    @PositiveOrZero
    private Double preco;
    @PastOrPresent
    private LocalDate dataDeFabricacao;
    @FutureOrPresent
    private LocalDate dataDeValidade;
    @NotBlank
    private String imagem;

    public RemedioTO(Long codigo, String nome, Double preco, LocalDate dataDeFabricacao, LocalDate dataDeValidade, String imagem) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.dataDeFabricacao = dataDeFabricacao;
        this.dataDeValidade = dataDeValidade;
        this.imagem = imagem;
    }

    public RemedioTO() {
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataDeFabricacao() {
        return dataDeFabricacao;
    }

    public void setDataDeFabricacao(LocalDate dataDeFabricacao) {
        this.dataDeFabricacao = dataDeFabricacao;
    }

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
