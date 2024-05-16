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
public class Axolotl implements Items {
	public static ItemStack createCustomItem() {
		ItemStack axolotl = new ItemStack(Material.AXOLOTL_BUCKET);
		ItemMeta data = axolotl.getItemMeta();
		data.setDisplayName(ChatColor.BLUE + "Axolotl");
		AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "noStacking", -1000, AttributeModifier.Operation.ADD_NUMBER);
		data.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
		data.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GOLD + "When Right-Clicked");
		lore.add(ChatColor.GRAY + "You cannot eat Axolotls.  How cruel.");
		lore.add("");
		lore.add(ChatColor.GOLD + "When Left-Clicked");
		lore.add(ChatColor.GRAY + "Shoot a fish in the direction you are looking.");
		lore.add(ChatColor.GRAY + "When hitting an enemy, deal " + ChatColor.RED + "2-3" + ChatColor.GRAY + " damage.");
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Flavor Text");
		lore.add("");
		lore.add(ChatColor.BLUE + String.valueOf(ChatColor.BOLD) + ChatColor.MAGIC + "a" + ChatColor.RESET + ChatColor.BLUE + ChatColor.BOLD + " RARE FISH " + ChatColor.MAGIC + "a");

		data.setLore(lore);
		axolotl.setItemMeta(data);
		return axolotl;
	}

	@Override
	public void onRightClick(Player p) {
		p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot eat this, evil person.");
	}

	@Override
	public void onLeftClick(Player p) {
		PlayerListener.shootBeam(p, Color.AQUA, 64, 999, 100);
	}
}
