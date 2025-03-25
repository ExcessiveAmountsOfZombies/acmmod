package com.epherical.acmmod.event;

import com.epherical.acmmod.ACMMod;
import com.epherical.acmmod.item.TestItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.UUID;

public class TestListeners {


    public static class PlayerInteractionListener {


        @SubscribeEvent
        public void playerClicksOnMobWithItem(PlayerInteractEvent.EntityInteract event) {
            Player player = event.getEntity();
            Entity target = event.getTarget();

            if (target instanceof Monster && event.getItemStack().getItem() instanceof TestItems.PacificationItem) {
                target.getData(ACMMod.PLAYER_PACIFICATION);
                target.setData(ACMMod.PLAYER_PACIFICATION, player.getUUID());
            } else {
                player.sendSystemMessage(Component.literal("You cannot pacify non monsters!"));
            }
        }
    }

    public static class EntityTargetListener {


        @SubscribeEvent
        public void entityTargetEvent(LivingChangeTargetEvent event) {
            if (event.getEntity() instanceof Monster && event.getNewAboutToBeSetTarget() instanceof Player player) {
                if (event.getEntity().hasData(ACMMod.PLAYER_PACIFICATION)) {
                    UUID data = event.getEntity().getData(ACMMod.PLAYER_PACIFICATION);
                    if (data.equals(player.getUUID())) {
                        event.setCanceled(true);
                    }
                }
            }
        }

    }


}
