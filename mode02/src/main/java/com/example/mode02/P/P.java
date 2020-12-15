package com.example.mode02.P;

import com.example.mode02.Back.Back;
import com.example.mode02.Bean.FooBean;
import com.example.mode02.M.M;
import com.example.mode02.V.View;

public class P implements P1, Back {
    private static M m;
    private final View view;

    public P(View view) {
        this.view = view;
        m = new M();
    }

    @Override
    public void sj(FooBean fooBean) {
        view.sj(fooBean);
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
