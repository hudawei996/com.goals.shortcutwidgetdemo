package com.goals.shortcutwidgetdemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;

/**
 * 动态设置快捷方式，可以根据用户状态等，再次重新设置快捷方式，就可以达到更新的效果
 */
public class ShortcutHelper {

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public static void setShortcut(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) return;

        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);

        /**
         * 本来再写下边这两个方法中的内容是复制，然后改下参数名，这样造成了错误，
         * 使得手机开门的 .setIntent(intentScan)也写成了，扫码的Intent
         * 今后这种复制的写法，还是不要复制着写了。或者复制完成后再单独写成《《《两个单独的方法》》》，这样就可以看出来哪里有问题了。
         */
        ShortcutInfo shortcutInfoOpenDoor = getOpenDoorShortcut(context);

        ShortcutInfo shortcutInfoScan = getScanShortcut(context);

        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcutInfoScan,shortcutInfoOpenDoor));
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private static ShortcutInfo getScanShortcut(Context context) {
        Intent intentScan = null;
//        if (XXXContext.getInstance().isLogin()){
//            intentScan = new Intent(context, QrParseAct.class);
//            intentScan.putExtra(ConstantValue.NEED_RETURN_MAIN, true);
//            intentScan.putExtra(ConstantValue.FROM_TYPE, ConstantValue.FromType.WIDGET);
//            intentScan = QrParseAct.getIntent(context,ConstantValue.FromType.WIDGET,true);
//        }else {
            intentScan = new Intent(context, NewMainActivity.class);
            intentScan.putExtra("route", "dynamic/common/scan_qr");
//        }
        intentScan.setAction(Intent.ACTION_VIEW);
        ShortcutInfo shortcutInfoScan = new ShortcutInfo.Builder(context,"id2")
                .setShortLabel("D扫码")
                .setLongLabel("D扫一扫")
                .setIcon(Icon.createWithResource(context,R.mipmap.ic_short_cut_scan_d))
                .setIntent(intentScan)
                .build();
        return shortcutInfoScan;
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private static ShortcutInfo getOpenDoorShortcut(Context context) {
        Intent intentOpenDoor = null;
//        if (XXXContext.getInstance().isLogin()){
//            intentOpenDoor = new Intent(context, AccessControlActivity.class);
//            intentOpenDoor.putExtra(ConstantValue.NEED_RETURN_MAIN, true);
//            intentOpenDoor.putExtra(ConstantValue.FROM_TYPE, ConstantValue.FromType.WIDGET);
//            intentOpenDoor = AccessControlActivity.getIntent(context,ConstantValue.FromType.WIDGET,true);
//        }else {
            intentOpenDoor = new Intent(context, NewMainActivity.class);
            intentOpenDoor.putExtra("route", "dynamic/property/opendoor/list");
//        }
        intentOpenDoor.setAction(Intent.ACTION_VIEW);
        ShortcutInfo shortcutInfoOpenDoor = new ShortcutInfo.Builder(context,"id1")
                .setShortLabel("D开门")
                .setLongLabel("D手机开门")
                .setIcon(Icon.createWithResource(context, R.mipmap.ic_short_cut_open_door_d))
                .setIntent(intentOpenDoor)
                .build();
        return shortcutInfoOpenDoor;
    }
}

