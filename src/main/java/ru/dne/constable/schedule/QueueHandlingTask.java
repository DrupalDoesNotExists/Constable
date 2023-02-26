package ru.dne.constable.schedule;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import ru.dne.constable.Constable;
import ru.dne.constable.configuration.Configuration;
import ru.dne.constable.queue.Event;

import java.util.List;

/**
 * Queue handling scheduler task
 */
public class QueueHandlingTask extends BukkitRunnable {

    @Override
    public void run() {

        boolean shuffle = Configuration.ENABLE_SHUFFLING.bool();
        List<Event> eventList = Constable.getConstable().getEventQueue().tick(shuffle);

        // Handle all events at the same time
        for (Event event : eventList) {
            LivingEntity damaged = event.getDamaged();
            LivingEntity damager = event.getDamager();

            damaged.damage(event.getDamage(), event.getDamager());

            // Add knockback to damaged entity
            Vector knockback = damaged.getLocation().toVector()
                    .subtract(damager.getLocation().toVector()).multiply(0.05);
            damaged.setVelocity(damaged.getVelocity().add(knockback));
        }

    }

}
