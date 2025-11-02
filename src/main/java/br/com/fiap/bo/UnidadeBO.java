package br.com.fiap.bo;

import br.com.fiap.dao.UnidadeDAO;
import br.com.fiap.to.UnidadeTO;

import java.util.ArrayList;

public class UnidadeBO {
    private UnidadeDAO unidadeDAO;

    public ArrayList<UnidadeTO> findAll() {
        unidadeDAO = new UnidadeDAO();
        return unidadeDAO.findAll();
    }

    public UnidadeTO findById(Long id) {
        unidadeDAO = new UnidadeDAO();
        return unidadeDAO.findById(id);
    }

    public UnidadeTO save(UnidadeTO unidade){
        unidadeDAO = new UnidadeDAO();
        return unidadeDAO.save(unidade);
    }

    public boolean delete(Long id){
        unidadeDAO = new UnidadeDAO();
        return unidadeDAO.delete(id);
    }

    public UnidadeTO update(UnidadeTO unidade){
        unidadeDAO = new UnidadeDAO();
        return unidadeDAO.update(unidade);
    }
}

