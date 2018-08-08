package com.example.baris.whatis.data.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class ConvertDate {
    @TypeConverter
    public static Date makeDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long makeTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}