package io.github.altkat.pathEffects.gui

import io.github.altkat.pathEffects.PathEffects
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class guiCreator(val plugin: PathEffects) {
    fun createGUI(): Inventory {
        val inventory : Inventory = Bukkit.createInventory(null, 9 , "")
        val inventoryReal : Inventory = Bukkit.createInventory(GUIHolder(inventory), (plugin.config.getInt("blockTypes.menu-size")), "Path Effects")

        val defaultItemStack : ItemStack = ItemStack((plugin.config.getString("blockTypes.default.block")?.let { Material.valueOf(it) } !! ))
        val defaultItemMeta : ItemMeta? = defaultItemStack.itemMeta
        defaultItemMeta?.setItemName(plugin.config.getString("blockTypes.default.title")!!.replace('&','§'))
        var list = mutableListOf<String>()
        list.addAll(plugin.config.getStringList("blockTypes.default.lore"))
        for (i in 0 until list.size){
            list.set(i, (list.get(i).replace("&","§")))
        }
        defaultItemMeta?.lore = list
        defaultItemStack.setItemMeta(defaultItemMeta)
        inventoryReal.setItem(plugin.config.getInt("blockTypes.default.slot"), defaultItemStack)

        val type1ItemStack: ItemStack = ItemStack((plugin.config.getString("blockTypes.type1.block")?.let {Material.valueOf(it)}!!))
        val type1ItemMeta: ItemMeta? = type1ItemStack.itemMeta
        type1ItemMeta?.setItemName(plugin.config.getString("blockTypes.type1.title")!!.replace('&', '§' ))
        var listtype1 = mutableListOf<String>()
        listtype1.addAll(plugin.config.getStringList("blockTypes.type1.lore"))
        for(i in 0 until listtype1.size){
            listtype1.set(i, (listtype1.get(i).replace("&", "§")))
        }
        type1ItemMeta?.lore = listtype1
        type1ItemStack.setItemMeta(type1ItemMeta)
        inventoryReal.setItem(plugin.config.getInt("blockTypes.type1.slot"), type1ItemStack)


        val type2ItemStack: ItemStack = ItemStack((plugin.config.getString("blockTypes.type2.block")?.let { Material.valueOf(it) }!!))
        val type2ItemMeta: ItemMeta? = type2ItemStack.itemMeta
        type2ItemMeta?.setItemName(plugin.config.getString("blockTypes.type2.title")!!.replace("&", "§"))
        var listtype2 = mutableListOf<String>()
        listtype2.addAll(plugin.config.getStringList("blockTypes.type2.lore"))
        for(i in 0 until listtype2.size){
            listtype2.set(i, (listtype2.get(i).replace("&", "§")))
        }
        type2ItemMeta?.lore = listtype2
        type2ItemStack.setItemMeta(type2ItemMeta)
        inventoryReal.setItem(plugin.config.getInt("blockTypes.type2.slot"), type2ItemStack)

        val type3ItemStack: ItemStack = ItemStack((plugin.config.getString("blockTypes.type3.block")?.let { Material.valueOf(it) }!!))
        val type3ItemMeta: ItemMeta? = type3ItemStack.itemMeta
        type3ItemMeta?.setItemName(plugin.config.getString("blockTypes.type3.title")!!.replace("&", "§"))
        var listtype3 = mutableListOf<String>()
        listtype3.addAll(plugin.config.getStringList("blockTypes.type3.lore"))
        for(i in 0 until listtype3.size){
            listtype3.set(i, (listtype3.get(i).replace("&","§")))
        }
        type3ItemMeta?.lore = listtype3
        type3ItemStack.setItemMeta(type3ItemMeta)
        inventoryReal.setItem(plugin.config.getInt("blockTypes.type3.slot"), type3ItemStack)
        return inventoryReal
    }
}