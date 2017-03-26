package jskj.com.naprioridetectclient.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import jskj.com.naprioridetectclient.R;
import jskj.com.naprioridetectclient.fragment.TestFragment;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private String[] appFunctions;
    private TextView mTxtTitle;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
        appFunctions = getResources().getStringArray(R.array.app_func_items);
        initTabLayout();
    }

    private void initTabLayout() {
        mViewPager.setAdapter(new HomeViewPager(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);

        TabLayout.Tab homeTab = mTabLayout.getTabAt(0);
        homeTab.setIcon(R.drawable.home_tab_selected);

        TabLayout.Tab localTab = mTabLayout.getTabAt(1);
        localTab.setIcon(R.drawable.local_tab_selected);

        TabLayout.Tab remoteTab = mTabLayout.getTabAt(2);
        remoteTab.setIcon(R.drawable.remote_tab_selected);

        TabLayout.Tab personTab = mTabLayout.getTabAt(3);
        personTab.setIcon(R.drawable.my_tab_selected);

    }

    private void initView() {
        mTxtTitle = (TextView) findViewById(R.id.txt_title_home);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout_home);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_home);
    }

    class HomeViewPager extends FragmentPagerAdapter {

        public HomeViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new TestFragment();
        }

        @Override
        public int getCount() {
            if(appFunctions == null) {
                return 0;
            }
            return appFunctions.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return appFunctions[position];
        }
    }

}
