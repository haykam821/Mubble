package hugman.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hugman.mod.init.elements.MubbleBlocks;
import hugman.mod.init.elements.MubbleCostumes;
import hugman.mod.init.elements.MubbleEntities;
import hugman.mod.init.elements.MubbleItems;
import hugman.mod.init.elements.MubbleSounds;
import hugman.mod.init.technical.MubbleColorMaps;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(Mubble.MOD_ID)
public class Mubble 
{
	public static final String MOD_ID = "mubble";
	public static final String MOD_PREFIX = MOD_ID + ":";
	private static final Logger LOGGER = LogManager.getLogger();
	
	
    public Mubble()
    {        
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }
    
    private void clientSetup(final FMLClientSetupEvent event)
    {
    	MubbleEntities.registerRenders();
    }
	
    public static Logger getLogger()
    {
        return LOGGER;
    }
    
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeRegistryEvents
    {
    	@SubscribeEvent
        public static void blockColorsRegistry(final ColorHandlerEvent.Block event)
        {
        	MubbleColorMaps.registerBlockColors(event);
        }
        
    	@SubscribeEvent
        public static void itemColorsRegistry(final ColorHandlerEvent.Item event)
        {
        	MubbleColorMaps.registerItemColors(event);
        }
    }
    
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ModRegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event)
        {
        	event.getRegistry().registerAll(MubbleBlocks.BLOCKS.toArray(new Block[0]));
        }
        
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event)
        {
        	event.getRegistry().registerAll(MubbleItems.ITEMS.toArray(new Item[0]));
    		event.getRegistry().registerAll(MubbleCostumes.COSTUMES.toArray(new Item[0]));
        }
        
        @SubscribeEvent
        public static void onSoundsRegistry(final RegistryEvent.Register<SoundEvent> event)
        {
        	event.getRegistry().registerAll(MubbleSounds.SOUNDS.toArray(new SoundEvent[0]));
        }
        
        @SubscribeEvent
        public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> event)
        {
        	IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        	MubbleEntities.registerEntity(registry);
        }
    }
}