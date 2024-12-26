package world.anhgelus.architectsland.difficultydeathscaler.difficulty.modifier;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import world.anhgelus.architectsland.difficultydeathscaler.difficulty.DifficultyManager;

public abstract class Modifier<T extends LivingEntity> {
    public static final String PREFIX = "dds_";

    protected double value = 0;
    protected final Identifier id;
    protected final RegistryEntry<EntityAttribute> attribute;
    protected final EntityAttributeModifier.Operation operation;

    protected Modifier(Identifier id, RegistryEntry<EntityAttribute> attribute, EntityAttributeModifier.Operation operation) {
        this.id = id;
        this.attribute = attribute;
        this.operation = operation;
    }

    /**
     * Update the value if needed
     *
     * @param newValue newValue
     */
    public abstract void update(double newValue);

    /**
     * Apply modifier to player
     *
     * @param entity Entity to apply the modifier
     */
    public void apply(@Nullable T entity) {
        if (entity == null) return;
        apply(id, attribute, operation, entity, value);
    }

    protected static void apply(
            Identifier id,
            RegistryEntry<EntityAttribute> attribute,
            EntityAttributeModifier.Operation operation,
            LivingEntity entity,
            double value
    ) {
        final var attr = entity.getAttributeInstance(attribute);
        if (attr == null) return;

        attr.removeModifier(id);
        if (value == 0) return;

        final var playerHealthModifier = new EntityAttributeModifier(
                id, value, operation
        );
        attr.addPersistentModifier(playerHealthModifier);
    }

    public double getValue() {
        return value;
    }
}
