package me.rayzr522.minecraftmarkdown;

import com.google.common.eventbus.Subscribe;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = MinecraftMarkdown.MODID, version = MinecraftMarkdown.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class MinecraftMarkdown {
    public static final String MODID = "minecraftmarkdown";
    public static final String VERSION = "1.0.0";

    private CharReplacer replacer = new CharReplacer();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        replacer.addReplacer("**", 'l');
        replacer.addReplacer("*", 'o');
        replacer.addReplacer("__", 'n');
        replacer.addReplacer("_", 'o');
        replacer.addReplacer("~~", 'm');

        System.out.println("Added replacers");

        MinecraftForge.EVENT_BUS.register(this);

        System.out.println("Registered events");
    }

    @SubscribeEvent
    public void onChat(ServerChatEvent event) {
        String text = event.getComponent().getFormattedText();
        String formatted = replacer.translate(text);

        System.out.println("Received message.");
        System.out.println(String.format("Text: %s\nFormatted: %s", text, formatted));

        event.setComponent(new TextComponentString(formatted));
    }
}
