package name.modid.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class TeleportMenuScreen extends Screen {
    private final Screen parent;

    public TeleportMenuScreen(Screen parent) {
        super(Text.literal("傳送子選單"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int y = this.height / 4;
        int x = this.width / 2 - 75;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("鑰匙"), btn -> runCommand("/warp crates"))
                .dimensions(x, y, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("出生點"), btn -> runCommand("/spawn"))
                .dimensions(x, y + 24, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("小鎮"), btn -> runCommand("/g home"))
                .dimensions(x, y + 48, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("蘑菇島"), btn -> runCommand("/pw 吉米鎮蘑菇島"))
                .dimensions(x, y + 72, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("上個地點"), btn -> runCommand("/back"))
                .dimensions(x, y + 96, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("home點"), btn -> runCommand("/home"))
                .dimensions(x, y + 120, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("返回主選單"), btn -> 
            MinecraftClient.getInstance().setScreen(parent)
        ).dimensions(x, y + 160, 150, 20).build());
    }

    private void runCommand(String command) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            client.player.networkHandler.sendChatCommand(command.replaceFirst("/", ""));
        }
        client.setScreen(null);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}