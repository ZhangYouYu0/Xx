package com.example.moded.Db;

import com.anfly.wanandroidbig.dao.DaoMaster;
import com.anfly.wanandroidbig.dao.DaoSession;
import com.anfly.wanandroidbig.dao.FoodDbBeanDao;
import com.example.moded.Bean.FoodDbBean;
import com.example.moded.MyApp;

import java.util.List;

public class DbUtil {
    public static DbUtil dbUtil;
    private final FoodDbBeanDao foodDbBeanDao;

    public DbUtil() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.context, "d");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        foodDbBeanDao = daoSession.getFoodDbBeanDao();
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

    public long insert(FoodDbBean foodDbBean){
        long insert = foodDbBeanDao.insert(foodDbBean);
        return insert;
    }

    public List<FoodDbBean> query(){
        List<FoodDbBean> list = foodDbBeanDao.queryBuilder().list();
        return list;
    }

}
