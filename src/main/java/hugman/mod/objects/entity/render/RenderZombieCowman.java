package hugman.mod.objects.entity.render;

import hugman.mod.Mubble;
import hugman.mod.objects.entity.EntityZombieCowman;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;

public class RenderZombieCowman extends BipedRenderer<EntityZombieCowman, ZombieModel<EntityZombieCowman>>
{
	private static final ResourceLocation ZOMBIE_COWMAN_TEXTURE = new ResourceLocation(Mubble.MOD_PREFIX + "textures/entity/zombie_cowman.png");
	
	public RenderZombieCowman(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ZombieModel<>(), 0.5F);
		this.addLayer(new BipedArmorLayer<>(this, new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true)));
   }
   
   protected ResourceLocation getEntityTexture(EntityZombieCowman entity)
   {
      return ZOMBIE_COWMAN_TEXTURE;
   }
}