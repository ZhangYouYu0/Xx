package com.example.moded.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FoodDbBean {
    @Id(autoincrement = true)
    private Long id;
    private String des;
    private String pic;
    @Generated(hash = 1668969797)
    public FoodDbBean(Long id, String des, String pic) {
        this.id = id;
        this.des = des;
        this.pic = pic;
    }
    @Generated(hash = 649472808)
    public FoodDbBean() {
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
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
}
