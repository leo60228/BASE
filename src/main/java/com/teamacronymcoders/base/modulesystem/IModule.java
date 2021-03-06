package com.teamacronymcoders.base.modulesystem;

import com.teamacronymcoders.base.IBaseMod;
import com.teamacronymcoders.base.modulesystem.dependencies.IDependency;
import com.teamacronymcoders.base.modulesystem.proxies.IModuleProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface IModule extends Comparable<IModule> {
    String getName();

    List<IDependency> getDependencies(List<IDependency> dependencies);

    void preInit(FMLPreInitializationEvent event);

    void afterModulesPreInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    @Nullable
    String getClientProxyPath();

    @Nullable
    String getServerProxyPath();

    @Nullable
    IModuleProxy getModuleProxy();

    void setModuleProxy(IModuleProxy moduleProxy);

    boolean getIsActive();

    void setIsActive(Boolean isActive);

    boolean getActiveDefault();

    boolean isConfigurable();

    IBaseMod getMod();

    void setMod(@Nonnull IBaseMod mod);

    void setModuleHandler(@Nonnull ModuleHandler moduleHandler);
}
