package dev.wnuke.NukeClient.module;

import com.google.common.eventbus.Subscribe;
import dev.wnuke.NukeClient.NukeClient;
import net.minecraft.client.Minecraft;

import java.lang.reflect.Method;

public class Module {
    protected Minecraft mc = Minecraft.getInstance();
    private String name;
    private boolean enabled;
    private Category category;
    private String desc;

    public Module(String name, Category category, String description) {
        this.name = name;
        this.category = category;
        desc = description;
        enabled = false;
    }


    public void toggle() {
        enabled = !enabled;
        if(enabled) onEnable();
        else onDisable();
    }

    public void onEnable() {
        for(Method method : getClass().getMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                NukeClient.eventBus.register(this);
                break;
            }
        }
    }

    public void onDisable() {
        try{
            for(Method method : getClass().getMethods()) {
                if (method.isAnnotationPresent(Subscribe.class)) {
                    NukeClient.eventBus.unregister(this);
                    break;
                }
            }
        }catch(Exception this_didnt_get_registered_hmm_weird) { this_didnt_get_registered_hmm_weird.printStackTrace(); }
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getDesc() {
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isToggled() {
        return enabled;
    }

    public void setToggled(boolean toggled) {
        this.enabled = toggled;
        if(toggled) onEnable();
        else onDisable();
    }
}
