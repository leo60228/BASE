package com.teamacronymcoders.base.modules.botania;

import com.teamacronymcoders.base.client.ClientHelper;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.wand.IWandHUD;

public class EntityWandHUDHandler {
    @SubscribeEvent
    public void onRenderOverlayPost(RenderGameOverlayEvent.Post event) {
        RayTraceResult rayTraceResult = ProjectileHelper.forwardsRaycast(ClientHelper.player(), true, true, null);
        if(rayTraceResult != null && rayTraceResult.typeOfHit == Type.ENTITY) {
            if(rayTraceResult.entityHit instanceof IWandHUD) {

            }
        }
    }
}
