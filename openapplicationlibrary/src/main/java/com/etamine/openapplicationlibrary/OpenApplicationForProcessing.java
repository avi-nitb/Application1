package com.etamine.openapplicationlibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import java.util.List;

public class OpenApplicationForProcessing {
    public static void startApplication(String packageName, Context context, Bundle bundle)
    {
        try
        {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");

            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            List<ResolveInfo> resolveInfoList;
            resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);

            for(ResolveInfo info : resolveInfoList)
                if(info.activityInfo.packageName.equalsIgnoreCase(packageName))
                {
                    launchComponent(info.activityInfo.packageName, info.activityInfo.name, context, bundle);
                    return;
                }

            // No match, so application is not installed

        }
        catch (Exception e)
        {

        }
    }

    private static void launchComponent(String packageName, String name, Context context, Bundle bundle)
    {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(packageName, name));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("inputParameters", bundle);
        context.startActivity(intent);
    }

}
