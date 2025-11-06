package br.com.fiap.dao;

import br.com.fiap.to.ClienteTO;

import java.sql.Date;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.StringReader;
import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;

public class ClienteDAO {
    public ArrayList<ClienteTO> findAll() {
        ArrayList<ClienteTO> clientes = new ArrayList<>();
        String sql = "select * from ddd_clientes order by codigo";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs!=null){
                while (rs.next()){
                    ClienteTO cliente = new ClienteTO();
                    cliente.setCodigo(rs.getLong("codigo"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setDataDeNascimento(rs.getDate("data_de_nascimento").toLocalDate());
                    cliente.setSenha(rs.getString("senha"));
                    // ler CLOB da coluna imagem
                    Clob clob = rs.getClob("imagem");
                    if (clob != null) {
                        try (Reader r = clob.getCharacterStream()) {
                            StringBuilder sb = new StringBuilder();
                            char[] buffer = new char[2048];
                            int len;
                            while ((len = r.read(buffer)) != -1) {
                                sb.append(buffer, 0, len);
                            }
                            cliente.setImagem(sb.toString());
                        } catch (IOException e) {
                            System.out.println("Erro ao ler CLOB imagem: " + e.getMessage());
                            cliente.setImagem(null);
                        }
                    } else {
                        cliente.setImagem(null);
                    }

                    clientes.add(cliente);
                }

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return clientes;

    }

    public ClienteTO findByCodigo(long codigo){
        ClienteTO cliente = new ClienteTO();
        String sql = "select * from ddd_clientes where codigo = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cliente.setCodigo(rs.getLong("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataDeNascimento(rs.getDate("data_de_nascimento").toLocalDate());
                cliente.setSenha(rs.getString("senha"));
                Clob clob = rs.getClob("imagem");
                if (clob != null) {
                    try (Reader r = clob.getCharacterStream()) {
                        StringBuilder sb = new StringBuilder();
                        char[] buffer = new char[2048];
                        int len;
                        while ((len = r.read(buffer)) != -1) {
                            sb.append(buffer, 0, len);
                        }
                        cliente.setImagem(sb.toString());
                    } catch (IOException e) {
                        System.out.println("Erro ao ler CLOB imagem: " + e.getMessage());
                        cliente.setImagem(null);
                    }
                } else {
                    cliente.setImagem(null);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return cliente;
    }

    public ClienteTO save(ClienteTO cliente) {
        String sql = "insert into ddd_clientes(nome, cpf, email, data_de_nascimento, senha, imagem) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            ps.setDate(4, Date.valueOf(cliente.getDataDeNascimento()));
            ps.setString(5, cliente.getSenha());
            // gravar imagem como CLOB
            if (cliente.getImagem() != null) {
                StringReader reader = new StringReader(cliente.getImagem());
                ps.setClob(6, (Reader) reader, cliente.getImagem().length());
            } else {
                ps.setClob(6, (Reader) null, 0);
            }
            if (ps.executeUpdate() > 0) {
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "delete from ddd_clientes where codigo = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ClienteTO update(ClienteTO cliente) {
        String sql = "update ddd_clientes set nome=?, cpf=?, email=?, data_de_nascimento=?, senha=?, imagem=? where codigo=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            ps.setDate(4, Date.valueOf(cliente.getDataDeNascimento()));
            ps.setString(5, cliente.getSenha());
            if (cliente.getImagem() != null) {
                StringReader reader = new StringReader(cliente.getImagem());
                ps.setClob(6, (Reader) reader, cliente.getImagem().length());
            } else {
                ps.setClob(6, (Reader) null, 0);
            }
            ps.setLong(7, cliente.getCodigo());
            if (ps.executeUpdate() > 0) {
                return cliente;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
