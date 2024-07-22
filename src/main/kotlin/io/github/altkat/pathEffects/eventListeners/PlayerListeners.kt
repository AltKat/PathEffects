package io.github.altkat.pathEffects.eventListeners
import io.github.altkat.pathEffects.PathEffects
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent

class PlayerListeners (val plugin : PathEffects) : Listener {


    @EventHandler
    fun onPlayerrFirstJoin(event: PlayerJoinEvent){
        val player = event.player
        if(plugin.db.checkifPlayerExists(player)) return
        plugin.db.insertPlayer(player.uniqueId.toString(),player.name.toString(), 0 ,0, "default")
    }



    @EventHandler
    fun onPlayerMovement(event: PlayerMoveEvent) {
        var player = event.player
        val pathStatus : Boolean = plugin.pathStatus.getOrDefault(event.player.uniqueId, false)
        var PlayerStatus = plugin.db.getPlayerStatus(player)
        var configBlock : Material = Material.DIAMOND_BLOCK

        if(PlayerStatus == 0) return

        configBlock = when(plugin.db.getPlayerPathType(player)){
            "type1" -> plugin.config.getString("blockTypes.type1.block")?.let { Material.valueOf(it) }!!
            "type2" -> plugin.config.getString("blockTypes.type2.block")?.let { Material.valueOf(it) }!!
            "type3" -> plugin.config.getString("blockTypes.type3.block")?.let { Material.valueOf(it) }!!
            else -> plugin.config.getString("blockTypes.default.block")?.let { Material.valueOf(it) }!!
        }

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