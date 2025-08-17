package name.modid.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class MainMenuScreen extends Screen {
    public MainMenuScreen() {
        super(Text.literal("主選單"));
    }

    @Override
    protected void init() {
        int y = this.height / 4;
        int x = this.width / 2 - 75;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("傳送"), btn -> 
            MinecraftClient.getInstance().setScreen(new TeleportMenuScreen(this))
        ).dimensions(x, y, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("公會"), btn -> 
            MinecraftClient.getInstance().setScreen(new GuildMenuScreen(this))
        ).dimensions(x, y + 24, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("飛行"), btn -> 
            MinecraftClient.getInstance().setScreen(new FlyMenuScreen(this))
        ).dimensions(x, y + 48, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("等級"), btn -> 
            runCommand("/levels")
        ).dimensions(x, y + 72, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("任務"), btn -> 
            runCommand("/quests")
        ).dimensions(x, y + 96, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("垃圾桶"), btn -> 
            runCommand("/disposal")
        ).dimensions(x, y + 120, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("背包"), btn -> 
            runCommand("/backpack")
        ).dimensions(x, y + 144, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("終界箱"), btn -> 
            runCommand("/echest")
        ).dimensions(x, y + 168, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("拍賣"), btn -> 
            runCommand("/ah")
        ).dimensions(x, y + 192, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("合成"), btn -> 
            runCommand("/craft")
        ).dimensions(x, y + 216, 150, 20).build());
    }

    private void runCommand(String command) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            client.player.networkHandler.sendChatCommand(command.replaceFirst("/", ""));
        }
        client.setScreen(null); // 關閉GUI
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}