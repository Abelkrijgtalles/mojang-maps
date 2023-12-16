package nl.abelkrijgtalles.MojangMaps.listener;

import nl.abelkrijgtalles.MojangMaps.util.other.BlockSelectUtil;
import nl.abelkrijgtalles.MojangMaps.util.other.HiddenStringUtil;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.List;

public class RegisterItemListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (HiddenStringUtil.extractHiddenString(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0)) != "RegisterItem") {

            event.getPlayer().sendMessage(ChatColor.RED + "Don't break stuff when using the register item (still need a better name)");
            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player p) {

            if (HiddenStringUtil.extractHiddenString(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0)) != "RegisterItem") {

                p.sendMessage(ChatColor.RED + "Don't damage entity when using the register item (still need a better name)");
                event.setCancelled(true);

            }

        }

    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {

        if (HiddenStringUtil.extractHiddenString(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0)) != "RegisterItem") {

            event.getPlayer().sendMessage(ChatColor.RED + "Don't damage this item when using the register item (still need a better name)");
            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getHand() != EquipmentSlot.HAND) return;

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (!p.getInventory().getItemInMainHand().hasItemMeta()) return;

        if (!p.getInventory().getItemInMainHand().getItemMeta().hasLore()) return;

        List<String> lore = p.getInventory().getItemInMainHand().getItemMeta().getLore();

        if (lore != null && lore.size() > 0 && HiddenStringUtil.hasHiddenString(lore.get(0)))
            if (HiddenStringUtil.extractHiddenString(lore.get(0)) != "RegisterItem") return;

        BlockSelectUtil.getSelectedBlock(event.getClickedBlock().getLocation(), event.getPlayer());

        event.setUseItemInHand(Event.Result.DENY);
    }

}
