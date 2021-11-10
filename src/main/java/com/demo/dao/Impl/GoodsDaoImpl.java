package com.demo.dao.Impl;

import com.demo.dao.GoodsDao;
import com.demo.pojo.Goods;
import com.demo.utils.DruidUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date: 2021/11/5
 * Time: 15:10
 */
public class GoodsDaoImpl implements GoodsDao {
    private final JdbcTemplate template = new JdbcTemplate(DruidUtil.getDataSource());
    @Override
    public List<Goods> findAll() {
        // sql
        String sql = "select * from goods";

        return template.query(sql, new BeanPropertyRowMapper<>(Goods.class));
    }

    @Override
    public int delGood(int gid) {
        String sql = "DELETE FROM goods WHERE gid = ? ";
        int count = template.update(sql, gid);
        return count;
    }

    @Override
    public int addGood(String gname, String gimg, double gprice, long gstock, String maintainDate, String classify, String gdescribe) {
        String sql = "insert into goods(gname, gImg, gprice, gstock, maintainDate, classify, gdescribe) values(?,?,?,?,?,?,?)";
        int count = template.update(sql, gname,gimg,gprice,gstock,maintainDate,classify,gdescribe);
        return count;
    }

    // 一种就是对象， map也可以封装多个数据
    @Override
    public List<Goods> findGoodsWithParam(Map<String,Object> param){
        // 创建一个可变字符串，用来拼接sql  1=1目的就是让where在没有拼接其他参数时也可以使用
        StringBuilder sql = new StringBuilder("select * from goods where 1=1 ");
        // 创建一个集合，用于保存对应的数据
        ArrayList<Object> paramValues = new ArrayList<>();

        for (String name : param.keySet()){
            // 判断当前name的具体值，再根据值添加拼接sql语句
            // gname需要模糊查询，gprice需要小于等于，gstock需要等于
            switch (name){
                case "gname":
                    sql.append(" and gname like ? ");
                    // 先获取对应的数据
                    Object obj = param.get(name);
                    // 拼接值为包含模式
                    paramValues.add("%"+obj+"%");
                    break;
                case "gprice":
                    sql.append(" and gprice <= ? ");
                    paramValues.add(param.get(name));
                    break;
                case "gstock":
                    sql.append(" and gstock = ?");
                    paramValues.add(param.get(name));
                    break;
            }
        }
        return template.query(sql.toString(), new BeanPropertyRowMapper<>(Goods.class),paramValues.toArray());
    }

    @Override
    public boolean updateGoodsBy(Goods goods) {
        String sql = "update goods set gname=?,gprice=?,gstock=?,maintainDate=?,gdescribe=? where gid=?";
        int row = template.update(sql,goods.getGname(),
                goods.getGprice(),
                goods.getGstock(),
                goods.getMaintainDate(),
                goods.getGdescribe(),
                goods.getGid());
        return row > 0;
    }
}
