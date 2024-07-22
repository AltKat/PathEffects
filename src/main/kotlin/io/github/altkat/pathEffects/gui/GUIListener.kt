package io.github.altkat.pathEffects.gui

import io.github.altkat.pathEffects.PathEffects
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class GUIListener(val plugin: PathEffects) : Listener {
    @EventHandler
    fun onGUIClick(event: InventoryClickEvent){
        val player : Player = event.whoClicked as Player
        val itemStack : ItemStack? = event.currentItem
        if(event.inventory.holder is GUIHolder){
            event.isCancelled = true

            if(itemStack?.type.toString() == plugin.config.getString("blockTypes.default.block") ){
                plugin.db.setPlayerPathType(player, "default")
                plugin.sendMessage(player, "path.selection-message")
                event.whoClicked.closeInventory()
            }else if(itemStack?.type.toString() == plugin.config.getString("blockTypes.type1.block")) {
                plugin.db.setPlayerPathType(player, "type1")
                plugin.sendMessage(player, "path.selection-message")
                event.whoClicked.closeInventory()
            }
        }
    }
}