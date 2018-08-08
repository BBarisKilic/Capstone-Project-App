package com.example.baris.whatis.user_interface.history;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ComponentName;
import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.baris.whatis.R;
import com.example.baris.whatis.data.database.HistoryDB;
import com.example.baris.whatis.user_interface.widget.HistoryWidgetProvider;

import java.util.List;


public class HistoryViewModel extends AndroidViewModel {

    private LiveData<List<HistoryDB>> historyWords;
    private HistoryDB.DictionaryDatabase dictionaryDatabase;

    HistoryViewModel(Application application, HistoryDB.DictionaryDatabase dictionaryDatabase) {
        super(application);
        this.dictionaryDatabase = dictionaryDatabase;
        historyWords = dictionaryDatabase.historyDataAccessObject().loadAllHistoryWords();
    }

    public LiveData<List<HistoryDB>> getHistoryWords() {
        return historyWords;
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteAllHistory(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dictionaryDatabase.historyDataAccessObject().deleteAll();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(HistoryViewModel.this.getApplication());
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName
                        (HistoryViewModel.this.getApplication(), HistoryWidgetProvider.class));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.history_rv);
                return null;
            }
            @Override
            protected void onPostExecute(Void voids) {
            }
        }.execute();
    }
}
