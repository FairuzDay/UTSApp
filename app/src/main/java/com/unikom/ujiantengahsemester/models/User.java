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

@Entity(tableName = "table_user")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "unim")
    private String fnim;

    @ColumnInfo(name = "uname")
    private String fname;

    @ColumnInfo(name = "uclass")
    private String fclass;

    @ColumnInfo(name = "udesc")
    private String udesc;

    @ColumnInfo(name = "upict")
    private String upict;

    @ColumnInfo(name = "uemail")
    private String uemail;

    @ColumnInfo(name = "uphone")
    private String uphone;

    @ColumnInfo(name = "usosmed")
    private String usosmed;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @NonNull
    public String getFnim() {
        return fnim;
    }

    public String getFname() {
        return fname;
    }

    public String getFclass() {
        return fclass;
    }

    public String getUdesc() {
        return udesc;
    }

    public String getUpict() {
        return upict;
    }

    public String getUemail() {
        return uemail;
    }

    public String getUphone() {
        return uphone;
    }

    public String getUsosmed() {
        return usosmed;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(@NonNull String fnim, String fname, String fclass, String udesc, String upict, String uemail, String uphone, String usosmed, String username, String password) {
        this.fnim = fnim;
        this.fname = fname;
        this.fclass = fclass;
        this.udesc = udesc;
        this.upict = upict;
        this.uemail = uemail;
        this.uphone = uphone;
        this.usosmed = usosmed;
        this.username = username;
        this.password = password;
    }
}

