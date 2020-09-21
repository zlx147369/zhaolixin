package com.example.crazyshopping;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.crazyshopping.ui.classification.ClassificationFragment;
import com.example.crazyshopping.ui.home.HomeFragment;
import com.example.crazyshopping.ui.my.MyFragment;
import com.example.crazyshopping.ui.shoppingcart.CartFragment;
import com.example.crazyshopping.ui.special.SpecialFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Toolbar tool;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
            Toolbar tool = findViewById(R.id.tool);
            setSupportActionBar(tool);

            mViewPager = (ViewPager) findViewById(R.id.mViewPager);
            mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);



            List<Fragment> fragments = Arrays.asList(
                    new HomeFragment(),new SpecialFragment(),new ClassificationFragment(),new CartFragment(),new MyFragment()
            );

            List<String> title = Arrays.asList(
                    "首页", "专题", "分类", "购物车","我的"
            );


            mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragments.get(position);
                }

                @Override
                public int getCount() {
                    return fragments.size();
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return title.get(position);
                }
            });

            mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("首页").setIcon(R.drawable.xuanze1).select();
        mTabLayout.getTabAt(1).setText("专题").setIcon(R.drawable.xuanze2);
        mTabLayout.getTabAt(2).setText("分类").setIcon(R.drawable.xuanze3);
        mTabLayout.getTabAt(3).setText("购物车").setIcon(R.drawable.xuanze4);
        mTabLayout.getTabAt(4).setText("我的").setIcon(R.drawable.xuanze5);
        }
}