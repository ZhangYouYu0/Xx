package com.example.ceshi1.P;

import com.example.ceshi1.Back.Back;
import com.example.ceshi1.Bean.FoodBean;
import com.example.ceshi1.M.M;
import com.example.ceshi1.V.View;

public class P implements P1, Back {
    private static M m;
    private final View view;

    public P(View view) {
        this.view = view;
        m  = new M();
    }

    @Override
    public void sj(FoodBean foodBean) {
        view.sj(foodBean);
    }

    @Override
    public void yc(String yc) {
        view.yc(yc);
    }

    @Override
    public void P1() {
        m.M1(this);
    }
}
