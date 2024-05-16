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
public class TropicalFish implements Items {
	public static ItemStack createCustomItem() {
		ItemStack tropicalFish = new ItemStack(Material.TROPICAL_FISH);
		ItemMeta data = tropicalFish.getItemMeta();
		data.setDisplayName(ChatColor.BLUE + "Tropical Fish");
		AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "noStacking", -1000, AttributeModifier.Operation.ADD_NUMBER);
		data.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
		data.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GOLD + "When Right-Clicked");
		lore.add(ChatColor.GRAY + "Spawn a shield that will fully absorb an entire hit.");
		lore.add("");
		lore.add(ChatColor.GOLD + "When Left-Clicked");
		lore.add(ChatColor.GRAY + "Shoot a fish in the direction you are looking.");
		lore.add(ChatColor.GRAY + "When hitting an enemy, deal " + ChatColor.RED + "1" + ChatColor.GRAY + " damage.");
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Flavor Text");
		lore.add("");
		lore.add(ChatColor.BLUE + String.valueOf(ChatColor.BOLD) + ChatColor.MAGIC + "a" + ChatColor.RESET + ChatColor.BLUE + ChatColor.BOLD + " RARE FISH " + ChatColor.MAGIC + "a");

		data.setLore(lore);
		tropicalFish.setItemMeta(data);
		return tropicalFish;
	}

	@Override
	public void onRightClick(Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, -1, 0));
		p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You ate a Tropical Fish and now have a shield protecting you!");
	}

	@Override
	public void onLeftClick(Player p) {
		PlayerListener.shootBeam(p, Color.ORANGE, 64, 1, 1);
	}
}
