package events;

import items.Items;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class AbsorptionShield implements Listener {
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player p) {
			if(p.hasPotionEffect(PotionEffectType.ABSORPTION)) {
				e.setCancelled(true);
				p.removePotionEffect(PotionEffectType.ABSORPTION);
			}
		}
		if(e.getDamager() instanceof Player p) {
			try {
				Items item = Items.customItemFromInventory(Objects.requireNonNull(p.getInventory().getItemInMainHand()));
				assert item != null;
				item.onLeftClick(p);
				p.getInventory().remove(Objects.requireNonNull(p.getInventory().getItemInMainHand()));
				e.setCancelled(true);
			} catch(Exception exception) {
				exception.printStackTrace();
				e.setCancelled(false);
			}
		}
	}
}