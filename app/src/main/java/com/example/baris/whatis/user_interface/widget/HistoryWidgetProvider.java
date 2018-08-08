package com.example.baris.whatis.user_interface.widget;

import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.baris.whatis.R;
import com.example.baris.whatis.MainActivity;

import java.util.Objects;


public class HistoryWidgetProvider extends AppWidgetProvider
{
    public static final String NEW_SEARCH_WORD = "new_search_word";
    private static final String ITEM_CLICKED = "item_clicked";

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId)
    {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intent = new Intent(context, HistoryWordsWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        remoteViews.setRemoteAdapter(R.id.history_rv, intent);
        remoteViews.setEmptyView(R.id.history_rv, R.id.empty_widget_tv);

        Intent startIntent = new Intent(context, HistoryWidgetProvider.class);
        startIntent.setAction(ITEM_CLICKED);
        PendingIntent startActivityPendingIntent = PendingIntent.getBroadcast(context, appWidgetId, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.history_rv, startActivityPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.history_rv);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ITEM_CLICKED.equals(intent.getAction())) {
            String searchEntry = Objects.requireNonNull(intent.getExtras()).getString(NEW_SEARCH_WORD);
            Intent startActivityIntent = new Intent(context, MainActivity.class);
            startActivityIntent.putExtra(NEW_SEARCH_WORD, searchEntry);
            startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(startActivityIntent);
        }
    }

}