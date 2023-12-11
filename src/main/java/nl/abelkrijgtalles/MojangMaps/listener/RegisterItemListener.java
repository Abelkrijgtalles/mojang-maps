package nl.abelkrijgtalles.MojangMaps.listener;

import nl.abelkrijgtalles.MojangMaps.util.other.HiddenStringUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.Objects;

public class RegisterItemListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (Objects.equals(HiddenStringUtil.extractHiddenString(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(event.getPlayer().getItemInUse()).getItemMeta()).getLore()).get(0)), "RegisterItem")) {

            event.getPlayer().sendMessage("Don't break stuff when using the register item (still need a better name)");
            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player p) {

            if (Objects.equals(HiddenStringUtil.extractHiddenString(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(p.getItemInUse()).getItemMeta()).getLore()).get(0)), "RegisterItem")) {

                p.sendMessage("Don't damage entity when using the register item (still need a better name)");
                event.setCancelled(true);

            }

        }

    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {

        if (Objects.equals(HiddenStringUtil.extractHiddenString(Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(event.getPlayer().getItemInUse()).getItemMeta()).getLore()).get(0)), "RegisterItem")) {

            event.getPlayer().sendMessage("Don't damage this item when using the register item (still need a better name)");
            event.setCancelled(true);

        }

    }

}
