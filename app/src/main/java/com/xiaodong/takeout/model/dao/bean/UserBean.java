package com.xiaodong.takeout.model.dao.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Think on 2017/1/27.
 * 用户地址列表的数据库表,提供set和get方法来设置和获取数据
 */

@DatabaseTable(tableName = "t_user")
public class UserBean {
    @DatabaseField(columnName = "_id",id = true) //columnName : 列的名字,如果不设置由当前属性名称当做列名
    private int _id;

    //需要一个集合来存放当前用户所有的地址列表信息
    @ForeignCollectionField(eager = true)
    private ForeignCollection<AddressBean> addressList;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public ForeignCollection<AddressBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(ForeignCollection<AddressBean> addressList) {
        this.addressList = addressList;
    }

    public UserBean() {
    }
}
