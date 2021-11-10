package com.demo.service.Impl;

import com.demo.dao.GoodsDao;
import com.demo.dao.Impl.GoodsDaoImpl;
import com.demo.pojo.Goods;
import com.demo.service.GoodsService;

import java.util.List;
import java.util.Map;

/**
 * User: EDZ
 * Date: 2021/11/5
 * Time: 15:15
 */
public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public List<Goods> findAll() {
        return goodsDao.findAll();
    }

    @Override
    public int delGood(int gid) {
        return goodsDao.delGood(gid);
    }

    @Override
    public int addGood(String gname, String gimg, double gprice, long gstock, String maintainDate, String classify, String gdescribe) {
        return goodsDao.addGood(gname,gimg,gprice,gstock,maintainDate,classify,gdescribe);
    }

    @Override
    public List<Goods> findGoodsWithParam(Map<String, Object> param) {
        return goodsDao.findGoodsWithParam(param);
    }

    @Override
    public boolean updateGoodsBy(Goods goods) {
        return goodsDao.updateGoodsBy(goods);
    }
}
