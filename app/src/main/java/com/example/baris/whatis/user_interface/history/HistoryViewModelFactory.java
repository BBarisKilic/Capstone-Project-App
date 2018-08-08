package com.example.baris.whatis.user_interface.history;

import android.support.annotation.NonNull;
import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;


import com.example.baris.whatis.data.database.HistoryDB;


public class HistoryViewModelFactory implements ViewModelProvider.Factory  {

    private Application application;
    private HistoryDB.DictionaryDatabase dictionaryDatabase;

    HistoryViewModelFactory(Application application, HistoryDB.DictionaryDatabase dictionaryDatabase) {
        this.application = application;
        this.dictionaryDatabase = dictionaryDatabase;
    }
    @NonNull
    @Override
    public HistoryViewModel create(@NonNull Class modelClass) {
        return new HistoryViewModel(application, dictionaryDatabase);
    }
}
