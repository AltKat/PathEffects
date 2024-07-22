package io.github.altkat.pathEffects

import io.github.altkat.pathEffects.commands.Commands
import io.github.altkat.pathEffects.database.database
import io.github.altkat.pathEffects.eventListeners.PlayerListeners
import io.github.altkat.pathEffects.gui.GUIListener
import org.bukkit.ChatColor
import org.bukkit.block.Block
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

class PathEffects : JavaPlugin() {
    lateinit var db: database

    val pluginTag: String = "PathEffects"
    val blockListesi: HashMap<Block, Boolean> = HashMap()
    val pathStatus: HashMap<UUID, Boolean> = HashMap()

    override fun onEnable() {
        // PathEffects ve database klasörlerinin var olup olmadığını kontrol et ve gerekirse oluştur
        val pluginFolder = dataFolder
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs()
        }

        val dbFolder = File(pluginFolder, "database")
        if (!dbFolder.exists()) {
            dbFolder.mkdirs()
        }

        val dbFilePath = "${dbFolder.absolutePath}/database.db"
        db = database(dbFilePath)
        db.connect()
        db.createTable()

        // Plugin startup logic
        server.consoleSender.sendMessage("[${ChatColor.GREEN} $pluginTag] is enabled")
        server.pluginManager.registerEvents(PlayerListeners(this), this)
        server.pluginManager.registerEvents(GUIListener(this),this)
        getCommand("patheffects")?.setExecutor(Commands(this))
        reloadPlugin()
    }

    override fun onDisable() {
        // Plugin shutdown logic
        server.consoleSender.sendMessage("[${ChatColor.RED} $pluginTag] is disabled")

        // Veritabanı bağlantısını kapat
        db.close()
    }

    fun reloadPlugin() {
        reloadConfig()
        saveDefaultConfig()
        config.options().copyDefaults(true)
        saveConfig()
    }

    fun sendMessage(receiver: CommandSender, path: String, vararg args: String?) {
        val rawMessage = config.getString("pluginTag") + " " + config.getString("messages.$path")
        val formattedMessage = ChatColor.translateAlternateColorCodes('&', String.format(rawMessage, args as Any))
        receiver.sendMessage(formattedMessage)
    }
}
