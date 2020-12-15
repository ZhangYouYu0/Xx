package com.example.mode02.Bean;

public class SerBean {
    private long con;
    private long len;

    public SerBean(long con, long len) {
        this.con = con;
        this.len = len;
    }

    public long getCon() {
        return con;
    }

    public void setCon(long con) {
        this.con = con;
    }

    public long getLen() {
        return len;
    }

    public void setLen(long len) {
        this.len = len;
    }

    @Override
    public String toString() {
        return "SerBean{" +
                "con=" + con +
                ", len=" + len +
                '}';
    }
}
