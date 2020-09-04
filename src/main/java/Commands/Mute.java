package Commands;

import AmongBot.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Mute extends Command {

    protected static GuildMessageReceivedEvent cEvent;
    private Member cMember;
    private static boolean state;

    public static boolean getState(){
        return state;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        cEvent = event;

        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Command.PREFIX + "mute")){

            muteMentioned(event);

        }
        else if(args[0].equalsIgnoreCase(Command.PREFIX + "unmute")) {
            //TODO implement unmute command
        }
    }

    public void onGuildVoiceGuildMute(@Nonnull GuildVoiceGuildMuteEvent event) {
        super.onGuildVoiceGuildMute(event);
        cEvent.getChannel().sendMessage("Status: " + Objects.requireNonNull(cMember.getVoiceState()).isGuildMuted()).queue();
    }

    public void muteMentioned(@NotNull GuildMessageReceivedEvent event){
        cMember = event.getMessage().getMentionedMembers().get(0);
        try {
            AuditableRestAction<Void> auditableRestAction = cMember.mute(true);
            auditableRestAction.submit();
        } catch (Exception e){
            event.getChannel().sendMessage(e.getMessage()).queue();
        }
    }

    public static void muteEveryone(){
        Mute.mute(true);
    }

    public static void unmuteEveryone(){
        Mute.mute(false);
    }

    private static void mute(boolean mute){
        Guild guild = cEvent.getGuild();

        for (User user: Bot.jda.getUsers()) {
            try {
                AuditableRestAction<Void> auditableRestAction = Objects.requireNonNull(guild.getMember(user)).mute(mute);
                auditableRestAction.submit();
            } catch (Exception e){
                System.out.println(e.toString());
            }
        }
        Mute.state = mute;
    }
}
