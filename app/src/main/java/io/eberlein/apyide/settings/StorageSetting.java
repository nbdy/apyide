package io.eberlein.apyide.settings;

import io.eberlein.apyide.ui.StorageSettingFragment;

class StorageSetting extends Setting {
    public StorageSetting(){
        name = "storage";
        fragment = new StorageSettingFragment();
    }
}
