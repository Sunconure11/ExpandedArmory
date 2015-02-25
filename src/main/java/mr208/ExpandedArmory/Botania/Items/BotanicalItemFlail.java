package mr208.ExpandedArmory.Botania.Items;

import mr208.ExpandedArmory.Botania.ManaHelper;
import mr208.ExpandedArmory.Items.ExArmItemFlail;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

public class BotanicalItemFlail extends ExArmItemFlail implements IManaUsingItem {
    private static final int MANA_PER_DMG = 51;
    public BotanicalItemFlail(String id, ToolMaterial toolmaterial) {
        super(id, toolmaterial);
    }


    @Override
    public boolean hitEntity(ItemStack itemWeapon, EntityLivingBase targetEntity, EntityLivingBase usingEntity)
    {
        if(usesMana(itemWeapon)) ManaHelper.damageItem(itemWeapon, 1, usingEntity, MANA_PER_DMG);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemWeapon, World world, Block block, int x,int y, int z, EntityLivingBase player)
    {
        if(usesMana(itemWeapon) && block.getBlockHardness(world,x,y,z) !=0f) ManaHelper.damageItem(itemWeapon,1,player,MANA_PER_DMG);
        return true;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    public void onUpdate(ItemStack weapon, World world, Entity player, int num, boolean bool)
    {
        if(!world.isRemote && player instanceof EntityPlayer && weapon.getItemDamage() >0 && ManaItemHandler.requestManaExactForTool(weapon, (EntityPlayer) player, MANA_PER_DMG * 2, true))
            weapon.setItemDamage(weapon.getItemDamage()-1);
    }
}
