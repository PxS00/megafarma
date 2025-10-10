package br.com.fiap.bo;

import br.com.fiap.dao.RemedioDAO;
import br.com.fiap.to.RemedioTO;

import java.util.ArrayList;

public class RemedioBO {
    private RemedioDAO remedioDAO;

    public ArrayList<RemedioTO> findAll() {
        remedioDAO = new RemedioDAO();
        // Lógica de negócio pode ser adicionada aqui
        return remedioDAO.findAll();
    }

    public RemedioTO findByCodigo(Long codigo) {
        remedioDAO = new RemedioDAO();
        // Lógica de negócio pode ser adicionada aqui
        return remedioDAO.findByCodigo(codigo);
    }

    public RemedioTO save(RemedioTO remedio){
        //remedioDAO = new RemedioDAO();
        // Lógica de negócio pode ser adicionada aqui
        // if (remedio.getDataDeValidade().isBefore(LocalDate.now())){
        //    return null;
        // }
        return remedioDAO.save(remedio);
    }
}
