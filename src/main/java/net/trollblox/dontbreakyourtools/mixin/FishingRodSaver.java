package net.trollblox.dontbreakyourtools.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.trollblox.dontbreakyourtools.DontBreakYourTools;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public abstract class FishingRodSaver {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private void preventBreakage(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack usedItem = user.getStackInHand(hand);
        if (DontBreakYourTools.isDurabilityOne(usedItem, 5)) {
            cir.setReturnValue(TypedActionResult.fail(usedItem));
            cir.cancel();
        }
    }
}
