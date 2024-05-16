package items;

import events.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("DataFlowIssue")
public class Salmon implements Items {
	public static ItemStack createCustomItem() {
		ItemStack salmon = new ItemStack(Material.SALMON);
		ItemMeta data = salmon.getItemMeta();
		data.setDisplayName(ChatColor.WHITE + "Salmon");
		AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "noStacking", -1000, AttributeModifier.Operation.ADD_NUMBER);
		data.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
		data.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GOLD + "When Right-Clicked");
		lore.add(ChatColor.GRAY + "Heal " + ChatColor.RED + "6" + ChatColor.GRAY + " HP.");
		lore.add("");
		lore.add(ChatColor.GOLD + "When Left-Clicked");
		lore.add(ChatColor.GRAY + "Shoot a fish in the direction you are looking.");
		lore.add(ChatColor.GRAY + "When hitting an enemy, deal " + ChatColor.RED + "4" + ChatColor.GRAY + " damage.");
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Flavor Text");
		lore.add("");
		lore.add(ChatColor.WHITE + String.valueOf(ChatColor.BOLD) + ChatColor.MAGIC + "a" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + " COMMON FISH " + ChatColor.MAGIC + "a");

		data.setLore(lore);
		salmon.setItemMeta(data);
		return salmon;
	}

	@Override
	public void onRightClick(Player p) {
		double health = p.getHealth();
		if(health + 6 > 20) {
			p.setHealth(20.0);
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You ate a Salmon and are now at full health!");
		} else {
			p.setHealth(health + 6);
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You ate a Salmon and healed for 6 HP.");
		}
	}

	@Override
	public void onLeftClick(Player p) {
		PlayerListener.shootBeam(p, Color.RED, 64, 1, 4);
	}
}
