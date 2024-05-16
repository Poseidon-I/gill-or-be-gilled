package items;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Items {
	static Items customItemFromInventory(ItemStack itemInUse) {
		switch(itemInUse.getType()) {
			case AXOLOTL_BUCKET -> {
				return new Axolotl();
			}
			case COD -> {
				return new Cod();
			}
			case PUFFERFISH -> {
				return new Pufferfish();
			}
			case SALMON -> {
				return new Salmon();
			}
			case INK_SAC -> {
				return new Squid();
			}
			case FROGSPAWN -> {
				return new Tadpole();
			}
			case TROPICAL_FISH -> {
				return new TropicalFish();
			}
			default -> {
				return null;
			}
		}
	}

	static Items customItemFromEntity(EntityType entity) {
		switch(entity) {
			case AXOLOTL -> {
				return new Axolotl();
			}
			case COD -> {
				return new Cod();
			}
			case PUFFERFISH -> {
				return new Pufferfish();
			}
			case SALMON -> {
				return new Salmon();
			}
			case SQUID -> {
				return new Squid();
			}
			case TADPOLE -> {
				return new Tadpole();
			}
			case TROPICAL_FISH -> {
				return new TropicalFish();
			}
			default -> {
				return null;
			}
		}
	}

	void onRightClick(Player p);

	void onLeftClick(Player p);
}