package edu.ucsb.cs.cs184.omercohen.omercohendemosuite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CustomFragmentsPagerAdapter mCustomFragmentsPager;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private int[] tabIcons = {
            R.drawable.ic_menu_share,
            R.drawable.warning,
            R.drawable.ic_menu_slideshow,
            R.drawable.ic_menu_manage,
            android.R.drawable.ic_btn_speak_now
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomFragmentsPager = new CustomFragmentsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.fargmentsPager);
        mTabLayout = (TabLayout) findViewById(R.id.fragmentTabs);

//        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"));

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setupViewPager(mViewPager);
        setupTabIcons();

        //A toast to show your package name for us
        Toast.makeText(this, getPackageName(this), Toast.LENGTH_LONG).show();
    }

    private void setupTabIcons() {
        for(int i =0; i< mTabLayout.getTabCount();i++){
            mTabLayout.getTabAt(i).setIcon(tabIcons[i]);
            int tabIconColor = ContextCompat.getColor(this, R.color.colorwhite);
            mTabLayout.getTabAt(i).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        }
    }

    private  void setupViewPager(ViewPager pager){
        CustomFragmentsPagerAdapter pagerAdapter = new CustomFragmentsPagerAdapter(getSupportFragmentManager());

        //Web comic fragment
        ExampleFragment exampleFragment = new ExampleFragment();
        pagerAdapter.addFragment(exampleFragment,"Browser");

        FireworkFragment fw = new FireworkFragment();
        pagerAdapter.addFragment(fw, "Firework");

        VideoPlayBackFragment vidPlayBack = new VideoPlayBackFragment();
        pagerAdapter.addFragment(vidPlayBack, "VideoPlayBack");

        //TextToSpeech fragment
        TextToSpeechFragment textToSpeech = new TextToSpeechFragment();
        pagerAdapter.addFragment(textToSpeech, "TextToSpeech");

        //SpeechToText fragment
        SpeechToTextFragment speechToText = new SpeechToTextFragment();
        pagerAdapter.addFragment(speechToText, "SpeechToText");


        pager.setAdapter(pagerAdapter);



    }

    // a function to retrieve the package name
    public static String getPackageName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo.packageName;
    }
}
