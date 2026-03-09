package com.hykaika.health_warning.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Health_warningClient implements ClientModInitializer {
    private boolean warningSent = false;

    @Override
    public void onInitializeClient() {
        System.out.println("Health Warning Mod mit AMBOSS aktiv!");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClientPlayerEntity player = client.player;

            if (player != null) {
                float health = player.getHealth();


                if (health <= 6.0f && !warningSent) {

                    player.sendMessage(Text.literal("⚠ Warning! You only have 3 hearts left! Heal yourself so you don't die.⚠")
                            .formatted(Formatting.RED, Formatting.BOLD), false);


                    player.playSound(SoundEvents.BLOCK_ANVIL_LAND, 1.0f, 1.0f);

                    warningSent = true;
                }

                else if (health > 6.0f) {
                    warningSent = false;
                }
            }
        });
    }
}