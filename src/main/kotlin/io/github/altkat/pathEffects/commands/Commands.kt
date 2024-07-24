package io.github.altkat.pathEffects.commands

import io.github.altkat.pathEffects.PathEffects
import io.github.altkat.pathEffects.gui.guiCreator
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class Commands (val plugin : PathEffects) : CommandExecutor {
    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if (p3.size == 0) {
            plugin.sendMessage(p0, "path.usage");
            return true;
        }
        if(!p3[0].isEmpty()){
            when(p3[0]){
                "reload" -> {
                    if(p0.hasPermission("patheffects.admin")){
                        plugin.reloadPlugin()
                        if(p0 is Player) {
                            plugin.sendMessage(p0, "config-reloaded")
                        }else{
                            p0.sendMessage("§aPathEffects Reloaded!")
                        }
                    }else{
                        plugin.sendMessage(p0, "no-permission")
                    }
                }
                "start" -> {

                        if (plugin.db.getPlayerStatus(p0 as Player) == 0) {
                            if(p0.hasPermission("patheffects.start")) {
                                plugin.db.setPlayerStatus(p0 as Player)
                                plugin.sendMessage(p0, "path.path-started")
                            }else {
                                plugin.sendMessage(p0, "no-permission")
                            }
                        } else {
                            plugin.sendMessage(p0, "path.already-started")
                        }
                }"stop" ->{

                    if(plugin.db.getPlayerStatus(p0 as Player) == 1){
                        if(p0.hasPermission("patheffects.stop")){
                            plugin.sendMessage(p0, "path.path-ended")
                            plugin.db.setPlayerStatus(p0)
                        }else {
                            plugin.sendMessage(p0, "no-permission")
                        }
                    }else {
                        plugin.sendMessage(p0, "path.already-ended")
                    }

                }"gui" -> {
                    val inventory : Inventory = guiCreator(plugin, (p0 as Player)).createGUI()
                    val player: Player = (p0 as Player)
                    player.openInventory(inventory)
                }
                else ->{
                    plugin.sendMessage(p0, "path.usage");
                }
            }

        }
        return true
    }
}