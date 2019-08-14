package com.unikom.ujiantengahsemester.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**tanggal : 11-08-2019
 *Nim     : 10116568
 *Nama    : Muh.Fairuz Hadi Day
 *Kelas    : IF-13
 */

@Entity(tableName = "table_friend")
public  class Friends {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "fnim")
    private String fnim;

    @ColumnInfo(name = "fname")
    private String fname;

    @ColumnInfo(name = "fclass")
    private String fclass;

    @ColumnInfo(name = "fphone")
    private String fphone;

    @ColumnInfo(name = "femail")
    private String femail;

    @ColumnInfo(name = "fsosmed")
    private String fsosmed;

    public Friends(String fnim, String fname, String fclass, String fphone, String femail, String fsosmed) {
        this.fnim = fnim;
        this.fname = fname;
        this.fclass = fclass;
        this.fphone = fphone;
        this.femail = femail;
        this.fsosmed = fsosmed;
    }

    public String getFnim() {
        return fnim;
    }

    public String getFname() {
        return fname;
    }

    public String getFclass() {
        return fclass;
    }

    public String getFphone() {
        return fphone;
    }

    public String getFemail() {
        return femail;
    }

    public String getFsosmed() {
        return fsosmed;
    }
}

