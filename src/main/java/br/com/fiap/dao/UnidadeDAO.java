package br.com.fiap.dao;

import br.com.fiap.to.UnidadeTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnidadeDAO {
    public ArrayList<UnidadeTO> findAll(){
        ArrayList<UnidadeTO> unidades = new ArrayList<>();
        String sql = "select * from ddd_unidades order by id";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs!=null){
                while (rs.next()){
                    UnidadeTO u = new UnidadeTO();
                    u.setCodigo(rs.getLong("id"));
                    u.setNome(rs.getString("nome"));
                    u.setEndereco(rs.getString("endereco"));
                    u.setCidade(rs.getString("cidade"));
                    u.setUf(rs.getString("uf"));
                    u.setTelefone(rs.getString("telefone"));
                    u.setHorario(rs.getString("horario"));
                    u.setImagem(rs.getString("imagem"));
                    u.setLocalizacao(rs.getString("localizacao"));

                    unidades.add(u);
                }
            } else {
                return null;
            }

        } catch (SQLException e){
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return unidades;
    }

    public UnidadeTO findById(long id){
        UnidadeTO u = new UnidadeTO();
        String sql = "select * from ddd_unidades where id = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                u.setCodigo(rs.getLong("id"));
                u.setNome(rs.getString("nome"));
                u.setEndereco(rs.getString("endereco"));
                u.setCidade(rs.getString("cidade"));
                u.setUf(rs.getString("uf"));
                u.setTelefone(rs.getString("telefone"));
                u.setHorario(rs.getString("horario"));
                u.setImagem(rs.getString("imagem"));
                u.setLocalizacao(rs.getString("localizacao"));
            } else {
                return null;
            }
        } catch (SQLException e){
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return u;
    }

    public UnidadeTO save(UnidadeTO u){
        String sql = "insert into ddd_unidades(nome, endereco, cidade, uf, telefone, horario, imagem, localizacao) values(?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEndereco());
            ps.setString(3, u.getCidade());
            ps.setString(4, u.getUf());
            ps.setString(5, u.getTelefone());
            ps.setString(6, u.getHorario());
            ps.setString(7, u.getImagem());
            ps.setString(8, u.getLocalizacao());
            if (ps.executeUpdate() > 0) return u;
            else return null;
        } catch (SQLException e){
            System.out.println("Erro ao Salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(long id){
        String sql = "delete from ddd_unidades where id = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1,id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Erro ao Excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public UnidadeTO update(UnidadeTO u){
        String sql = "update ddd_unidades set nome=?, endereco=?, cidade=?, uf=?, telefone=?, horario=?, imagem=?, localizacao=? where id=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEndereco());
            ps.setString(3, u.getCidade());
            ps.setString(4, u.getUf());
            ps.setString(5, u.getTelefone());
            ps.setString(6, u.getHorario());
            ps.setString(7, u.getImagem());
            ps.setString(8, u.getLocalizacao());
            ps.setLong(9, u.getCodigo());
            if (ps.executeUpdate() > 0) return u;
            else return null;
        } catch (SQLException e){
            System.out.println("Erro ao Atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}

