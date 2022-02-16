package xyz.starmun.unlimitedpotions.mixin;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.starmun.unlimitedpotions.IUPClientboundUpdateMobEffectPacketExtensions;

@Mixin(ClientboundUpdateMobEffectPacket.class)
public class ClientboundUpdateMobEffectPacketMixin implements IUPClientboundUpdateMobEffectPacketExtensions {
    @Unique
    private int substituteEffectId;

    @Inject(method = "<init>(ILnet/minecraft/world/effect/MobEffectInstance;)V", at=@At("TAIL"))
    public void init(int i, MobEffectInstance arg, CallbackInfo ci){
        this.substituteEffectId = MobEffect.getId(arg.getEffect());// 29
    }
    @Inject(method = "read", at=@At("TAIL"))
    public void read(FriendlyByteBuf arg, CallbackInfo ci){
        this.substituteEffectId = arg.readVarInt();
    }
    @Inject(method = "write", at=@At("TAIL"))
    public void write(FriendlyByteBuf arg, CallbackInfo ci){
        arg.writeByte(this.substituteEffectId);
    }

    @Override
    public int getSubstituteEffectId() {
        return this.substituteEffectId;
    }
}
