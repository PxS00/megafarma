package br.com.fiap.bo;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.to.ClienteTO;

import java.util.ArrayList;

public class ClienteBO {
    private ClienteDAO clienteDAO;

    public ArrayList<ClienteTO> findAll() {
        clienteDAO = new ClienteDAO();
        // Lógica de negócio pode ser adicionada aqui
        return clienteDAO.findAll();
    }

    public ClienteTO findByCodigo(Long codigo) {
        clienteDAO = new ClienteDAO();
        // Lógica de negócio pode ser adicionada aqui
        return clienteDAO.findByCodigo(codigo);
    }

    public ClienteTO save(ClienteTO cliente){
        clienteDAO = new ClienteDAO();
        // Lógica de negócio pode ser adicionada aqui
        return clienteDAO.save(cliente);
    }

    public boolean delete(Long codigo){
        clienteDAO = new ClienteDAO();
        // Lógica de negócio pode ser adicionada aqui
        return clienteDAO.delete(codigo);
    }

    public ClienteTO update(ClienteTO cliente){
        clienteDAO = new ClienteDAO();
        // Lógica de negócio pode ser adicionada aqui
        return clienteDAO.update(cliente);
    }
}
