package nl.abelkrijgtalles.MojangMaps.listener;

import nl.abelkrijgtalles.MojangMaps.util.other.BlockSelectUtil;
import nl.abelkrijgtalles.MojangMaps.util.other.HiddenStringUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class RegisterItemListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (HiddenStringUtil.extractHiddenString(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0)) != "RegisterItem") {

            event.getPlayer().sendMessage("Don't break stuff when using the register item (still need a better name)");
            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player p) {

            if (HiddenStringUtil.extractHiddenString(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)) != "RegisterItem") {

                p.sendMessage("Don't damage entity when using the register item (still need a better name)");
                event.setCancelled(true);

            }

        }

    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {

        if (HiddenStringUtil.extractHiddenString(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0)) != "RegisterItem") {

            event.getPlayer().sendMessage("Don't damage this item when using the register item (still need a better name)");
            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        p.sendMessage("Hopefully not 1 or 2");

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            p.sendMessage("1");
            return;
        }

        if (HiddenStringUtil.extractHiddenString(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0)) != "RegisterItem") {
            p.sendMessage("2");
            return;

        }

        BlockSelectUtil.getSelectedBlock(event.getClickedBlock().getLocation(), event.getPlayer());

        event.setUseItemInHand(Event.Result.DENY);
    }

}
