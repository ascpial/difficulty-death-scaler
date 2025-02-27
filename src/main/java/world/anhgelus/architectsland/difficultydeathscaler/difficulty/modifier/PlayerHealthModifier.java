package world.anhgelus.architectsland.difficultydeathscaler.difficulty.modifier;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public abstract class PlayerHealthModifier extends Modifier<ServerPlayerEntity> {
    public static final RegistryEntry<EntityAttribute> ATTRIBUTE = EntityAttributes.MAX_HEALTH;
    public static final EntityAttributeModifier.Operation OPERATION = EntityAttributeModifier.Operation.ADD_VALUE;

    public PlayerHealthModifier(Identifier id) {
        super(id, ATTRIBUTE, OPERATION);
    }

    @Override
    public void update(double newValue) {
        if (newValue < value) value = newValue;
    }

    public static void apply(ServerPlayerEntity player, Identifier id, double value) {
        apply(id, ATTRIBUTE, OPERATION, player, value);
    }
}