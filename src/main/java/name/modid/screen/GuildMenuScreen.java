package name.modid.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class GuildMenuScreen extends Screen {
    private final Screen parent;

    public GuildMenuScreen(Screen parent) {
        super(Text.literal("公會子選單"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int y = this.height / 4;
        int x = this.width / 2 - 75;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("公會資訊"), btn -> runCommand("/g info"))
                .dimensions(x, y, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("公會箱子"), btn -> runCommand("/g vault"))
                .dimensions(x, y + 24, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("公會聊天開關"), btn -> runCommand("/g gc"))
                .dimensions(x, y + 48, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("聯盟聊天開關"), btn -> runCommand("/g ac"))
                .dimensions(x, y + 72, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("返回主選單"), btn -> 
            MinecraftClient.getInstance().setScreen(parent)
        ).dimensions(x, y + 112, 150, 20).build());
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