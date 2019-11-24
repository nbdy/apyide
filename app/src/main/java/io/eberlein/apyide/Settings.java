package io.eberlein.apyide;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

class Setting {
    protected String name;
    protected Fragment fragment;

    Setting(){

    }

    void open(Fragment c){
        Utils.replaceFragment(R.id.nav_host_fragment, c, fragment);
    }

    public String getName() {
        return name;
    }

    public Fragment getFragment() {
        return fragment;
    }
}

class TermuxSetting extends Setting {
    TermuxSetting(){
        name = "termux";
        fragment = null; // todo TermuxSettingFragment
    }
}

class StorageSetting extends Setting {
    StorageSetting(){
        name = "storage";
        fragment = null; // todo StorageSettingFragment
    }
}

class CodeStyleSetting extends Setting {
    CodeStyleSetting(){
        name = "code style";
        fragment = null; // todo
    }
}


public class Settings {
    private List<Setting> settings;

    Settings(){
        settings = new ArrayList<>();
        settings.add(new TermuxSetting());
        settings.add(new StorageSetting());
        settings.add(new CodeStyleSetting());
    }

    public List<Setting> getSettings() {
        return settings;
    }

    Setting getSetting(int index){
        return settings.get(index);
    }

    int getSize(){
        return settings.size();
    }
}
