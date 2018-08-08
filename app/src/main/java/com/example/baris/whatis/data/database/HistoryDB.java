package com.example.baris.whatis.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

@Entity(tableName = "history")
public class HistoryDB {

    @PrimaryKey @NonNull
    private String word;
    @ColumnInfo(name = "update_time")
    private Date update_time;

    public HistoryDB(@NonNull String word, Date update_time) {
        this.word = word;
        this.update_time = update_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    @Database(entities = {HistoryDB.class}, version = 1, exportSchema = false)
    @TypeConverters(ConvertDate.class)

    public abstract static class DictionaryDatabase extends RoomDatabase {

        private static DictionaryDatabase dictionaryDatabase;
        private static final Object OBJECT = new Object();
        private static final String DATABASE_NAME = "what_is.db";


        public static DictionaryDatabase getInstance(Context context) {
            if (dictionaryDatabase == null) {
                synchronized (OBJECT) {
                    dictionaryDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            DictionaryDatabase.class, DictionaryDatabase.DATABASE_NAME)
                            .build();
                }
            }
            return dictionaryDatabase;
        }

        public abstract HistoryDataAccessObject historyDataAccessObject();

        @Dao
        public interface HistoryDataAccessObject {

            @Query("SELECT * FROM history ORDER BY update_time DESC")
            LiveData<List<HistoryDB>> loadAllHistoryWords();

            @Query("SELECT * FROM history ORDER BY update_time DESC LIMIT 10")
            List<HistoryDB> getHistoryWords();

            @Insert
            void insertHistoryWord(HistoryDB seekHistoryWords);

            @Update(onConflict = OnConflictStrategy.REPLACE)
            void updateHistoryWord(HistoryDB seekHistoryWords);

            @Query("SELECT * FROM history WHERE word = :word")
            HistoryDB loadHistoryWordByWord(String word);

            @Query("DELETE FROM history")
            void deleteAll();

        }
    }

}
