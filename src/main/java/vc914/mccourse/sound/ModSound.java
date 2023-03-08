package vc914.mccourse.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vc914.mccourse.MCCourseMod;

public class ModSound {
   public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
           DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MCCourseMod.MOD_ID);

    public static RegistryObject<SoundEvent> DOWSING_ROD_FOUND_ORE
            = registrySoundEvent("dowsing_rod_found_ore");

    public static RegistryObject<SoundEvent> COBALT_LAMP_BREAK = registrySoundEvent("cobalt_lamp_break");
    public static RegistryObject<SoundEvent> COBALT_LAMP_STEP = registrySoundEvent("cobalt_lamp_step");
    public static RegistryObject<SoundEvent> COBALT_LAMP_PLACE =  registrySoundEvent("cobalt_lamp_place");
    public static RegistryObject<SoundEvent> COBALT_LAMP_HIT =  registrySoundEvent("cobalt_lamp_hit");
    public static RegistryObject<SoundEvent> COBALT_LAMP_FALL = registrySoundEvent("cobalt_lamp_fall");
    public static RegistryObject<SoundEvent> BAR_BRAWL = registrySoundEvent("bar_brawl");

    public static final ForgeSoundType COBALT_LAMP_SOUNDS = new ForgeSoundType(1f,1f,
            ModSound.COBALT_LAMP_BREAK, ModSound.COBALT_LAMP_STEP, ModSound.COBALT_LAMP_PLACE,
            ModSound.COBALT_LAMP_HIT, ModSound.COBALT_LAMP_FALL);

    private static RegistryObject<SoundEvent> registrySoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(MCCourseMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> new SoundEvent(id));
    }

    public static void register(IEventBus eventBus) {
       SOUND_EVENTS.register(eventBus);
   }
}
