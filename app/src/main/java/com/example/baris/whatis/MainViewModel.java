package com.example.baris.whatis;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.ComponentName;
import android.support.annotation.NonNull;

import com.example.baris.whatis.user_interface.widget.HistoryWidgetProvider;
import com.example.baris.whatis.data.ManagerOfData;
import com.example.baris.whatis.data.models.LexicalEntry;
import com.example.baris.whatis.data.models.Result;
import com.example.baris.whatis.data.models.Responses;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> bool;
    private MutableLiveData<Throwable> throwableMutableLiveData;
    private MutableLiveData<List<LexicalEntry>> lexicalEntries;
    private ManagerOfData managerOfData;

    MainViewModel(Application application, ManagerOfData managerOfData) {
        super(application);
        bool = new MutableLiveData<>();
        throwableMutableLiveData = new MutableLiveData<>();
        lexicalEntries = new MutableLiveData<>();
        this.managerOfData = managerOfData;
    }

    public MutableLiveData<Boolean> getBool() {
        return bool;
    }

    public MutableLiveData<Throwable> getThrowableMutableLiveData() {
        return throwableMutableLiveData;
    }
    public MutableLiveData<List<LexicalEntry>> getLexicalEntries() {
        return lexicalEntries;
    }

    public void search(final String lang,final String word) {
        bool.setValue(true);
        throwableMutableLiveData.setValue(null);
        lexicalEntries.setValue(null);

        managerOfData.search(lang, word, new Callback<Responses>() {
            @Override
            public void onResponse(@NonNull Call<Responses> call, @NonNull Response<Responses> response) {
                bool.setValue(false);
                if (response.isSuccessful()) {
                    Responses searchResponse = response.body();
                    assert searchResponse != null;
                    if (searchResponse.getResults() != null && searchResponse.getResults().size() > 0) {
                        Result result = searchResponse.getResults().get(0);
                        if (result.getLexicalEntries() != null && result.getLexicalEntries().size() > 0) {
                            lexicalEntries.setValue(result.getLexicalEntries());
                            saveSearchHistoryEntry(word);
                            throwableMutableLiveData.setValue(null);
                        } else {
                            throwableMutableLiveData.setValue(new Throwable(getApplication().getString(R.string.empty_result)));
                        }
                    } else {
                        throwableMutableLiveData.setValue(new Throwable(getApplication().getString(R.string.empty_result)));
                    }
                } else {
                    if (response.code() != 404)
                        throwableMutableLiveData.setValue(new Throwable(getApplication().getString(R.string.server_error)));
                    else {
                        throwableMutableLiveData.setValue(new Throwable(getApplication().getString(R.string.empty_result)));
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Responses> call, @NonNull Throwable t) {
                throwableMutableLiveData.setValue(t);
                bool.setValue(false);
            }
        });
    }

    private void saveSearchHistoryEntry(String entry) {
        managerOfData.saveHistory(entry);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplication());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this.getApplication(), HistoryWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.history_rv);
    }
}
