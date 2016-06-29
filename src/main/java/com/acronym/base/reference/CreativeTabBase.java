package com.acronym.base.reference;

import com.acronym.base.Base;
import com.acronym.base.api.materials.registries.MaterialRegistry;
import com.acronym.base.items.BaseItems;
import com.google.common.base.Stopwatch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jared on 6/27/2016.
 */
public class CreativeTabBase extends CreativeTabs {

    Stopwatch watch = Stopwatch.createUnstarted();

    int iconIndex = -1;

    public CreativeTabBase() {
        super(12, "B.A.S.E");
    }


    private void updateIcon() {
        if (!watch.isRunning()) {
            watch.reset();
            watch.start();
        }

        if (Base.proxy.isClient() && this.watch.elapsed(TimeUnit.MILLISECONDS) >= 3000L) {
            Random random = new Random();
            this.watch.reset();
            this.watch.start();
            iconIndex = random.nextInt(MaterialRegistry.getMaterials().size());
        }

    }

    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {

        updateIcon();

        if (iconIndex != -1) {
            return new ItemStack(BaseItems.GEAR, 1, iconIndex);
        } else {
            return new ItemStack(BaseItems.GEAR, 1);
        }

    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return this.getIconItemStack().getItem();
    }
}