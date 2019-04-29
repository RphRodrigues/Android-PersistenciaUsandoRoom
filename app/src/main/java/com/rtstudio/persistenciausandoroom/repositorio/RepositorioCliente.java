package com.rtstudio.persistenciausandoroom.repositorio;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.rtstudio.persistenciausandoroom.DAO.ClienteDAO;
import com.rtstudio.persistenciausandoroom.banco.ClienteRoomDatabase;
import com.rtstudio.persistenciausandoroom.classe.Cliente;

import java.util.List;

public class RepositorioCliente {

    private ClienteDAO clienteDAO;
    private LiveData<List<Cliente>> listClientes;

    public RepositorioCliente(Application application) {
        ClienteRoomDatabase bd = ClienteRoomDatabase.getInstance(application);
        clienteDAO = bd.clienteDAO();
        listClientes = clienteDAO.getAll();
    }

    public LiveData<List<Cliente>> getListaClientes() {
        return listClientes;
    }

    public void insert(Cliente cliente){
        new insertAsyncTask(clienteDAO).execute(cliente);
    }

    private static class insertAsyncTask extends AsyncTask<Cliente, Void, Void> {

        private ClienteDAO asyncTaskDao;

        insertAsyncTask(ClienteDAO clienteDAO) {
            asyncTaskDao = clienteDAO;
        }

        @Override
        protected Void doInBackground(Cliente... clientes) {
            asyncTaskDao.insertCliente(clientes[0]);
            return null;
        }
    }
}
