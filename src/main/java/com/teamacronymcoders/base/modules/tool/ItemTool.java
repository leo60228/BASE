package com.teamacronymcoders.base.modules.tool;

import com.teamacronymcoders.base.Capabilities;
import com.teamacronymcoders.base.api.ITool;
import com.teamacronymcoders.base.items.IHasRecipe;
import com.teamacronymcoders.base.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemTool extends ItemBase implements IHasRecipe {

    public ItemTool() {
        super("wrench");
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setMaxStackSize(1);
    }

    @Override
    @Nonnull
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new CapabilityProvider();
    }

    public static class CapabilityProvider implements ICapabilityProvider {
        private ITool spanner;

        public CapabilityProvider() {
            this(new ITool(){});
        }

        public CapabilityProvider(ITool cap) {
            this.spanner = cap;
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == Capabilities.TOOL;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return capability == Capabilities.TOOL ? Capabilities.TOOL.cast(spanner) : null;
        }
    }

    @Override
    public List<IRecipe> getRecipes(List<IRecipe> recipes) {
        recipes.add(new ShapedOreRecipe(this, "I I", "ISI", " S ", 'I', "ingotIron", 'S', "stickWood"));
        return recipes;
    }

}
