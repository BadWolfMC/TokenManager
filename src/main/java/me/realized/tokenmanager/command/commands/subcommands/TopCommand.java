/*
 *
 *   This file is part of TokenManager, licensed under the MIT License.
 *
 *   Copyright (c) Realized
 *   Copyright (c) contributors
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 *
 */

package me.realized.tokenmanager.command.commands.subcommands;

import me.realized.tokenmanager.TokenManager;
import me.realized.tokenmanager.command.BaseCommand;
import me.realized.tokenmanager.data.database.Database;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TopCommand extends BaseCommand {

    public TopCommand(final TokenManager plugin) {
        super(plugin, "top", "top", "tokenmanager.use.top", 1, false, "balancetop");
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        sendMessage(sender, true, "top-next-update", "remaining", getDataManager().getNextUpdate());

        final List<Database.RankedData> top = getDataManager().getTopCache();

        sendMessage(sender, true, "top-header", "total", (top != null ? top.size() : 0));

        if (top == null || top.isEmpty()) {
            sendMessage(sender, true, "no-data");
        } else {
            for (final Database.RankedData data : top) {
                sendMessage(sender, true, "top-format", "rank", data.getRank(), "name", data.getKey(), "tokens", data.getValue());
            }
        }

        sendMessage(sender, true, "top-footer");
    }
}
