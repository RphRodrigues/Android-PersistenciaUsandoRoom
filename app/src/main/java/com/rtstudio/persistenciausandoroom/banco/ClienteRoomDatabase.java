package com.rtstudio.persistenciausandoroom.banco;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.rtstudio.persistenciausandoroom.DAO.ClienteDAO;
import com.rtstudio.persistenciausandoroom.classe.Cliente;
import com.rtstudio.persistenciausandoroom.conversao.Conversores;

@Database(entities = {Cliente.class}, version = 1, exportSchema = false)
@TypeConverters({Conversores.class})
public abstract class ClienteRoomDatabase extends RoomDatabase {

    private static volatile ClienteRoomDatabase INSTANCE;

    public static ClienteRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ClienteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ClienteRoomDatabase.class, "cliente_bd")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ClienteDAO clienteDAO();
}
