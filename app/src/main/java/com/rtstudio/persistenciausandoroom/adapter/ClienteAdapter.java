package com.rtstudio.persistenciausandoroom.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rtstudio.persistenciausandoroom.CadastrarCliente;
import com.rtstudio.persistenciausandoroom.R;
import com.rtstudio.persistenciausandoroom.classe.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<Cliente> listaClientes;
    private Context context;

    public ClienteAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public ClienteAdapter(Context context, List<Cliente> listaClientes) {
        this.inflater = LayoutInflater.from(context);
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = inflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Cliente clienteCurrent = listaClientes.get(position);

        holder.clienteItemView.setText(clienteCurrent.getNomeCliente());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundleCliente = new Bundle();
                bundleCliente.putSerializable("cliente", clienteCurrent);

                Intent intentEditarCliente = new Intent(context, CadastrarCliente.class);
                intentEditarCliente.putExtra("bundleCliente", bundleCliente);
                context.startActivity(intentEditarCliente);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listaClientes != null) {
            return listaClientes.size();
        } else {
            return 0;
        }
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView clienteItemView;
        private View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            clienteItemView = itemView.findViewById(R.id.recyclerview_titulo);
            view = itemView;
        }
    }
}
