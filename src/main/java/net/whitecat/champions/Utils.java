package net.whitecat.champions;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Utils {
    public static Object[] createBlock(String blockName, Block blockInstance, ItemGroup itemGroup){
        Block BLOCK_OBJECT = Registry.register(Registry.BLOCK,new Identifier("whitecat", blockName),blockInstance);
        Item BLOCK_ITEM = Registry.register(Registry.ITEM,new Identifier("whitecat",blockName), new BlockItem(blockInstance, new Item.Settings().group(itemGroup)));
        return new Object[]{BLOCK_OBJECT, BLOCK_ITEM};
    }

    public static Item createItem(String itemName, Item itemInstance){
        return Registry.register(Registry.ITEM,new Identifier("whitecat",itemName), itemInstance);
    }


}
