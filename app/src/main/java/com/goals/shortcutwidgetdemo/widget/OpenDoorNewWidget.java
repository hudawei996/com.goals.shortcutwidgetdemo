package com.goals.shortcutwidgetdemo.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.goals.shortcutwidgetdemo.NewMainActivity;
import com.goals.shortcutwidgetdemo.R;

/**
 * Implementation of App Widget functionality.
 */
public class OpenDoorNewWidget extends AppWidgetProvider {
    int[] appWidgetIds = {};

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        if (BroadcastUtil.ACTION_WIDGET.equals(action)) {
            Log.d("接收到更新事件：=======" + action + ",", appWidgetIds.length + "");
            //接收到广播后更新微件
            onUpdate(context,AppWidgetManager.getInstance(context), appWidgetIds);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //这个appWidgetIds你并不需要管他是多少，只是系统记录下你新建了多少个微件，然后系统给他一个编号，用于来刷新这个微件使用的
        //同一种微件，系统会在这里返回这类微件的所有编号，用于刷新，对于开发者我觉得用处不大。除非你能分清这两个不同的微件，谁是谁，
        //就算分清楚了，又有啥用，其实功能都是一样的，长相也是一样的，只是有两个，仅此而已。
        this.appWidgetIds = appWidgetIds;
        for (int appWidgetId : appWidgetIds) {
            Log.d("","OpenDoorNewWidget-appWidgetIds:"+appWidgetId);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_open_door_widget);

            Intent intent  = null;
            //更新微件，比如说你可以做，登录和未登录的微件不同跳转
//            if (XXXContext.getInstance().isLogin()){
//                intent  = new Intent(context, AccessControlActivity.class);
//            }else {
                intent  = new Intent(context, NewMainActivity.class);
                intent.putExtra("route", "widget/property/opendoor/list");
//            }
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            //这里设置PendingIntent，要给整个Layout设置，而不是一个控件
//            views.setOnClickPendingIntent(R.id.info_tv, pendingIntent);
            views.setOnClickPendingIntent(R.id.open_door_ll, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}

