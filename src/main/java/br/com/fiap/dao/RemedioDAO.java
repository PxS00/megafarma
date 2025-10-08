package br.com.fiap.dao;

import br.com.fiap.to.RemedioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RemedioDAO {
    public ArrayList<br.com.fiap.to.RemedioTO> findAll(){
        ArrayList<br.com.fiap.to.RemedioTO>remedios = new ArrayList<>();
        br.com.fiap.to.RemedioTO remedio = new br.com.fiap.to.RemedioTO(1L, "Loratadina", 7.93, LocalDate.parse("2023-10-10"), LocalDate.parse("2025-10-10"));

        remedios.add(remedio);

        remedio = new br.com.fiap.to.RemedioTO(2L, "Amoxicilina", 26.50, LocalDate.now(), LocalDate.now().plusYears(2));
        remedios.add(remedio);
        return remedios;
    };

    public RemedioTO save(RemedioTO remedio){
        String sql = "insert into ddd_remedios(nome, preco, data_de_fabricacao, data_de_validade) values(?,?,?,?)";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, remedio.getNome());
            ps.setDouble(2, remedio.getPreco());
            ps.setDate(3, Date.valueOf(remedio.getDataDeFabricacao()));
            ps.setDate(4, Date.valueOf(remedio.getDataDeValidade()));
            if (ps.executeUpdate() > 0){
                return remedio;
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Salvar: " + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
