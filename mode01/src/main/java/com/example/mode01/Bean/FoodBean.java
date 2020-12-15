package com.example.mode01.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FoodBean {
    @Id(autoincrement = true)
    private Long id;
    private String des;
    @Generated(hash = 1925791520)
    public FoodBean(Long id, String des) {
        this.id = id;
        this.des = des;
    }
    @Generated(hash = 895705851)
    public FoodBean() {
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
    
}
