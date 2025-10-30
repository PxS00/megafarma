package br.com.fiap.bo;

import br.com.fiap.dao.ItensVendidosDAO;
import br.com.fiap.to.ItensVendidosTO;

import java.util.ArrayList;

public class ItensVendidosBO {
    private ItensVendidosDAO itensDAO;

    public ArrayList<ItensVendidosTO> findAll() {
        itensDAO = new ItensVendidosDAO();
        // Lógica de negócio pode ser adicionada aqui
        return itensDAO.findAll();
    }

    public ItensVendidosTO findByKeys(Long codVenda, Long codRemedio) {
        itensDAO = new ItensVendidosDAO();
        // Lógica de negócio pode ser adicionada aqui
        return itensDAO.findByKeys(codVenda, codRemedio);
    }

    public ItensVendidosTO save(ItensVendidosTO item){
        // Lógica de negócio pode ser adicionada aqui
        return itensDAO.save(item);
    }

    public boolean delete(Long codVenda, Long codRemedio){
        itensDAO = new ItensVendidosDAO();
        // Lógica de negócio pode ser adicionada aqui
        return itensDAO.delete(codVenda, codRemedio);
    }

    public ItensVendidosTO update(ItensVendidosTO item){
        itensDAO = new ItensVendidosDAO();
        // Lógica de negócio pode ser adicionada aqui
        return itensDAO.update(item);
    }
}
