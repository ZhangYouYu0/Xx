package com.example.ceshi1.Fragment;

public class SerBwan {
    private long con;
    private long len;

    public SerBwan(long con, long len) {
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
        return "SerBwan{" +
                "con=" + con +
                ", len=" + len +
                '}';
    }
}
