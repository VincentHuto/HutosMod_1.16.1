package com.huto.hutosmod.gui.mindrunes;

import javax.annotation.Nullable;

import com.huto.hutosmod.containers.mindrunes.PlayerExpandedContainer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class GuiProvider implements INamedContainerProvider {

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("PlayerRuneInv");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new PlayerExpandedContainer(id, playerInventory, !playerEntity.world.isRemote);
    }
}