package io.github.altkat.pathEffects.gui

import io.github.altkat.pathEffects.PathEffects
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class guiCreator(val plugin: PathEffects, val player: Player) {
    fun createGUI(): Inventory {
        val inventory : Inventory = Bukkit.createInventory(null, 9 , "")
        val inventoryReal : Inventory = Bukkit.createInventory(GUIHolder(inventory), (plugin.config.getInt("blockTypes.menu-size")), "Path Effects")

        val defaultItemStack : ItemStack = ItemStack((plugin.config.getString("blockTypes.default.guiBlock")?.let { Material.valueOf(it) } !! ))
        val defaultItemMeta : ItemMeta? = defaultItemStack.itemMeta
        defaultItemMeta?.setItemName(plugin.config.getString("blockTypes.default.title")!!.replace('&','§'))

        var list = mutableListOf<String>()
        list.addAll(plugin.config.getStringList("blockTypes.default.lore"))
        for (i in 0 until list.size) {
            list.set(i, (list.get(i).replace("&", "§")))
        }
        defaultItemMeta?.lore = list
        defaultItemMeta?.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ARMOR_TRIM)
        defaultItemStack.setItemMeta(defaultItemMeta)

        if(plugin.db.getPlayerPathType(player) == "default"){
            var list = mutableListOf<String>()
            list.addAll(plugin.config.getStringList("blockTypes.default.selectedlore"))
            for (i in 0 until list.size) {
                list.set(i, (list.get(i).replace("&", "§")))
            }
            defaultItemMeta?.lore = list
            if(plugin.db.getPlayerPathType(player) == "default" ){
                defaultItemMeta?.addEnchant(Enchantment.UNBREAKING, 1 ,true)
            }
            defaultItemStack.setItemMeta(defaultItemMeta)
        }

        if(!player.hasPermission("patheffects.default")) {
            var List = mutableListOf<String>()
            List.addAll(plugin.config.getStringList("blockTypes.default.lockedlore"))
            for (i in 0 until List.size) {
                List.set(i, (List.get(i).replace("&", "§")))
            }
            defaultItemMeta?.lore = List
            defaultItemMeta?.removeEnchantments()
            defaultItemStack.setItemMeta(defaultItemMeta)

        }
        inventoryReal.setItem(plugin.config.getInt("blockTypes.default.slot"), defaultItemStack)






        val type1ItemStack: ItemStack = ItemStack((plugin.config.getString("blockTypes.type1.guiBlock")?.let {Material.valueOf(it)}!!))
        val type1ItemMeta: ItemMeta? = type1ItemStack.itemMeta
        type1ItemMeta?.setItemName(plugin.config.getString("blockTypes.type1.title")!!.replace('&', '§' ))
        var listtype1 = mutableListOf<String>()
        listtype1.addAll(plugin.config.getStringList("blockTypes.type1.lore"))
        for(i in 0 until listtype1.size){
            listtype1.set(i, (listtype1.get(i).replace("&", "§")))
        }
        type1ItemMeta?.lore = listtype1
        type1ItemMeta?.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ARMOR_TRIM)
        type1ItemStack.setItemMeta(type1ItemMeta)

        if(plugin.db.getPlayerPathType(player) == "type1"){
            var List = mutableListOf<String>()
            List.addAll(plugin.config.getStringList("blockTypes.type1.selectedlore"))
            for (i in 0 until List.size) {
                List.set(i, (List.get(i).replace("&", "§")))
            }
            type1ItemMeta?.lore = List
            if(plugin.db.getPlayerPathType(player) == "type1" ){
                type1ItemMeta?.addEnchant(Enchantment.UNBREAKING, 1 ,true)
            }
            type1ItemStack.setItemMeta(type1ItemMeta)
        }

        if(!player.hasPermission("patheffects.type1")) {
            var List = mutableListOf<String>()
            List.addAll(plugin.config.getStringList("blockTypes.type1.lockedlore"))
            for (i in 0 until List.size) {
                List.set(i, (List.get(i).replace("&", "§")))
            }
            type1ItemMeta?.lore = List
            type1ItemMeta?.removeEnchantments()
            type1ItemStack.setItemMeta(type1ItemMeta)

        }
        inventoryReal.setItem(plugin.config.getInt("blockTypes.type1.slot"), type1ItemStack)


        val type2ItemStack: ItemStack = ItemStack((plugin.config.getString("blockTypes.type2.guiBlock")?.let { Material.valueOf(it) }!!))
        val type2ItemMeta: ItemMeta? = type2ItemStack.itemMeta
        type2ItemMeta?.setItemName(plugin.config.getString("blockTypes.type2.title")!!.replace("&", "§"))
        var listtype2 = mutableListOf<String>()
        listtype2.addAll(plugin.config.getStringList("blockTypes.type2.lore"))
        for(i in 0 until listtype2.size){
            listtype2.set(i, (listtype2.get(i).replace("&", "§")))
        }
        type2ItemMeta?.lore = listtype2
        type2ItemMeta?.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ARMOR_TRIM)
        type2ItemStack.setItemMeta(type2ItemMeta)

        if(plugin.db.getPlayerPathType(player) == "type2"){
            var List = mutableListOf<String>()
            List.addAll(plugin.config.getStringList("blockTypes.type2.selectedlore"))
            for (i in 0 until List.size) {
                List.set(i, (List.get(i).replace("&", "§")))
            }
            type2ItemMeta?.lore = List
            if(plugin.db.getPlayerPathType(player) == "type2" ){
                type2ItemMeta?.addEnchant(Enchantment.UNBREAKING, 1 ,true)
            }
            type2ItemStack.setItemMeta(type2ItemMeta)
        }

        if(!player.hasPermission("patheffects.type2")) {
            var List = mutableListOf<String>()
            List.addAll(plugin.config.getStringList("blockTypes.type2.lockedlore"))
            for (i in 0 until List.size) {
                List.set(i, (List.get(i).replace("&", "§")))
            }
            type2ItemMeta?.lore = List
            type2ItemMeta?.removeEnchantments()
            type2ItemStack.setItemMeta(type2ItemMeta)

        }
        inventoryReal.setItem(plugin.config.getInt("blockTypes.type2.slot"), type2ItemStack)

        val type3ItemStack: ItemStack = ItemStack((plugin.config.getString("blockTypes.type3.guiBlock")?.let { Material.valueOf(it) }!!))
        val type3ItemMeta: ItemMeta? = type3ItemStack.itemMeta
        type3ItemMeta?.setItemName(plugin.config.getString("blockTypes.type3.title")!!.replace("&", "§"))
        var listtype3 = mutableListOf<String>()
        listtype3.addAll(plugin.config.getStringList("blockTypes.type3.lore"))
        for(i in 0 until listtype3.size){
            listtype3.set(i, (listtype3.get(i).replace("&","§")))
        }
        type3ItemMeta?.lore = listtype3
        type3ItemMeta?.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ARMOR_TRIM)
        type3ItemStack.setItemMeta(type3ItemMeta)

        if(plugin.db.getPlayerPathType(player) == "type3"){
            var List = mutableListOf<String>()
            List.addAll(plugin.config.getStringList("blockTypes.type3.selectedlore"))
            for (i in 0 until List.size) {
                List.set(i, (List.get(i).replace("&", "§")))
            }
            type3ItemMeta?.lore = List
            if(plugin.db.getPlayerPathType(player) == "type3" ){
                type3ItemMeta?.addEnchant(Enchantment.UNBREAKING, 1 ,true)
            }
            type3ItemStack.setItemMeta(type3ItemMeta)
        }

        if(!player.hasPermission("patheffects.type3")) {
            var List = mutableListOf<String>()
            List.addAll(plugin.config.getStringList("blockTypes.type3.lockedlore"))
            for (i in 0 until List.size) {
                List.set(i, (List.get(i).replace("&", "§")))
            }
            type3ItemMeta?.lore = List
            type3ItemMeta?.removeEnchantments()
            type3ItemStack.setItemMeta(type3ItemMeta)

        }
        inventoryReal.setItem(plugin.config.getInt("blockTypes.type3.slot"), type3ItemStack)



        val type4ItemStack: ItemStack = ItemStack((plugin.config.getString("blockTypes.type4.guiBlock")?.let { Material.valueOf(it) }!!))
        val type4ItemMeta: ItemMeta? = type4ItemStack.itemMeta
        type4ItemMeta?.setItemName(plugin.config.getString("blockTypes.type4.title")!!.replace("&", "§"))
        var listtype4 = mutableListOf<String>()
        listtype4.addAll(plugin.config.getStringList("blockTypes.type4.lore"))
        for(i in 0 until listtype4.size){
            listtype4.set(i, (listtype4.get(i).replace("&","§")))
        }
        type4ItemMeta?.lore = listtype4
        type4ItemMeta?.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ARMOR_TRIM)
        type4ItemStack.setItemMeta(type4ItemMeta)

        if(plugin.db.getPlayerPathType(player) == "type4"){
            var List = mutableListOf<String>()
            List.addAll(plugin.config.getStringList("blockTypes.type4.selectedlore"))
            for (i in 0 until List.size) {
                List.set(i, (List.get(i).replace("&", "§")))
            }
            type4ItemMeta?.lore = List

            if(plugin.db.getPlayerPathType(player) == "type4" ){
                type4ItemMeta?.addEnchant(Enchantment.UNBREAKING, 1 ,true)
            }
            type4ItemStack.setItemMeta(type4ItemMeta)
        }

        if(!player.hasPermission("patheffects.type4")) {
            var List = mutableListOf<String>()
            List.addAll(plugin.config.getStringList("blockTypes.type4.lockedlore"))
            for (i in 0 until List.size) {
                List.set(i, (List.get(i).replace("&", "§")))
            }
            type4ItemMeta?.removeEnchantments()
            type4ItemMeta?.lore = List
            type4ItemStack.setItemMeta(type4ItemMeta)

        }

        inventoryReal.setItem(plugin.config.getInt("blockTypes.type4.slot"), type4ItemStack)






        if(plugin.db.getPlayerStatus(player) == 1) {
            val activeItemStack: ItemStack = ItemStack((plugin.config.getString("blockTypes.statusBlock.activeBlock")?.let { Material.valueOf(it) }!!))
            val activeItemMeta: ItemMeta? = activeItemStack.itemMeta
            activeItemMeta?.setItemName(plugin.config.getString("blockTypes.statusBlock.activeBlockTitle")!!.replace("&", "§"))
            var activeList = mutableListOf<String>()
            activeList.addAll(plugin.config.getStringList("blockTypes.statusBlock.activeBlockLore"))
            for(i in 0 until activeList.size){
                activeList.set(i, activeList.get(i).replace("&", "§" ))
            }
            activeItemMeta?.lore = activeList
            activeItemMeta?.addEnchant(Enchantment.EFFICIENCY, 1, true)
            activeItemMeta?.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ARMOR_TRIM)
            activeItemStack.setItemMeta(activeItemMeta)
            inventoryReal.setItem(49, activeItemStack)
        }else if(plugin.db.getPlayerStatus(player) == 0){
            val deactiveItemStack: ItemStack = ItemStack((plugin.config.getString("blockTypes.statusBlock.deactiveBlock")?.let { Material.valueOf(it) }!!))
            val deactiveItemMeta: ItemMeta? = deactiveItemStack.itemMeta
            deactiveItemMeta?.setItemName(plugin.config.getString("blockTypes.statusBlock.deactiveBlockTitle")!!.replace("&", "§"))
            var deactiveList = mutableListOf<String>()
            deactiveList.addAll(plugin.config.getStringList("blockTypes.statusBlock.deactiveBlockLore"))
            for(i in 0 until deactiveList.size){
                deactiveList.set(i, deactiveList.get(i).replace("&", "§" ))
            }
            deactiveItemMeta?.lore = deactiveList
            deactiveItemStack.setItemMeta(deactiveItemMeta)
            inventoryReal.setItem(49, deactiveItemStack)
        }
        return inventoryReal
    }
}