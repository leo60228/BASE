package com.teamacronymcoders.base.modulesystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.dependencies.ModuleDependency;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import com.teamacronymcoders.base.registrysystem.BlockRegistry;
import com.teamacronymcoders.base.registrysystem.EntityRegistry;
import com.teamacronymcoders.base.registrysystem.IRegistryHolder;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import com.teamacronymcoders.base.util.Platform;
import com.teamacronymcoders.base.util.logging.ILogger;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class ModuleBase implements IModule {
    private boolean isActive = true;
    private ModuleHandler moduleHandler;
    private IRegistryHolder registryHolder;
    private IBaseMod mod;
    private IModuleProxy moduleProxy;

    @Override
    public List<IDependency> getDependencies(List<IDependency> dependencies) {
        return dependencies;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        this.configure(this.getConfigRegistry());
        this.registerBlocks(this.getConfigRegistry(), this.getBlockRegistry());
        this.registerItems(this.getConfigRegistry(), this.getItemRegistry());
        this.registerEntities(this.getConfigRegistry(), this.getEntityRegistry());
        if (this.getModuleProxy() != null) {
            this.getModuleProxy().preInit(event);
        }
    }

    @Override
    public void afterModulesPreInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {
        if (this.getModuleProxy() != null) {
            this.getModuleProxy().init(event);
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        if (this.getModuleProxy() != null) {
            this.getModuleProxy().postInit(event);
        }
    }

    public void configure(ConfigRegistry configRegistry) {

    }

    public void registerItems(ConfigRegistry configRegistry, ItemRegistry itemRegistry) {

    }

    public void registerBlocks(ConfigRegistry configRegistry, BlockRegistry blockRegistry) {

    }

    public void registerEntities(ConfigRegistry configRegistry, EntityRegistry entityRegistry) {

    }

    @Override
    @Nullable
    public String getClientProxyPath() {
        return "";
    }

    @Override
    @Nullable
    public String getServerProxyPath() {
        return "";
    }

    @Override
    @Nullable
    public IModuleProxy getModuleProxy() {
        return moduleProxy;
    }

    @Override
    public void setModuleProxy(IModuleProxy moduleProxy) {
        this.moduleProxy = moduleProxy;
    }

    @Override
    public boolean getIsActive() {
        return isActive;
    }

    @Override
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public boolean getActiveDefault() {
        return true;
    }

    @Override
    public IBaseMod getMod() {
        return this.mod;
    }

    @Override
    public void setMod(@Nonnull IBaseMod mod) {
        this.registryHolder = mod.getRegistryHolder();
        this.mod = mod;
    }

    @Override
    public void setModuleHandler(@Nonnull ModuleHandler moduleHandler) {
        this.moduleHandler = moduleHandler;
    }

    public ItemRegistry getItemRegistry() {
        return this.registryHolder.getRegistry(ItemRegistry.class, "ITEM");
    }

    public BlockRegistry getBlockRegistry() {
        return this.registryHolder.getRegistry(BlockRegistry.class, "BLOCK");
    }

    public EntityRegistry getEntityRegistry() {
        return this.registryHolder.getRegistry(EntityRegistry.class, "ENTITY");
    }

    public ConfigRegistry getConfigRegistry() {
        return this.registryHolder.getRegistry(ConfigRegistry.class, "CONFIG");
    }

    public ILogger getLogger() {
        return this.mod.getLogger();
    }

    public boolean isOtherModuleActive(String name) {
        return moduleHandler.isModuleEnabled(name);
    }

    @Override
    public int compareTo(@Nonnull IModule module) {
        int result = 0;

        if (module != null) {
            List<IDependency> module1Deps = this.getDependencies(new ArrayList<>());
            List<IDependency> module2Deps = module.getDependencies(new ArrayList<>());

            boolean module1IsDepTo2 = false;
            boolean module2IsDepTo1 = false;

            for (IDependency dependency : module1Deps) {
                if (dependency instanceof ModuleDependency) {
                    module1IsDepTo2 |= ((ModuleDependency) dependency).getModuleName().equals(module.getName());
                }
            }

            for (IDependency dependency : module2Deps) {
                if (dependency instanceof ModuleDependency) {
                    module2IsDepTo1 |= ((ModuleDependency) dependency).getModuleName().equals(this.getName());
                }
            }

            if (module1IsDepTo2 && module2IsDepTo1) {
                Platform.attemptLogErrorToCurrentMod("CIRCULAR DEPENDENCIES FOUND. THINKS MAY GO WRONG");
            } else if (module1IsDepTo2) {
                result = -1;
            } else if (module2IsDepTo1) {
                result = 1;
            }
        } else {
            throw new NullPointerException("Module is null");
        }

        return result;
    }

    public IRegistryHolder getRegistryHolder() {
        return registryHolder;
    }
}
