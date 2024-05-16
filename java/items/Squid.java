package items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("DataFlowIssue")
public class Squid implements Items {
	public static ItemStack createCustomItem() {
		ItemStack squid = new ItemStack(Material.INK_SAC);
		ItemMeta data = squid.getItemMeta();
		data.setDisplayName(ChatColor.GREEN + "Squid");
		AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "noStacking", -1000, AttributeModifier.Operation.ADD_NUMBER);
		data.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
		data.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GOLD + "When Right-Clicked");
		lore.add(ChatColor.GRAY + "Heal " + ChatColor.RED + "4" + ChatColor.GRAY + " HP.");
		lore.add("");
		lore.add(ChatColor.GOLD + "When Left-Clicked");
		lore.add(ChatColor.GRAY + "Spawns a large ink cloud.");
		lore.add(ChatColor.GRAY + "Enemies in this cloud take " + ChatColor.RED + "6" + ChatColor.GRAY + " damage.");
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Flavor Text");
		lore.add("");
		lore.add(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + ChatColor.MAGIC + "a" + ChatColor.RESET + ChatColor.GREEN + ChatColor.BOLD + " UNCOMMON FISH " + ChatColor.MAGIC + "a");

		data.setLore(lore);
		squid.setItemMeta(data);
		return squid;
	}

	@Override
	public void onRightClick(Player p) {
		double health = p.getHealth();
		if(health + 4 > 20) {
			p.setHealth(20.0);
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You ate a Squid and are now at full health!");
		} else {
			p.setHealth(health + 4);
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You ate a Squid and healed for 4 HP.");
		}
	}

	@Override
	public void onLeftClick(Player p) {
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation(), 10000);
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone().subtract(3, 0, 0), 10000);
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone().subtract(3, 0, 3), 10000);
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone().subtract(0, 0, 3), 10000);
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone().subtract(-3, 0, 3), 10000);
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone().subtract(-3, 0, 0), 10000);
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone().subtract(-3, 0, -3), 10000);
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone().subtract(0, 0, -3), 10000);
		p.getWorld().spawnParticle(Particle.SQUID_INK, p.getLocation().clone().subtract(3, 0, -3), 10000);
		List<Entity> entities = p.getNearbyEntities(5, 5, 5);
		for(Entity entity : entities) {
			if(!entity.equals(p) && entity instanceof LivingEntity entity1 && entity1.getHealth() > 0) {
				entity1.damage(6);
			}
		}
	}
}
