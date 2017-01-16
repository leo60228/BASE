package com.teamacronymcoders.base.modules.botania;

import com.teamacronymcoders.base.modulesystem.Module;
import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModDependency;
import com.teamacronymcoders.base.util.Platform;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

import static com.teamacronymcoders.base.reference.Reference.MODID;

@Module(MODID)
public class BotaniaModule extends ModuleBase {
    @Override
    public String getName() {
        return "Botania";
    }

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        dependencies.add(new ModDependency("Botania", true));
        return dependencies;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if(Platform.isClient()) {
            MinecraftForge.EVENT_BUS.register(new EntityWandHUDHandler());
        }
    }
}
