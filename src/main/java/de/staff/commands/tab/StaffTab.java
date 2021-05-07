package de.staff.commands.tab;

import de.staff.StaffService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaffTab implements TabCompleter {
    private StaffService plugin;

    public StaffTab(StaffService plugin) {
        this.plugin = plugin;
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        if(command.getName().equals("staff")){
            if(args.length == 1){
                List<String> subcommands = new ArrayList<>();
                String[] subcmdlist = { "gui", "reload"};

                if(!args[0].isEmpty()){
                    subcommands.removeAll(subcommands);
                    for(String cmd : subcmdlist){
                        if(cmd.startsWith(args[0])){
                            subcommands.add(cmd);
                        }

                    }
                }else {
                    subcommands.removeAll(subcommands);
                    subcommands.addAll(Arrays.asList(subcmdlist));
                }

                return subcommands;

            }
        }

        return null;
    }
}
