package com.example.roomexample.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = Result.TABLE_NAME)
public class Result {
    final static String TABLE_NAME = "result";

    @PrimaryKey(autoGenerate = true)
    private int uid;

    private int fichas;

    @ColumnInfo(name = "nombre")
    private String name;

    @Ignore
    public Result(int fichas, String name) {
        this.fichas = fichas;
        this.name = name;
    }

    public Result(int uid, int fichas, String name) {
        this.uid = uid;
        this.fichas = fichas;
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public int getFichas() {
        return fichas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Result{" +
                "uid=" + uid +
                ", fichas=" + fichas +
                ", name='" + name + '\'' +
                '}' + "\n";
    }
}
