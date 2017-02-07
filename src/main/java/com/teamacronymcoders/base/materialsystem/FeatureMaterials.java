package com.teamacronymcoders.base.materialsystem;

import com.teamacronymcoders.base.featuresystem.FeatureHandler;
import com.teamacronymcoders.base.featuresystem.IFeature;
import com.teamacronymcoders.base.modules.minetweaker.Materials;

public class FeatureMaterials implements IFeature {
    boolean active;

    @Override
    public void activate() {
        MaterialsSystem.setup();
        FeatureHandler.requestFeature("SUB_BLOCKS");
        this.active = true;
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
