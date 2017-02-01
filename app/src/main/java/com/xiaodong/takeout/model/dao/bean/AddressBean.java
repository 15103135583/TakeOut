package com.xiaodong.takeout.model.dao.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Think on 2017/1/27.
 * 用户列表的数据库表,提供set和get方法来设置和获取数据
 */

/*
常用配置说明：
        主键：id = true
        自增主键：generatedId = true
        列名：columnName = "name"
        是否为空：canBeNull = true
        外键：foreign = true
*/
@DatabaseTable(tableName = "t_address")
public class AddressBean {
    @DatabaseField(id = true)//作为主键,
    private int id;

    //收货地址   canbenull : 是否可以为空
    @DatabaseField(canBeNull = false)
    private String goodsAddress;

    //门牌号
    @DatabaseField(canBeNull = false)
    private String village;

    //需要与usetbean表想关联
    //注解参数: 1: 不能为空,2: 作为外键 3: 外键的名称
    @DatabaseField(canBeNull = false,foreign = true,foreignColumnName = "_id")
    private UserBean userBean;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
