package dev.wnuke.NukeClient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ChatMessage {
    private static final ClientPlayerEntity player = Minecraft.getInstance().player;
    public static final String NUKE_CLIENT_CHAT_PREFIX = TextFormatting.BLACK + "[" + TextFormatting.YELLOW + TextFormatting.BOLD + "NUKE Client" + TextFormatting.BLACK + "] ";
    public static void sendRawMessage(String message) {
        player.sendMessage(new StringTextComponent(message));
    }
    public static void sendPublicMessage(String message) {
        player.sendChatMessage(message);
    }
    public static void sendMessage(String message) {
        sendRawMessage(NUKE_CLIENT_CHAT_PREFIX + message);
    }
}
