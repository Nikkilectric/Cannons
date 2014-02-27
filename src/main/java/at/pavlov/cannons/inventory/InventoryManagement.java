package at.pavlov.cannons.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import at.pavlov.cannons.projectile.Projectile;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import at.pavlov.cannons.cannon.Cannon;


public class InventoryManagement
{

	/**
	 * removes the given number of items from the players hand
	 * @param player
	 * @param numberOfItems
	 */
	public static void TakeFromPlayerInventory(Player player, int numberOfItems)
	{
		for (int i = 0; i < numberOfItems; i ++)
		{
			int amount = player.getInventory().getItemInHand().getAmount();
			if (amount == 1)
			{
				//last item removed
				player.getInventory().removeItem(player.getInventory().getItemInHand());
				break;
			}
			else
			{
				//remove one item
				player.getInventory().getItemInHand().setAmount(amount - 1);
			}
		}
	}


	// #################################### removeAmmoFromChest ##############
	public static ItemStack removeItemInChests(List<Inventory> invlist, ItemStack item)
	{
		if (item == null) return null;
		
		Iterator<Inventory> iter = invlist.iterator();
		while (iter.hasNext() && item.getAmount() > 0)
		{
			Inventory next = iter.next();
			item = remove(next, item);
		}

        //the amount of remaining items
		return item;
	}


	/**
	 * removes item from in the inventory. If datavalue < 0 it is not compared
	 * 
	 * @param inv
	 * @param item
	 * @return the amount of not removed items
	 */
	private static ItemStack remove(Inventory inv, ItemStack item)
	{
		if (inv == null || item == null)
			return item;

		HashMap<Integer, ItemStack> itemMap = inv.removeItem(item);

		// all items have been removed
		if (itemMap.size() == 0)
		{
			item.setAmount(0);
			return item;
		}

		// not all items have been removed
		for (ItemStack newItem : itemMap.values())
		{
			// set new amount for item
			return newItem;
		}

		// return untouched item - no item removed
		return item;
	}

	/**
	 * puts an itemstack in the first empty space of the given inventories
	 * @param invlist
	 * @param item
	 * @return
	 */
	public static boolean addItemInChests(List<Inventory> invlist, ItemStack item)
	{
		// return if there should be nothing removed
		if (item == null || item.getAmount() == 0)
			return true;

		// return false if something is missing
		Iterator<Inventory> iter = invlist.iterator();
		while (iter.hasNext())
		{
			Inventory next = iter.next();
			// add items and returned hashmap is zero
			int size = next.addItem(item).size();
			if (size == 0)
                return true;
		}
		return false;
	}

    /**
     * returns the inventory of this block if valid, else null
     * @param block
     * @param list
     * @return
     */
	public static List<Inventory> getInventories(Block block, List<Inventory> list)
	{
		if (list == null)
		{
			list = new ArrayList<Inventory>();
		}
        if(block.getState() instanceof InventoryHolder)
        {
            InventoryHolder ih = (InventoryHolder)block.getState();
            list.add(ih.getInventory());
        }
		return list;
	}

}
