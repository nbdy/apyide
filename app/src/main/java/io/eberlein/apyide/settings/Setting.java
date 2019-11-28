package io.eberlein.apyide.settings;

import androidx.fragment.app.Fragment;

import io.eberlein.apyide.R;
import io.eberlein.apyide.Utils;

public class Setting {
    protected String name;
    protected Fragment fragment;

    public Setting(){

    }

    public void open(Fragment c){
        Utils.replaceFragment(R.id.nav_host_fragment, c, fragment);
    }

    public String getName() {
        return name;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
