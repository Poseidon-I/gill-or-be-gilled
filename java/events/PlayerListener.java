package events;

import items.Items;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(Objects.equals(e.getHand(), EquipmentSlot.HAND) && e.getPlayer().isInWater()) {
			e.setCancelled(true);
			try {
				Items item = Items.customItemFromInventory(Objects.requireNonNull(e.getItem()));
				if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
					assert item != null;
					item.onLeftClick(e.getPlayer());
					e.getPlayer().getInventory().remove(Objects.requireNonNull(e.getPlayer().getInventory().getItemInMainHand()));
				} else if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					assert item != null;
					item.onRightClick(e.getPlayer());
					e.getPlayer().getInventory().remove(Objects.requireNonNull(e.getPlayer().getInventory().getItemInMainHand()));
				}
			} catch(Exception exception) {
				e.setCancelled(false);
			}
		}
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {

	}

	public static void shootBeam(Entity origin, Color color, long distance, long pierce, double damage) {
		Location l = origin.getLocation();
		if(origin instanceof LivingEntity entity) {
			try {
				l.add(0, Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_SCALE)).getValue() * 1.62, 0);
			} catch(Exception exception) {
				l.add(0, 1.62, 0);
			}
		}
		Vector v = l.getDirection();
		v.setX(v.getX() / 5);
		v.setY(v.getY() / 5);
		v.setZ(v.getZ() / 5);
		World world = origin.getWorld();
		Set<Entity> damagedEntities = new HashSet<>();
		damagedEntities.add(origin);
		for(int i = 0; i < distance * 5 && pierce > 0; i++) {
			if(l.getBlock().getType().isSolid()) {
				break;
			}
			ArrayList<Entity> entities = (ArrayList<Entity>) world.getNearbyEntities(l, 1, 1, 1);
			for(Entity entity : entities) {
				if(entity instanceof LivingEntity temp && !damagedEntities.contains(entity)) {
					damagedEntities.add(entity);
					temp.damage(damage);
					pierce--;
					if(color.equals(Color.YELLOW) && !temp.hasPotionEffect(PotionEffectType.ABSORPTION)) {
						temp.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0));
					}
				}
			}
			Particle.DustOptions particle = new Particle.DustOptions(color, 1.0F);
			world.spawnParticle(Particle.DUST, l, 1, particle);
			l.add(v);
		}
	}
}
