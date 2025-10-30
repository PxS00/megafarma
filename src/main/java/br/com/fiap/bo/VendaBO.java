package br.com.fiap.bo;

import br.com.fiap.dao.VendaDAO;
import br.com.fiap.to.VendaTO;

import java.util.ArrayList;

public class VendaBO {
    private VendaDAO vendaDAO;

    public ArrayList<VendaTO> findAll() {
        vendaDAO = new VendaDAO();
        // Lógica de negócio pode ser adicionada aqui
        return vendaDAO.findAll();
    }

    public VendaTO findByCodigo(Long codigo) {
        vendaDAO = new VendaDAO();
        // Lógica de negócio pode ser adicionada aqui
        return vendaDAO.findByCodigo(codigo);
    }

    public VendaTO save(VendaTO venda){
        // Lógica de negócio pode ser adicionada aqui
        return vendaDAO.save(venda);
    }

    public boolean delete(Long codigo){
        vendaDAO = new VendaDAO();
        // Lógica de negócio pode ser adicionada aqui
        return vendaDAO.delete(codigo);
    }

    public VendaTO update(VendaTO venda){
        vendaDAO = new VendaDAO();
        // Lógica de negócio pode ser adicionada aqui
        return vendaDAO.update(venda);
    }
}
