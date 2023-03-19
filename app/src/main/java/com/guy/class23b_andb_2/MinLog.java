package com.guy.class23b_andb_2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "min_data")
public class MinLog {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "tag")
    public String tag = "";

    @ColumnInfo(name = "message")
    public String message = "";

    @ColumnInfo(name = "time")
    public long time = 0;

    @ColumnInfo(name = "priority")
    public int priority = 1;

    public MinLog() {}

    public MinLog(String tag, String message, long time, int priority) {
        this.tag = tag;
        this.message = message;
        this.time = time;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public MinLog setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MinLog setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getTime() {
        return time;
    }

    public MinLog setTime(long time) {
        this.time = time;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public MinLog setPriority(int priority) {
        this.priority = priority;
        return this;
    }
}
