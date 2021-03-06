package com.ibingbo.demo.adapter;

/**
 * Created by zhangbingbing on 16/8/13.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ibingbo.demo.fragment.CardViewFragment;
import com.ibingbo.demo.fragment.PlaceholderFragment;
import com.ibingbo.demo.fragment.RecyclerViewFragment;
import com.ibingbo.demo.fragment.TextSwitcherFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return CardViewFragment.newInstance(position + 1);
            case 1:
                return RecyclerViewFragment.newInstance(position + 1);
            case 2:
                return TextSwitcherFragment.newInstance(position + 1);
            default:
                return PlaceholderFragment.newInstance(position + 1);
        }

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
            case 3:
                return "SECTION 4";
            case 4:
                return "SECTION 5";
        }
        return null;
    }
}
