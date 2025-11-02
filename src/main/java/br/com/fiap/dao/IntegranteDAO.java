package br.com.fiap.dao;

import br.com.fiap.to.IntegranteTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IntegranteDAO {
    public ArrayList<IntegranteTO> findAll(){
        ArrayList<IntegranteTO> lista = new ArrayList<>();
        String sql = "select * from ddd_integrantes order by codigo";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs!=null){
                while (rs.next()){
                    IntegranteTO it = new IntegranteTO();
                    it.setCodigo(rs.getLong("codigo"));
                    it.setNome(rs.getString("nome"));
                    it.setIdade(rs.getInt("idade"));
                    it.setImagem(rs.getString("imagem"));
                    it.setGithub(rs.getString("github"));
                    lista.add(it);
                }
            } else {
                return null;
            }
        } catch (SQLException e){
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return lista;
    }

    public IntegranteTO findByCodigo(long codigo){
        IntegranteTO it = new IntegranteTO();
        String sql = "select * from ddd_integrantes where codigo = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1,codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                it.setCodigo(rs.getLong("codigo"));
                it.setNome(rs.getString("nome"));
                it.setIdade(rs.getInt("idade"));
                it.setImagem(rs.getString("imagem"));
                it.setGithub(rs.getString("github"));
            } else {
                return null;
            }
        } catch (SQLException e){
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return it;
    }

    public IntegranteTO save(IntegranteTO it){
        String sql = "insert into ddd_integrantes(nome, idade, imagem, github) values(?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, it.getNome());
            ps.setInt(2, it.getIdade());
            ps.setString(3, it.getImagem());
            ps.setString(4, it.getGithub());
            if (ps.executeUpdate() > 0) return it;
            else return null;
        } catch (SQLException e){
            System.out.println("Erro ao Salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(long codigo){
        String sql = "delete from ddd_integrantes where codigo = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1,codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println("Erro ao Excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public IntegranteTO update(IntegranteTO it){
        String sql = "update ddd_integrantes set nome=?, idade=?, imagem=?, github=? where codigo=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, it.getNome());
            ps.setInt(2, it.getIdade());
            ps.setString(3, it.getImagem());
            ps.setString(4, it.getGithub());
            ps.setLong(5, it.getCodigo());
            if (ps.executeUpdate() > 0) return it;
            else return null;
        } catch (SQLException e){
            System.out.println("Erro ao Atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}

