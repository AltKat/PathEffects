package io.github.altkat.pathEffects.gui

import io.github.altkat.pathEffects.PathEffects
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class GUIListener(val plugin: PathEffects) : Listener {
    @EventHandler
    fun onGUIClick(event: InventoryClickEvent){
        val player : Player = event.whoClicked as Player
        val itemStack : ItemStack? = event.currentItem
        if(event.inventory.holder is GUIHolder){
            event.isCancelled = true
            if(itemStack?.itemMeta?.itemName == plugin.config.getString("blockTypes.default.title")!!.replace("&", "§") ){
                if(!player.hasPermission("patheffects.default")){

                    return
                }
                if(plugin.db.getPlayerPathType(player).equals("default")){
                    plugin.sendMessage(player, "path.already-selected")
                    return
                }
                if(plugin.db.getPlayerStatus(player) == 1){
                    plugin.sendMessage(player, "path.cantWhileActive")
                    return
                }
                plugin.db.setPlayerPathType(player, "default")
                plugin.sendMessage(player, "path.selection-message")
                event.whoClicked.closeInventory()
            }else if(itemStack?.itemMeta?.itemName == plugin.config.getString("blockTypes.type1.title")!!.replace("&", "§")) {
                if(!player.hasPermission("patheffects.type1")){

                    return
                }
                if(plugin.db.getPlayerPathType(player).equals("type1")){
                    plugin.sendMessage(player, "path.already-selected")
                    return
                }
                if(plugin.db.getPlayerStatus(player) == 1){
                    plugin.sendMessage(player, "path.cantWhileActive")
                    return
                }
                plugin.db.setPlayerPathType(player, "type1")
                plugin.sendMessage(player, "path.selection-message")
                event.whoClicked.closeInventory()
            }else if(itemStack?.itemMeta?.itemName == plugin.config.getString("blockTypes.type2.title")!!.replace("&", "§")) {
                if(!player.hasPermission("patheffects.type2")){

                    return
                }
                if(plugin.db.getPlayerPathType(player).equals("type2")){
                    plugin.sendMessage(player, "path.already-selected")
                    return
                }
                if(plugin.db.getPlayerStatus(player) == 1){
                    plugin.sendMessage(player, "path.cantWhileActive")
                    return
                }
                plugin.db.setPlayerPathType(player, "type2")
                plugin.sendMessage(player, "path.selection-message")
                event.whoClicked.closeInventory()
            } else if(itemStack?.itemMeta?.itemName == plugin.config.getString("blockTypes.type3.title")!!.replace("&", "§")) {
                if(!player.hasPermission("patheffects.type3")){

                    return
                }
                if(plugin.db.getPlayerPathType(player).equals("type3")){
                    plugin.sendMessage(player, "path.already-selected")
                    return
                }
                if(plugin.db.getPlayerStatus(player) == 1){
                    plugin.sendMessage(player, "path.cantWhileActive")
                    return
                }
                plugin.db.setPlayerPathType(player, "type3")
                plugin.sendMessage(player, "path.selection-message")
                event.whoClicked.closeInventory()
            }else if(itemStack?.itemMeta?.itemName == plugin.config.getString("blockTypes.type4.title")!!.replace("&", "§")) {
                if(!player.hasPermission("patheffects.type4")){

                    return
                }
                if(plugin.db.getPlayerPathType(player).equals("type4")){
                    plugin.sendMessage(player, "path.already-selected")
                    return
                }
                if(plugin.db.getPlayerStatus(player) == 1){
                    plugin.sendMessage(player, "path.cantWhileActive")
                    return
                }
                plugin.db.setPlayerPathType(player, "type4")
                plugin.sendMessage(player, "path.selection-message")
                event.whoClicked.closeInventory()
            } else if(itemStack?.itemMeta?.itemName.toString() == plugin.config.getString("blockTypes.statusBlock.activeBlockTitle")!!.replace("&","§")){
                if(plugin.db.getPlayerStatus(player) == 0){
                    plugin.sendMessage(player, "path.already-ended")
                    player.closeInventory()
                    return
                }
                plugin.db.setPlayerStatus(player)
                player.closeInventory()
                plugin.sendMessage(player, "path.path-ended")

            }else if(itemStack?.itemMeta?.itemName.toString() == plugin.config.getString("blockTypes.statusBlock.deactiveBlockTitle")!!.replace("&", "§")){
                if(!player.hasPermission("patheffects.default") && !player.hasPermission("type1") && !player.hasPermission("type2") && !player.hasPermission("type3") && !player.hasPermission("type4")){
                    plugin.sendMessage(player, "no-permission")
                    player.closeInventory()
                    return
                }
                if(plugin.db.getPlayerStatus(player) == 1){
                    plugin.sendMessage(player, "path.already-started")
                    player.closeInventory()
                    return
                }
                plugin.db.setPlayerStatus(player)
                player.closeInventory()
                plugin.sendMessage(player, "path.path-started")

            }
        }
    }
}