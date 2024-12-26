package world.anhgelus.architectsland.difficultydeathscaler.difficulty.modifier;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class SpawnReinforcementsModifier extends Modifier<HostileEntity> {
    public static final RegistryEntry<EntityAttribute> ATTRIBUTE = EntityAttributes.SPAWN_REINFORCEMENTS;
    public static final EntityAttributeModifier.Operation OPERATION = EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE;
    protected static Identifier ID = Identifier.of(PREFIX + "spawn_reinforcements_modifier");

    public SpawnReinforcementsModifier() {
        super(ID, ATTRIBUTE, OPERATION);
    }

    @Override
    public void update(double newValue) {
        if (newValue > value) value = newValue;
    }

    public static void apply(HostileEntity player, double value) {
        apply(ID, ATTRIBUTE, OPERATION, player, value);
    }
}
