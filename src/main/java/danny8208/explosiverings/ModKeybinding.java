package danny8208.explosiverings;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class ModKeybinding {
    public static final KeyBinding EJECT_BALL = new KeyBinding("key.explosiverings.eject_ball", KeyConflictContext.IN_GAME, KeyModifier.NONE, InputMappings.getInputByCode(GLFW.GLFW_KEY_RIGHT_BRACKET, 0), "key.categories.explosoverings");

    public static void init() {
        ClientRegistry.registerKeyBinding(EJECT_BALL);
    }
}
