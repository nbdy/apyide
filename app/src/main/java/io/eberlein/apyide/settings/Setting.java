package io.eberlein.apyide.settings;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import io.eberlein.apyide.R;
import io.eberlein.apyide.Utils;

public class Setting {
    protected String name;
    protected Fragment fragment;

    public Setting(){

    }

    public void open(FragmentManager fm){
        Utils.replaceFragment(fm, fragment);
    }

    public String getName() {
        return name;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
