package com.rtstudio.persistenciausandoroom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rtstudio.persistenciausandoroom.adapter.ClienteAdapter;
import com.rtstudio.persistenciausandoroom.classe.Cliente;
import com.rtstudio.persistenciausandoroom.viewModel.ClienteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CADASTRAR_CLIENTE = 1;
    private FloatingActionButton fab;
    private ClienteViewModel clienteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fabId);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ClienteAdapter adapter = new ClienteAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clienteViewModel = ViewModelProviders.of(this).get(ClienteViewModel.class);

        clienteViewModel.getListaClientes().observe(this, new Observer<List<Cliente>>() {
            @Override
            public void onChanged(@Nullable List<Cliente> clientes) {
                adapter.setListaClientes(clientes);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastrarCliente = new Intent(MainActivity.this, CadastrarCliente.class);
                startActivityForResult(intentCadastrarCliente, REQUEST_CODE_CADASTRAR_CLIENTE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_CADASTRAR_CLIENTE && resultCode == RESULT_OK) {

            Bundle bundleCliente = data.getBundleExtra("bundleCliente");

            Cliente cliente = (Cliente) bundleCliente.getSerializable("cliente");

            if (cliente != null) {
                clienteViewModel.insert(cliente);
                Toast.makeText(this, "Cliente " + cliente.getNomeCliente() + " salvo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
