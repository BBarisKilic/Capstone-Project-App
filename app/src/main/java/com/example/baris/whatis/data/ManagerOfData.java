package com.example.baris.whatis.data;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.app.Application;
import java.util.Date;

import com.example.baris.whatis.BuildConfig;
import com.example.baris.whatis.data.database.HistoryDB;
import com.example.baris.whatis.data.models.Responses;
import com.example.baris.whatis.data.network.API;

import retrofit2.Callback;

public class ManagerOfData {

    private HistoryDB.DictionaryDatabase dictionaryDatabase;
    private static ManagerOfData managerOfData;
    private API.oxford_API oxford_api;


    private ManagerOfData(Application application) {
        this.oxford_api = API.createService();
        this.dictionaryDatabase = HistoryDB.DictionaryDatabase.getInstance(application);
    }

    public static ManagerOfData getInstance(Application application) {
        if (managerOfData == null) managerOfData = new ManagerOfData(application);
        return managerOfData;
    }

    public void search(String lang, String word, Callback<Responses> listener) {
        oxford_api.search(lang, word, BuildConfig.APP_ID, BuildConfig.APP_KEY).enqueue(listener);
    }

    @SuppressLint("StaticFieldLeak")
    public void saveHistory(final String word){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                HistoryDB seekHistoryWords = dictionaryDatabase.historyDataAccessObject().loadHistoryWordByWord(word);
                if(seekHistoryWords!=null){
                    dictionaryDatabase.historyDataAccessObject().updateHistoryWord(new HistoryDB(word, new Date()));
                } else {
                    dictionaryDatabase.historyDataAccessObject().insertHistoryWord(new HistoryDB(word, new Date()));
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void voids) {
            }
        }.execute();
    }
}
