package com.guy.class23b_andb_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MinDataHelper.getInstance().addLog("awww", "onCreate");

        diit();
    }

    private void diit() {

        MinDataHelper.getInstance().addLog("awww", "DoIt");
        Log.d("pttt", "diit");
    }
}