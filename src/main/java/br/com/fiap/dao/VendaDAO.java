package br.com.fiap.dao;

import br.com.fiap.to.VendaTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendaDAO {
    public ArrayList<VendaTO> findAll() {
        ArrayList<VendaTO> vendas = new ArrayList<>();
        String sql = "select * from ddd_vendas order by codigo";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs!=null){
                while (rs.next()){
                    VendaTO venda = new VendaTO();
                    venda.setCodigo(rs.getLong("codigo"));
                    venda.setDataDaVenda(rs.getDate("data_da_venda").toLocalDate());
                    venda.setCodCliente(rs.getLong("codcliente"));

                    vendas.add(venda);
                }

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return vendas;

    }

    public VendaTO findByCodigo(long codigo){
        VendaTO venda = new VendaTO();
        String sql = "select * from ddd_vendas where codigo = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                venda.setCodigo(rs.getLong("codigo"));
                venda.setDataDaVenda(rs.getDate("data_da_venda").toLocalDate());
                venda.setCodCliente(rs.getLong("codcliente"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na Consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return venda;
    }

    public VendaTO save(VendaTO venda) {
        String sql = "insert into ddd_vendas(data_da_venda, codcliente) values(?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(venda.getDataDaVenda()));
            ps.setLong(2, venda.getCodCliente());
            if (ps.executeUpdate() > 0) {
                return venda;
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
        String sql = "delete from ddd_vendas where codigo = ?";
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

    public VendaTO update(VendaTO venda) {
        String sql = "update ddd_vendas set data_da_venda=?, codcliente=? where codigo=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(venda.getDataDaVenda()));
            ps.setLong(2, venda.getCodCliente());
            ps.setLong(3, venda.getCodigo());
            if (ps.executeUpdate() > 0) {
                return venda;
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
