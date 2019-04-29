package com.rtstudio.persistenciausandoroom.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rtstudio.persistenciausandoroom.classe.Cliente;
import com.rtstudio.persistenciausandoroom.repositorio.RepositorioCliente;

import java.util.List;

public class ClienteViewModel extends AndroidViewModel {

    private RepositorioCliente repositorioCliente;
    private LiveData<List<Cliente>> listaClientes;

    public ClienteViewModel(@NonNull Application application) {
        super(application);
        repositorioCliente = new RepositorioCliente(application);
        listaClientes = repositorioCliente.getListaClientes();
    }

    public LiveData<List<Cliente>> getListaClientes() {
        return listaClientes;
    }

    public void insert(Cliente cliente) {
        repositorioCliente.insert(cliente);
    }
}
