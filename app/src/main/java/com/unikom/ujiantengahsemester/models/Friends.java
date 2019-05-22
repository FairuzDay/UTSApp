package com.unikom.ujiantengahsemester.models;

public class Friends {
    private String fnim, fname, fclass, fphone, femail, fsosmed;

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

