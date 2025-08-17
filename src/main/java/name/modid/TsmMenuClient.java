package name.modid;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import name.modid.screen.MainMenuScreen;
import net.minecraft.client.MinecraftClient;

public class TsmMenuClient implements ClientModInitializer {
    public static KeyBinding openMenuKeyBinding;

    @Override
    public void onInitializeClient() {
        openMenuKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.tsm_menu.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                "category.tsm_menu"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKeyBinding.wasPressed()) {
                client.setScreen(new MainMenuScreen());
            }
        });
    }
}