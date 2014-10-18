package mr208.WeaponVault.Botania;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.ManaItemHandler;

/**
 * Created by Andrew on 10/17/2014.
 */
public class ManaHelper {

    public static void  damageItem(ItemStack item, int damage, EntityLivingBase player, int cost)
    {
        int mananeeded = damage * cost;
        boolean manacostmet = player instanceof EntityPlayer ? ManaItemHandler.requestManaExact(item,(EntityPlayer) player,mananeeded,true) : false;
        if(!manacostmet) item.damageItem(damage, player);
    }
}
