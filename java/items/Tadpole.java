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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("DataFlowIssue")
public class Tadpole implements Items {
	public static ItemStack createCustomItem() {
		ItemStack tadpole = new ItemStack(Material.FROGSPAWN);
		ItemMeta data = tadpole.getItemMeta();
		data.setDisplayName(ChatColor.GREEN + "Tadpole");
		AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "noStacking", -1000, AttributeModifier.Operation.ADD_NUMBER);
		data.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
		data.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GOLD + "When Right-Clicked");
		lore.add(ChatColor.GRAY + "Gain " + ChatColor.GREEN + "Jump Boost II" + ChatColor.GRAY + " for " + ChatColor.RED + "10" + ChatColor.GRAY + " seconds.");
		lore.add(ChatColor.GRAY + "Heal " + ChatColor.RED + "2" + ChatColor.GRAY + " HP.");
		lore.add("");
		lore.add(ChatColor.GOLD + "When Left-Clicked");
		lore.add(ChatColor.GRAY + "Shoot a fish in the direction you are looking.");
		lore.add(ChatColor.GRAY + "When hitting an enemy, deal " + ChatColor.RED + "2" + ChatColor.GRAY + " damage.");
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Flavor Text");
		lore.add("");
		lore.add(ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + ChatColor.MAGIC + "a" + ChatColor.RESET + ChatColor.GREEN + ChatColor.BOLD + " UNCOMMON FISH " + ChatColor.MAGIC + "a");

		data.setLore(lore);
		tadpole.setItemMeta(data);
		return tadpole;
	}

	@Override
	public void onRightClick(Player p) {
		double health = p.getHealth();
		if(health + 2 > 20) {
			p.setHealth(20.0);
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You ate a Tadpole and are now at full health!");
		} else {
			p.setHealth(health + 2);
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You ate a Tadpole and healed for 2 HP.");
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 200, 1));
		p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You also gained Jump Boost for 10 seconds!");
	}

	@Override
	public void onLeftClick(Player p) {
		PlayerListener.shootBeam(p, Color.GREEN, 64, 1, 2);
	}
}
