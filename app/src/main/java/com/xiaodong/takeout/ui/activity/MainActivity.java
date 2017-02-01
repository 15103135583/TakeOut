package com.xiaodong.takeout.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiaodong.takeout.R;
import com.xiaodong.takeout.ui.fragment.HomeFragment;
import com.xiaodong.takeout.ui.fragment.MoreFragment;
import com.xiaodong.takeout.ui.fragment.OrderFragment;
import com.xiaodong.takeout.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.main_fragment_container)
    FrameLayout mainFragmentContainer;
    @InjectView(R.id.main_bottome_switcher_container)
    LinearLayout mainBottomeSwitcherContainer;
private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
        setListener();
    }

    private void init() {
        fragments.add(new HomeFragment());
        fragments.add(new OrderFragment());
        fragments.add(new UserFragment());
        fragments.add(new MoreFragment());

        childClick.onClick(mainBottomeSwitcherContainer.getChildAt(0));
    }


    /**
     *  需求：完成一个通用底部导航的处理
     */
    private void setListener() {
        //获取容器中所有的子view
        int childCount = mainBottomeSwitcherContainer.getChildCount();

        for (int i = 0; i < childCount; i++) {
            //遍历所有子view并设置点击事件
            FrameLayout view = (FrameLayout) mainBottomeSwitcherContainer.getChildAt(i);
           view.setOnClickListener(childClick);
        }
    }

    private View.OnClickListener childClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //根据当前view在容器中的索引来改变子view的状态
            int index = mainBottomeSwitcherContainer.indexOfChild(v);
            chageUI(index);
            chageFragment(index);
        }
    };

    /**
     * 切换fragment
     * @param index
     */
    private void chageFragment(int index) {
        Fragment fragment = fragments.get(index);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container,fragment)
                .commit();
    }


    /**
     * 根据索引判断子view是否可以被点击
     * @param index  view在容器中的位置
     *  改变Index对应的孩子的状态，包括这个孩子中多有控件的状态（不可用状态：enable=false）
     * 改变其他的孩子的状态，，包括些孩子中多有控件的状态
     */
    private void chageUI(int index) {
        Toast.makeText(this, index+"", Toast.LENGTH_SHORT).show();
        int childCount = mainBottomeSwitcherContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == index){
                setEnable(mainBottomeSwitcherContainer.getChildAt(i),false);
            }else {
                setEnable(mainBottomeSwitcherContainer.getChildAt(i),true);
            }
        }
    }

    /**
     * 设置view是否可以被点击
     * @param item
     * @param b
     */
    private void setEnable(View item, boolean b) {
        item.setEnabled(b);
        if (item instanceof ViewGroup){
            int childCount = ((ViewGroup) item).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setEnable(((ViewGroup) item).getChildAt(i),b);
            }
        }
    }
}
