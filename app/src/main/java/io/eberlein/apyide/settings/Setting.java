package io.eberlein.apyide.settings;

import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.FragmentUtils;

public class Setting {
    protected String name;
    protected Fragment fragment;

    public Setting(){}

    public void open(Fragment c){
        FragmentUtils.replace(c, fragment, true);
    }

    public String getName() {
        return name;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
