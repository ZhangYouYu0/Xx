package com.example.mode02.Db;

import com.anfly.wanandroidbig.dao.DaoMaster;
import com.anfly.wanandroidbig.dao.DaoSession;
import com.anfly.wanandroidbig.dao.FoodDBeanDao;
import com.example.mode02.Bean.FoodDBean;
import com.example.mode02.MyApp;

import java.util.List;

public class DbUtil {
    private static DbUtil dbUtil;
    private final FoodDBeanDao foodDBeanDao;

    public DbUtil() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.context, "f");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        foodDBeanDao = daoSession.getFoodDBeanDao();
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

    public long inset(FoodDBean foodDBean){
        boolean b = is(foodDBean);
        if(!b){
            long insert = foodDBeanDao.insert(foodDBean);
            return insert;
        }else{
            return -1;
        }

    }

    private boolean is(FoodDBean foodDBean) {
        List<FoodDBean> list = foodDBeanDao.queryBuilder().where(FoodDBeanDao.Properties.Des.eq(foodDBean.getDes())).list();
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void delete(FoodDBean foodDBean){
        foodDBeanDao.delete(foodDBean);
    }

    public List<FoodDBean> query(){
        List<FoodDBean> list = foodDBeanDao.queryBuilder().list();
        return list;
    }

    public void update(FoodDBean foodDBean){
        foodDBeanDao.update(foodDBean);
    }

}
