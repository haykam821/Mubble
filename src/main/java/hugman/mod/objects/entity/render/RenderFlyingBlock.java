package hugman.mod.objects.entity.render;

import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;

import hugman.mod.objects.entity.EntityFlyingBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class RenderFlyingBlock extends EntityRenderer<EntityFlyingBlock>
{
   public RenderFlyingBlock(EntityRendererManager renderManagerIn)
   {
      super(renderManagerIn);
      this.shadowSize = 0.5F;
   }

   @Override
   public void doRender(EntityFlyingBlock entity, double x, double y, double z, float entityYaw, float partialTicks)
   {
      BlockState iblockstate = entity.getBlockState();
      if (iblockstate.getRenderType() == BlockRenderType.MODEL)
      {
         World world = entity.getWorldObj();
         if (iblockstate != world.getBlockState(new BlockPos(entity)) && iblockstate.getRenderType() != BlockRenderType.INVISIBLE)
         {
            this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            if (this.renderOutlines)
            {
               GlStateManager.enableColorMaterial();
               GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
            }
            bufferbuilder.begin(7, DefaultVertexFormats.BLOCK);
            BlockPos blockpos = new BlockPos(entity.posX, entity.getBoundingBox().maxY, entity.posZ);
            GlStateManager.translatef((float)(x - (double)blockpos.getX() - 0.5D), (float)(y - (double)blockpos.getY()), (float)(z - (double)blockpos.getZ() - 0.5D));
            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
            blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(iblockstate), iblockstate, blockpos, bufferbuilder, false, new Random(), iblockstate.getPositionRandom(entity.getOrigin()), EmptyModelData.INSTANCE);
            tessellator.draw();
            if (this.renderOutlines)
            {
               GlStateManager.tearDownSolidRenderingTextureCombine();
               GlStateManager.disableColorMaterial();
            }
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
         }
      }
   }
   
   @Override
   protected ResourceLocation getEntityTexture(EntityFlyingBlock entity)
   {
      return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
   }
}