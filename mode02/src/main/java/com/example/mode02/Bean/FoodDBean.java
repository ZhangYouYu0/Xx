package com.example.mode02.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FoodDBean {
    @Id(autoincrement = true)
    private Long id;
    private String des;
    private String pri;
    @Generated(hash = 884369463)
    public FoodDBean(Long id, String des, String pri) {
        this.id = id;
        this.des = des;
        this.pri = pri;
    }
    @Generated(hash = 852538801)
    public FoodDBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public String getPri() {
        return this.pri;
    }
    public void setPri(String pri) {
        this.pri = pri;
    }
}
