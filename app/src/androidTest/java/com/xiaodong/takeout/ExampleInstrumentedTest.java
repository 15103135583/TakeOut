package com.xiaodong.takeout;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.j256.ormlite.dao.Dao;
import com.xiaodong.takeout.model.dao.DBHelper;
import com.xiaodong.takeout.model.dao.bean.AddressBean;
import com.xiaodong.takeout.model.dao.bean.UserBean;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.xiaodong.takeout", appContext.getPackageName());
    }

    //测试创建数据库是否成功===success
    @Test
    public void testDb() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        DBHelper helper = new DBHelper(appContext);helper.getWritableDatabase();
    }


    //测试用户数据===success
    @Test
    public void testUser() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        DBHelper helper = new DBHelper(appContext);
        Dao<UserBean, Integer> dao = helper.getDao(UserBean.class);
        UserBean data1 = new UserBean();
        data1.setId(1);
        dao.create(data1);

        UserBean data2 = new UserBean();
        data2.setId(2);
        dao.create(data2);
    }

    //添加用户地址信息  == success
    @Test
    public void testUserAddress() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        DBHelper helper = new DBHelper(appContext);
        Dao<AddressBean, Integer> dao = helper.getDao(AddressBean.class);
        UserBean data1 = new UserBean();
        data1.setId(2);

        for (int i = 0; i < 10; i++) {
            AddressBean addressBean = new AddressBean();
            addressBean.setId(i);
            addressBean.setGoodsAddress("理想上城 "+ i +" 号楼");
            addressBean.setVillage("第 "+i+" 层");
            addressBean.setUserBean(data1);
            dao.create(addressBean);
        }
    }

    //查询用户
    @Test
    public void testFindUser() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        DBHelper helper = new DBHelper(appContext);
        Dao<UserBean, Integer> dao = helper.getDao(UserBean.class);
        UserBean userBean = dao.queryForId(1);
        System.out.println(userBean.toString());
    }
}
