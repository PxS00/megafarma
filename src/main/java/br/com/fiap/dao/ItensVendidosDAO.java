package br.com.fiap.dao;

import br.com.fiap.to.ItensVendidosTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItensVendidosDAO {
    public ArrayList<ItensVendidosTO> findAll() {
        ArrayList<ItensVendidosTO> itens = new ArrayList<>();
        String sql = "select * from ddd_itensvendidos order by codvenda";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs!=null){
                while (rs.next()){
                    ItensVendidosTO item = new ItensVendidosTO();
                    item.setCodVenda(rs.getLong("codvenda"));
                    item.setCodRemedio(rs.getLong("codremedio"));
                    item.setQuantidade(rs.getInt("quantidade"));

                    itens.add(item);
                }

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return itens;

    }

    public ItensVendidosTO findByKeys(long codVenda, long codRemedio){
        ItensVendidosTO item = new ItensVendidosTO();
        String sql = "select * from ddd_itensvendidos where codvenda = ? and codremedio = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codVenda);
            ps.setLong(2, codRemedio);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                item.setCodVenda(rs.getLong("codvenda"));
                item.setCodRemedio(rs.getLong("codremedio"));
                item.setQuantidade(rs.getInt("quantidade"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return item;
    }

    public ItensVendidosTO save(ItensVendidosTO item) {
        String sql = "insert into ddd_itensvendidos(codvenda, codremedio, quantidade) values(?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, item.getCodVenda());
            ps.setLong(2, item.getCodRemedio());
            ps.setInt(3, item.getQuantidade());
            if (ps.executeUpdate() > 0) {
                return item;
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

    public boolean delete(Long codVenda, Long codRemedio) {
        String sql = "delete from ddd_itensvendidos where codvenda = ? and codremedio = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codVenda);
            ps.setLong(2, codRemedio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao Excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ItensVendidosTO update(ItensVendidosTO item) {
        String sql = "update ddd_itensvendidos set quantidade=? where codvenda=? and codremedio=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, item.getQuantidade());
            ps.setLong(2, item.getCodVenda());
            ps.setLong(3, item.getCodRemedio());
            if (ps.executeUpdate() > 0) {
                return item;
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

