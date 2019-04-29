package com.rtstudio.persistenciausandoroom.conversao;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Conversores {

    @TypeConverter
    public static Date longToDate(Long valor) {
        return valor == null ? null : new Date(valor);
    }

    @TypeConverter
    public static Long dateToLong(Date date) {
        return date == null ? null : date.getTime();
    }
}
