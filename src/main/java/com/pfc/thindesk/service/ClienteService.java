package com.pfc.thindesk.service;

import com.pfc.thindesk.entity.Cliente;
import java.util.List;

public interface ClienteService {

    Cliente novoCliente(Cliente cliente);

    List<Cliente> listarClientes();
}
