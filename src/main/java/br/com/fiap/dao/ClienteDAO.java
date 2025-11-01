package br.com.fiap.dao;

import br.com.fiap.to.ClienteTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    cliente.setImagem(rs.getString("imagem"));

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
                cliente.setImagem(rs.getString("imagem"));
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
            ps.setString(6, cliente.getImagem());
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
            ps.setString(6, cliente.getImagem());
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
