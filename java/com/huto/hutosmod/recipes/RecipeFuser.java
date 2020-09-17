
package com.huto.hutosmod.recipes;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;

public class RecipeFuser implements IModRecipe {
	private final ResourceLocation id;
	private final ItemStack output;
	private final ImmutableList<Ingredient> inputs;
	private final float mana;

	public RecipeFuser(ResourceLocation id, ItemStack output, float mana, Ingredient... inputs) {
		Preconditions.checkArgument(inputs.length <= 16);
		Preconditions.checkArgument(mana <= 100000);
		this.id = id;
		this.output = output;
		this.inputs = ImmutableList.copyOf(inputs);
		this.mana = mana;
	}

	public boolean matches(IItemHandler inv) {
		List<Ingredient> ingredientsMissing = new ArrayList<>(inputs);

		for (int i = 0; i < inv.getSlots(); i++) {
			ItemStack input = inv.getStackInSlot(i);
			if (input.isEmpty())
				break;

			int stackIndex = -1;

			for (int j = 0; j < ingredientsMissing.size(); j++) {
				Ingredient ingr = ingredientsMissing.get(j);
				if (ingr.test(input)) {

					stackIndex = j;
					break;
				}
			}

			if (stackIndex != -1)
				ingredientsMissing.remove(stackIndex);
			else
				return false;
		}
		return ingredientsMissing.isEmpty();
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	public List<Ingredient> getInputs() {
		return inputs;
	}

	public ItemStack getOutput() {
		return output;
	}

	public float getManaUsage() {
		return mana;
	}

	public void write(PacketBuffer buf) {
		buf.writeResourceLocation(id);
		buf.writeVarInt(inputs.size());
		for (Ingredient input : inputs) {
			input.write(buf);
		}
		buf.writeItemStack(output, false);
		buf.writeFloat(mana);
	}

	public static RecipeFuser read(PacketBuffer buf) {
		ResourceLocation id = buf.readResourceLocation();
		Ingredient[] inputs = new Ingredient[buf.readVarInt()];
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = Ingredient.read(buf);
		}
		ItemStack output = buf.readItemStack();
		float mana = buf.readFloat();
		return new RecipeFuser(id, output, mana, inputs);
	}

}