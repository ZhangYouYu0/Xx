package com.anfly.wanandroidbig.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.mode02.Bean.FoodDBean;

import com.anfly.wanandroidbig.dao.FoodDBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig foodDBeanDaoConfig;

    private final FoodDBeanDao foodDBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        foodDBeanDaoConfig = daoConfigMap.get(FoodDBeanDao.class).clone();
        foodDBeanDaoConfig.initIdentityScope(type);

        foodDBeanDao = new FoodDBeanDao(foodDBeanDaoConfig, this);

        registerDao(FoodDBean.class, foodDBeanDao);
    }
    
    public void clear() {
        foodDBeanDaoConfig.clearIdentityScope();
    }

    public FoodDBeanDao getFoodDBeanDao() {
        return foodDBeanDao;
    }

}