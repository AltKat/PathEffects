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
        if(!p0.hasPermission("patheffects.admin")){
            plugin.sendMessage(p0, "no-permission")
            return true
        }
        if (p3.isEmpty()) {
            plugin.sendMessage(p0, "path.usage");
            return true;
        }
        if(!p3.isEmpty()){
            when(p3[0]){
                "reload" -> {
                    plugin.reloadPlugin()
                    plugin.sendMessage(p0, "config-reloaded")
                }
                "start" -> {
                    if(plugin.db.getPlayerStatus(p0 as Player) == 0){
                        plugin.db.setPlayerStatus(p0 as Player)
                        plugin.sendMessage(p0, "path.path-started")

                        Bukkit.getScheduler().runTaskLater(plugin, Runnable {
                            plugin.db.setPlayerStatus(p0 as Player)
                            plugin.sendMessage(p0, "path.path-ended")
                        }, plugin.config.getInt("pathDuration") * 20L)
                    } else {
                        plugin.sendMessage(p0, "path.path-already-started")
                    }
                }
                "gui" -> {
                    val inventory : Inventory = guiCreator(plugin).createGUI()
                    val player: Player = (p0 as Player)
                    player.openInventory(inventory)
                }
            }

        }
        return true
    }
}