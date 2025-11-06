package br.com.fiap.to;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ClienteTO {
    private Long codigo;
    @NotBlank
    private String nome;
    @NotBlank
    @Size(min = 11, max = 14)
    private String cpf;
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Past
    private LocalDate dataDeNascimento;
    @NotBlank
    @Size(min = 8)
    private String senha;
    private String imagem;

    public ClienteTO() {
    }

    public ClienteTO(Long codigo, String nome, String cpf, String email, LocalDate dataDeNascimento, String senha, String imagem) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
        this.senha = senha;
        this.imagem = imagem;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
