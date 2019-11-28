package io.eberlein.apyide.settings;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    private List<Setting> settings;

    public Settings(){
        settings = new ArrayList<>();
        settings.add(new TermuxSetting());
        settings.add(new StorageSetting());
        settings.add(new CodeStylesSetting());
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public Setting getSetting(int index){
        return settings.get(index);
    }

    public int getSize(){
        return settings.size();
    }
}
