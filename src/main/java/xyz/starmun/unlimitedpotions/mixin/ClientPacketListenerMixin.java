package xyz.starmun.unlimitedpotions.mixin;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.world.effect.MobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.starmun.unlimitedpotions.IUPClientboundUpdateMobEffectPacketExtensions;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @ModifyVariable(method = "handleUpdateMobEffect", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/effect/MobEffect;byId(I)Lnet/minecraft/world/effect/MobEffect;", shift = At.Shift.AFTER))
    public MobEffect test(MobEffect value, ClientboundUpdateMobEffectPacket packet){
        return MobEffect.byId(((IUPClientboundUpdateMobEffectPacketExtensions)packet).getSubstituteEffectId());
    }
}
