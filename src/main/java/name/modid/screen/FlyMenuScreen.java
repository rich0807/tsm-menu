package name.modid.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class FlyMenuScreen extends Screen {
    private final Screen parent;

    public FlyMenuScreen(Screen parent) {
        super(Text.literal("飛行子選單"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int y = this.height / 4;
        int x = this.width / 2 - 75;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("飛行商店"), btn -> runCommand("/tfly shop"))
                .dimensions(x, y, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("飛行開關"), btn -> runCommand("/tfly"))
                .dimensions(x, y + 24, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("返回主選單"), btn -> 
            MinecraftClient.getInstance().setScreen(parent)
        ).dimensions(x, y + 64, 150, 20).build());
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