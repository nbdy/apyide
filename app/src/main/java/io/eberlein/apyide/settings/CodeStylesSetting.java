package io.eberlein.apyide.settings;

import io.eberlein.apyide.ui.CodeStylesSettingsFragment;

public class CodeStylesSetting extends Setting {
    public CodeStylesSetting(){
        name = "code style";
        fragment = new CodeStylesSettingsFragment();
    }
}
