package com.example.baris.whatis.user_interface.widget;

import android.content.Intent;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.baris.whatis.data.database.HistoryDB;
import com.example.baris.whatis.utils.WhatIsExecutor;
import com.example.baris.whatis.R;

import java.util.List;

import static com.example.baris.whatis.user_interface.widget.HistoryWidgetProvider.NEW_SEARCH_WORD;

public class HistoryWordsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private HistoryDB.DictionaryDatabase dictionaryDatabase;
    private List<HistoryDB> historyDBList;

    HistoryWordsRemoteViewsFactory(Context context) {
        this.context = context;
        this.dictionaryDatabase = HistoryDB.DictionaryDatabase.getInstance(context);
    }

    @Override
    public void onCreate() {
        WhatIsExecutor.getInstance().diskIO().execute(() ->
                historyDBList = dictionaryDatabase.historyDataAccessObject().getHistoryWords());

    }

    @Override
    public int getCount() {
        if (historyDBList == null)
            return 0;
        else return historyDBList.size();
    }

    @Override
    public void onDataSetChanged() {
        WhatIsExecutor.getInstance().diskIO().execute(() ->
                historyDBList = dictionaryDatabase.historyDataAccessObject().getHistoryWords());
    }

    @Override
    public void onDestroy() { }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.item_widget);
        HistoryDB seekHistoryWord = historyDBList.get(position);
        remoteView.setTextViewText(R.id.history_tv, seekHistoryWord.getWord());
        Intent intent = new Intent();
        intent.putExtra(NEW_SEARCH_WORD, seekHistoryWord.getWord());
        remoteView.setOnClickFillInIntent(R.id.widgetRow, intent);
        return remoteView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}
