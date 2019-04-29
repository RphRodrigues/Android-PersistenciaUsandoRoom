package com.rtstudio.persistenciausandoroom.classe;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "tabela_cliente")
public class Cliente implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cliente_id")
    private long clienteId;

    @ColumnInfo(name = "nome_cliente")
    private String nomeCliente;

    @ColumnInfo(name = "telefone")
    private String telefone;

    @ColumnInfo(name = "ultima_compra")
    private Date ultimaCompra;

    @ColumnInfo(name = "codigo_cupom")
    private String codigoCupom;

    public Cliente(String nomeCliente, String telefone, Date ultimaCompra, String codigoCupom) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.ultimaCompra = ultimaCompra;
        this.codigoCupom = codigoCupom;
    }

    @Ignore
    public Cliente(String nomeCliente, String telefone) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
    }

    @Ignore
    public Cliente(String nomeCliente, String telefone, Date ultimaCompra) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.ultimaCompra = ultimaCompra;
    }

    @Ignore
    public Cliente(String nomeCliente, String telefone, String codigoCupom) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.codigoCupom = codigoCupom;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(Date ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }
}
