package com.guy.class23b_andb_2;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        MinDataHelper.getInstance().addLog("awww", "Application Created", 2);

    }
}
