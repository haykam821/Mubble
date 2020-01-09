package hugman.mubble.objects.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ShakeInstrumentItem extends InstrumentItem
{
    public ShakeInstrumentItem(Item.Settings builder, SoundEvent soundIn)
    {
        super(builder, soundIn);
    }
    
    @Override
    public boolean useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand)
    {
    	player.playSound(getInstrumentSound(), 0.5F, 1F);
    	player.incrementStat(Stats.USED.getOrCreateStat(this));
    	return super.useOnEntity(stack, player, entity, hand);
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
    	playerIn.swingHand(handIn);
		return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerIn.getStackInHand(handIn));
    }
}
