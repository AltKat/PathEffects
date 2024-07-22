package io.github.altkat.pathEffects.eventListeners

import io.github.altkat.pathEffects.PathEffects
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerMoveEvent

class PlayerListeners (val plugin : PathEffects) : Listener {
    @EventHandler
    fun onPlayerMovement(event: PlayerMoveEvent) {
        var player = event.player
        val pathStatus : Boolean = plugin.pathStatus.getOrDefault(event.player.uniqueId, false)

        if(!pathStatus) return

        var configBlock : Material? = plugin.config.getString("blockType")?.let { Material.valueOf(it) }
        if(configBlock == null) return

        val standingBlocks = listOf(
            player.location.subtract(0.0, 1.0, 0.0).block,
            player.location.subtract(0.0, 1.0, 1.0).block,
            player.location.subtract(0.0, 1.0, -1.0).block,
        )

        val standingBlockTypes = standingBlocks.map {it.type}

        val validBlocks = standingBlocks.all { block -> block.type != Material.AIR && block.type != Material.WATER && block.type != Material.LAVA
        }

        if(validBlocks) {
            standingBlocks.forEach { block ->
                plugin.blockListesi[block] = true
                block.type = configBlock
            }

            Bukkit.getScheduler().runTaskLater(plugin, Runnable{
                standingBlocks.forEachIndexed{ index, block ->
                    if(block.type == configBlock){
                        block.type = standingBlockTypes[index]
                        plugin.blockListesi[block] = false
                    }
                }
            }, plugin.config.getInt("blockRemovalDuration") * 20L)
        }
    }
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        var currentValue : Boolean = plugin.blockListesi.getOrDefault(event.block , false)
        if(currentValue){
            event.setCancelled(true)
        }
    }

}