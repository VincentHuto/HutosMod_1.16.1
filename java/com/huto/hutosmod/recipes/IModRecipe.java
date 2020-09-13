package com.huto.hutosmod.recipes;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

/**
 * Implementation detail, you shouldn't need to use this.
 */
public interface IModRecipe {
    List<Ingredient> getInputs();
    ItemStack getOutput();
    ResourceLocation getId();
}