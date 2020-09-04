package Commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

abstract public class Command extends ListenerAdapter{
    public static final String PREFIX = "/";

    @Override
    abstract public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event);
}
