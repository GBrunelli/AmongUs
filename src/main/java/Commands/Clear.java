package Commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/** *
 * Utility command for cleaning chat
 */
public class Clear extends Command {

    /**
     * Listener to received messages
     * */
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Command.PREFIX + "clear")){

            if (args.length != 2 || Integer.parseInt(args[1]) == 1){
                    event.getChannel().sendMessage("Invalid arguments").queue();
            }

            else{
                try {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();
                } catch (Exception e){
                    // TODO: treat the errors better!
                    event.getChannel().sendMessage("Error: " + e.getMessage()).queue();
                }
            }
        }
    }
}
