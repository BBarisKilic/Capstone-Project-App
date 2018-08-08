package com.example.baris.whatis.user_interface.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class HistoryWordsWidgetService extends RemoteViewsService
{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new HistoryWordsRemoteViewsFactory(getApplicationContext()));
    }
}
