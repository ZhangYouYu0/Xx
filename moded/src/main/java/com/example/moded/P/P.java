package com.example.moded.P;

import com.example.moded.Back.Back;
import com.example.moded.Bean.FoodBean;
import com.example.moded.M.M;
import com.example.moded.V.View;

public class P implements P1, Back {
    private static M m;
    private final View view;

    public P(View view) {
        this.view = view;
        m = new M();
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
