package com.pfc.thindesk.service;

import com.pfc.thindesk.entity.Cliente;
import com.pfc.thindesk.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
// A mudança principal está aqui: "implements ClienteService"
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override // Adicionamos @Override para indicar que estamos implementando o método do contrato
    public Cliente novoCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override // Adicionamos @Override aqui também
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}