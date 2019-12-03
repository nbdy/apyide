package io.eberlein.apyide.settings;

import io.eberlein.apyide.ui.TermuxSettingFragment;

class TermuxSetting extends Setting {
    public TermuxSetting(){
        name = "termux";
        fragment = new TermuxSettingFragment();
    }
}
