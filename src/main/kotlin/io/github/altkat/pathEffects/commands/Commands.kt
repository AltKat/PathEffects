package io.github.altkat.pathEffects.commands

import io.github.altkat.pathEffects.PathEffects
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

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
                    val currentValue : Boolean = plugin.pathStatus.getOrDefault((p0 as Player).uniqueId, false);
                    if(!currentValue){
                        plugin.pathStatus.set((p0 as Player).uniqueId, true);
                        plugin.sendMessage(p0, "path.path-started")

                        Bukkit.getScheduler().runTaskLater(plugin, Runnable {
                            plugin.pathStatus.set((p0 as Player).uniqueId, false)
                            plugin.sendMessage(p0, "path.path-ended")
                        }, plugin.config.getInt("pathDuration") * 20L)
                    } else {
                        plugin.sendMessage(p0, "path.path-already-started")
                    }
                }
            }

        }
        return true
    }
}