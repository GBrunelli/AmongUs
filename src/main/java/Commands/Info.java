package Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Information command
 * */
public class Info extends Command {

    /**
     * Listener to received messages
     * */
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Command.PREFIX + "info")){
            event.getChannel().sendTyping().queue();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            showInfo(event);

        }
    }

    /**
     * Shows a embed in chat
     * */
    private void showInfo(GuildMessageReceivedEvent event){
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Information");
        info.setDescription("Nice little bot for playing Among Us with voice channel :)");
        info.setFooter("created by Gustavo H. Brunelli", "https://cdn.discordapp.com/avatars/317797312938377217/835c32608c3ccf415af0f1d87b5d68a4.png");
        info.setColor(0xff6600);

        event.getChannel().sendMessage(info.build()).queue();

        info.clear();
    }

}

