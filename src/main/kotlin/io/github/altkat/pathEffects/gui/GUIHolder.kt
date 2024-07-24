package io.github.altkat.pathEffects.gui

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class GUIHolder(private val inventory: Inventory) : InventoryHolder {
    override fun getInventory(): Inventory {
        return inventory
    }
}