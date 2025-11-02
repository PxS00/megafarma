package br.com.fiap.bo;

import br.com.fiap.dao.IntegranteDAO;
import br.com.fiap.to.IntegranteTO;

import java.util.ArrayList;

public class IntegranteBO {
    private IntegranteDAO integranteDAO;

    public ArrayList<IntegranteTO> findAll() {
        integranteDAO = new IntegranteDAO();
        return integranteDAO.findAll();
    }

    public IntegranteTO findByCodigo(Long codigo) {
        integranteDAO = new IntegranteDAO();
        return integranteDAO.findByCodigo(codigo);
    }

    public IntegranteTO save(IntegranteTO integrante){
        integranteDAO = new IntegranteDAO();
        return integranteDAO.save(integrante);
    }

    public boolean delete(Long codigo){
        integranteDAO = new IntegranteDAO();
        return integranteDAO.delete(codigo);
    }

    public IntegranteTO update(IntegranteTO integrante){
        integranteDAO = new IntegranteDAO();
        return integranteDAO.update(integrante);
    }
}

