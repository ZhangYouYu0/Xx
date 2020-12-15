package com.example.ceshi1.Db;

import com.anfly.wanandroidbig.dao.DaoMaster;
import com.anfly.wanandroidbig.dao.DaoSession;
import com.anfly.wanandroidbig.dao.FooDbBeanDao;
import com.example.ceshi1.Bean.FooDbBean;
import com.example.ceshi1.MyApp;

import java.util.List;

public class DbUtil {
    private static DbUtil dbUtil;
    private final FooDbBeanDao fooDbBeanDao;

    public DbUtil() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.context, "d");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        fooDbBeanDao = daoSession.getFooDbBeanDao();
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


    public long insert(FooDbBean fooDbBean){
        long insert = fooDbBeanDao.insert(fooDbBean);
        return insert;
    }

    public List<FooDbBean> query(){
        List<FooDbBean> list = fooDbBeanDao.queryBuilder().list();
        return list;
    }
}
