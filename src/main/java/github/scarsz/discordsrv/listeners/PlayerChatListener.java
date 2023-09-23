/*
 * DiscordSRV - https://github.com/DiscordSRV/DiscordSRV
 *
 * Copyright (C) 2016 - 2022 Austin "cosdiscord" Shapiro
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package github.fazuedevelopment.cosdiscord.listeners;

import github.fazuedevelopment.cosdiscord.DiscordSRV;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @SuppressWarnings("deprecation") // legacy
    @EventHandler(priority = EventPriority.MONITOR)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (DiscordSRV.config().getBooleanElse("UseModernPaperChatEvent", false)
                && DiscordSRV.getPlugin().isModernChatEventAvailable()) {
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(DiscordSRV.getPlugin(), () ->
                DiscordSRV.getPlugin().processChatMessage(
                        event.getPlayer(),
                        event.getMessage(),
                        DiscordSRV.getPlugin().getOptionalChannel("global"),
                        event.isCancelled(),
                        event
                )
        );
    }

}
