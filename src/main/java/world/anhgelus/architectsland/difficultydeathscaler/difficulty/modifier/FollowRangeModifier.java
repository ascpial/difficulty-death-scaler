package world.anhgelus.architectsland.difficultydeathscaler.difficulty.modifier;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class FollowRangeModifier extends Modifier<HostileEntity> {
    public static final RegistryEntry<EntityAttribute> ATTRIBUTE = EntityAttributes.FOLLOW_RANGE;
    public static final EntityAttributeModifier.Operation OPERATION = EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE;
    protected static Identifier ID = Identifier.of(PREFIX + "follow_range_modifier");

    public FollowRangeModifier() {
        super(ID, ATTRIBUTE, OPERATION);
    }

    @Override
    public void update(double newValue) {
        if (newValue > value) value = newValue;
    }

    public static void apply(HostileEntity entity, double value) {
        apply(ID, ATTRIBUTE, OPERATION, entity, value);
    }
}