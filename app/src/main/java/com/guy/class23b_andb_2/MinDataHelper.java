package com.guy.class23b_andb_2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

public class MinDataHelper {

    private static MinDataHelper instance;
    private static AppDatabase appDatabase;

    private Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE min_data ADD COLUMN priority INTEGER NOT NULL DEFAULT '1'");
        }
    };

//    private Migration MIGRATION_2_3 = new Migration(2, 3) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE min_data ADD COLUMN priority INTEGER NOT NULL DEFAULT '1'");
//        }
//    };

    private MinDataHelper(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MinLogsDB.db")
                .addMigrations(MIGRATION_1_2)
                //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build();

//        appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MinLogsDB.db")
//                // allow queries on the main thread.
//                // Don't do this on a real app! See PersistenceBasicSample for an example.
//                // .allowMainThreadQueries()
//                .build();
    }

    public static MinDataHelper getInstance() {
        return instance;
    }

    public static MinDataHelper initHelper(Context context) {
        if (instance == null) {
            instance = new MinDataHelper(context);
        }

        return instance;
    }


    public interface CallBack_Logs {
        void dataReady(List<MinLog> minLogs);
    }

    public void addLog(String tag, String message, int priority) {
        long time = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.minLogDao().insertAll(
                        new MinLog(tag, message, time, priority)
                );
            }
        }).start();
    }

    public void addLog(String tag, String message) {
        addLog(tag, message, 1);
    }

    public void getAllLogsByTag(String tag, CallBack_Logs callBack_logs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MinLog> minLogs = appDatabase.minLogDao().getAllByTag(tag);
                if (callBack_logs != null) {
                    callBack_logs.dataReady(minLogs);
                }
            }
        }).start();
    }

    public void getAllLogs(CallBack_Logs callBack_logs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MinLog> minLogs = appDatabase.minLogDao().getAll();
                if (callBack_logs != null) {
                    callBack_logs.dataReady(minLogs);
                }
            }
        }).start();
    }

    public void deleteAllLogs() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.minLogDao().deleteAll();
            }
        }).start();
    }
}










