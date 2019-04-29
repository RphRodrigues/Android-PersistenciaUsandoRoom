package com.rtstudio.persistenciausandoroom.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rtstudio.persistenciausandoroom.classe.Cliente;

import java.util.List;

@Dao
public abstract class ClienteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertCliente(Cliente cliente);

    @Update
    public abstract void updateCliente(Cliente cliente);

    @Delete
    public abstract void deleteCliente(Cliente cliente);

    @Query("DELETE FROM tabela_cliente")
    public abstract void deleteAll();

    @Query("SELECT * FROM tabela_cliente ORDER BY ultima_compra ASC")
    public abstract LiveData<List<Cliente>> getAll();
}
