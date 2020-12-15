package com.example.mode01.Db;

import com.anfly.wanandroidbig.dao.DaoMaster;
import com.anfly.wanandroidbig.dao.DaoSession;
import com.anfly.wanandroidbig.dao.FoodBeanDao;
import com.example.mode01.Bean.FoodBean;
import com.example.mode01.MyApp;

import java.util.List;

public class DbUtil {
    private static DbUtil dbUtil;
    private final FoodBeanDao foodBeanDao;

    public DbUtil() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.context, "f");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        foodBeanDao=daoSession.getFoodBeanDao();
    }

    public static DbUtil getDbUtil() {
        if(dbUtil==null){
            synchronized (DbUtil.class){
                if(dbUtil==null){
                    dbUtil = new DbUtil();
                }
            }
        }
        return dbUtil;
    }

    public long insert(FoodBean foodBean){
        boolean b = is(foodBean);
        if(!b){
            long insert = foodBeanDao.insert(foodBean);
            return insert;
        }else{
            return -1;
        }
    }

    private boolean is(FoodBean foodBean) {
        List<FoodBean> list = foodBeanDao.queryBuilder().where(FoodBeanDao.Properties.Des.eq(foodBean.getDes())).list();
        if(list!=null && list.size()>0){
            return true;
        }else{
            return false;
        }

    }

    public List<FoodBean> query(){
        List<FoodBean> list = foodBeanDao.queryBuilder().list();
        return list;
    }

}
