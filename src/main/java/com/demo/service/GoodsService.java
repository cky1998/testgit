package com.demo.service;

import com.demo.pojo.Goods;

import java.util.List;
import java.util.Map;

/**
 * User: EDZ
 * Date: 2021/11/5
 * Time: 15:14
 */
public interface GoodsService {
    /**
     * 查询所有商品信息
     * @return 商品对象集合
     */
    List<Goods> findAll();

    int delGood(int gid);

    int addGood(String gname, String gimg, double gprice, long gstock, String maintainDate, String classify, String gdescribe);

    List<Goods> findGoodsWithParam(Map<String, Object> param);

    boolean updateGoodsBy(Goods goods);
}
