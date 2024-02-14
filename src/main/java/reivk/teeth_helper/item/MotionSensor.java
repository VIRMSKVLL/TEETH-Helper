package reivk.teeth_helper.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import reivk.teeth_helper.Teeth;

import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.function.Predicate;

public class MotionSensor extends Item {
    private static final Settings settings = new Item.Settings();
    static int radius = 16;
    int tick_timer = 0;
    float closest = 0;
    float closest_percent = 0;
    int closeness_modifier = 0;


    public MotionSensor() {
        super(settings);
    }

    public static final Predicate<Entity> ENTITY_IS_MOVING = (entity) -> {

        return entity.isAlive() && (entity.horizontalSpeed > entity.prevHorizontalSpeed || entity.getY() != entity.prevY) && !entity.isSpectator() && !(entity instanceof PlayerEntity);
    };

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity player, int slot, boolean selected) {
        super.inventoryTick(stack, world, player, slot, selected);

        if (selected && !world.isClient) { // TODO: Make work with Offhand


            //  Timing
            closest_percent = closest / 100;
            if (closest_percent < 0.1 ) { closeness_modifier = 2; }
            else if (closest_percent < 0.08 ) { closeness_modifier = 3; }
            else if (closest_percent < 0.05 ) { closeness_modifier = 5; }
            else if (closest_percent < 0.03 ) { closeness_modifier = 8; }
            else { closeness_modifier = 0; }

            tick_timer++;
            tick_timer %= (15 - closeness_modifier);

            if (tick_timer == 0) {
                closest = 100; // reset the closest entity so the position doesn't stick

                // Make a list of Entities within a 32 block radius of the holding entity
                var box = new Box(player.getBlockX() - radius, player.getBlockY() - radius, player.getBlockZ() - radius, player.getBlockX() + radius, player.getBlockY() + radius, player.getBlockZ() + radius);
                List<Entity> entities = world.getOtherEntities(player, box, ENTITY_IS_MOVING);


                if (!entities.isEmpty()) {

                    // For each entity in the list get its distance from the holding entity and store it if it's lower than the previous stored value
                    for (Entity ent : entities) {
                        if (ent.distanceTo(player) < closest) {
                            closest = ent.distanceTo(player);
                            // change closest var to be an array of all the distances and directions of the valid entities; pushing the closest one to be always the first index :)
                        }
                    }

                    //  Play Sound with Pitch based on Range of closest entity
                    world.playSound(null, player.getBlockPos(), Teeth.RADAR_WHIR, SoundCategory.PLAYERS, 0.4f, (1.5f - (closest / 100)));

                }
                else {
                    world.playSound(null, player.getBlockPos(), Teeth.RADAR_BASS, SoundCategory.PLAYERS, 0.4f, 1f);
                }
            }

        }
    }
}
