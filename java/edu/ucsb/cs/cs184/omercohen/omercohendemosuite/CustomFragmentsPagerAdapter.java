package edu.ucsb.cs.cs184.omercohen.omercohendemosuite;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomFragmentsPagerAdapter extends FragmentStatePagerAdapter  {

    private final List<Fragment> customFragmentList = new ArrayList<>();
    private final List<String> customFragmentTitles = new ArrayList<>();

    public CustomFragmentsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public  void  addFragment(Fragment fragment, String title)
    {
        customFragmentList.add(fragment);
        customFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return customFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return customFragmentList.size();
    }
}
