package com.huto.forcesofreality.entity.summon;

import javax.annotation.Nullable;

import com.huto.forcesofreality.entity.guardian.EntityHastur;
import com.huto.forcesofreality.entity.lord.EntityTrueXanthousKing;
import com.huto.forcesofreality.entity.lord.EntityXanthousKing;
import com.huto.forcesofreality.sound.SoundHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class EntityEldritchGrip extends MonsterEntity {
	public float deathTicks = 1;

	public EntityEldritchGrip(EntityType<? extends EntityEldritchGrip> type, World worldIn) {
		super(type, worldIn);

	}

	@Override
	protected float getSoundVolume() {
		return 0.3f;
	}

	@Override
	protected void registerData() {
		super.registerData();

	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			@Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		return spawnDataIn;

	}

	@Override
	public boolean canRenderOnFire() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		return false;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return super.getRenderBoundingBox().grow(10);
	}

	@Override
	public boolean canBeHitWithPotion() {
		return false;
	}

	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}

	@Override
	public boolean canSwim() {
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return super.getBoundingBox();
	}

	@Override
	public void tick() {
		super.tick();

		// Particle Effects
		float f = (this.rand.nextFloat() - 0.5F) * 1.5F;
		float f1 = -1;
		float f3 = (this.rand.nextFloat() - 0.5F) * 1.5F;
		float f2 = (this.rand.nextFloat() - 0.5F) * 1.5F;
		if (this.ticksExisted > 0) {
			for (int j = 0; j < 10; j++) {
				this.world.addParticle(ParticleTypes.SMOKE, this.getPosX() + (double) f * 1.6,
						this.getPosY() + 2.1D + (double) f1 * 1.6, this.getPosZ() + (double) f2 * 1.6, 0.0D, 0.0D,
						0.0D);
			}
		}
		if (this.ticksExisted > 15) {
			for (int i = 0; i < 2; i++) {
				this.world.addParticle(ParticleTypes.SMOKE, this.getPosX() + (double) f * 0.5, this.getPosY(),
						this.getPosZ() + (double) f2 * 0.5, 0.0D, 0.0D, 0.0D);
				this.world.addParticle(ParticleTypes.ASH, this.getPosX() + (double) f,
						this.getPosY() + (0.0D + i) + (double) f3, this.getPosZ() + (double) f2, 0.0D, 0.0D, 0.0D);

				this.world.addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT, this.getPosX() + (double) f,
						this.getPosY() + (0.0D + i) + (double) f3, this.getPosZ() + (double) f2, 0.0D, 0.0D, 0.0D);
			}
		}
		if (this.ticksExisted == 200) {
			this.world.addParticle(ParticleTypes.SMOKE, this.getPosX() + (double) f,
					this.getPosY() + 2.0D + (double) f1, this.getPosZ() + (double) f2, 0.0D, 0.0D, 0.0D);
			if (!this.world.isRemote) {
				this.setHealth(0);
			} else {
				if (!world.isRemote) {
					world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(),
							SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.HOSTILE, 3f, 1.2f, false);
				}
			}
		}
	}

	@Override
	protected void onDeathUpdate() {
		// Particle Effects
		float g = (this.rand.nextFloat() - 0.5F) * 2.0F;
		float g1 = -1;
		float g2 = (this.rand.nextFloat() - 0.5F) * 2.0F;
		deathTicks -= 0.05;
		if (this.deathTicks <= 0.1) {
			if (world.isRemote) {
				world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
						SoundCategory.HOSTILE, 3f, 0.2f, false);
				this.world.addParticle(ParticleTypes.SMOKE, this.getPosX() + (double) g,
						this.getPosY() + 2.0D + (double) g1, this.getPosZ() + (double) g2, 0.0D, 0.0D, 0.0D);
			}
		}

		if (this.deathTicks <= 0.1 && !this.world.isRemote) {
			this.remove();
		}

	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		super.onCollideWithPlayer(entityIn);
		// entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.5f);
		entityIn.setMotion(0, -0.5f, 0);
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		super.collideWithEntity(entityIn);
		if (!(entityIn instanceof EntityTentacle || entityIn instanceof EntityHastur|| entityIn instanceof EntityXanthousKing|| entityIn instanceof EntityTrueXanthousKing)) {
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 2));

		}

	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}

	public static AttributeModifierMap.MutableAttribute setAttributes() {
		return LivingEntity.registerAttributes().createMutableAttribute(Attributes.FOLLOW_RANGE, 16.0D)
				.createMutableAttribute(Attributes.ATTACK_KNOCKBACK)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0f)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1f);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundHandler.ENTITY_TENTACLE_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundHandler.ENTITY_TENTACLE_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundHandler.ENTITY_TENTACLE_HURT;
	}
}
