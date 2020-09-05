package Commands;

import AmongBot.Bot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class Start extends Command {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Command.PREFIX + "start")){

            if(args.length != 2){
                event.getChannel().sendMessage("Incorrect commands usage.").queue();
            }

            Mute.cEvent = event;
            Bot.serverID = args[1];

            /*
            *
            * connect with server
            *
            */

        }
    }
}
