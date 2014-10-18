package mr208.WeaponVault.Items;

import appeng.api.AEApi;
import ckathode.weaponmod.item.ItemMusket;
import ckathode.weaponmod.item.MeleeComponent;
import mr208.WeaponVault.IWeaponMaterialCheck;
import mr208.WeaponVault.Materials;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import vazkii.botania.api.BotaniaAPI;

/**
 * Created by Andrew on 10/16/2014.
 */
public class WVItemMusket extends ItemMusket implements IWeaponMaterialCheck {
    private final int materialID;
    public WVItemMusket(String id, MeleeComponent meleecomponent, Item bayonetitem) {
        super(id, meleecomponent, bayonetitem);
        materialID = meleecomponent.weaponMaterial.ordinal();
    }

    @Override
    public int getMaterialID() {
        return materialID;
    }

    @Override
    public boolean getIsRepairable(ItemStack weaponStack, ItemStack itemStack)
    {
        return itemStack.isItemEqual(getRepairItem(this.meleeComponent.weaponMaterial)) ? true: super.getIsRepairable(weaponStack, itemStack);
    }
    private ItemStack getRepairItem(ToolMaterial material)
    {
        if(material == ThaumcraftApi.toolMatThaumium) return ItemApi.getItem("itemResource",2);
        if(material == ThaumcraftApi.toolMatVoid) return ItemApi.getItem("itemResource",16);
        if(material == Materials.netherQuartz) return new ItemStack(Items.quartz);
        if(material == Materials.certusQuartz) return AEApi.instance().materials().materialCertusQuartzCrystal.stack(1);
        if(material == BotaniaAPI.manasteelToolMaterial) return OreDictionary.getOres("ingotManasteel").get(0);
        if(material == BotaniaAPI.terrasteelToolMaterial) return OreDictionary.getOres("ingotTerrasteel").get(0);
        if(material == BotaniaAPI.elementiumToolMaterial) return OreDictionary.getOres("ingotElementium").get(0);
        return new ItemStack(material.func_150995_f());
    }

}
