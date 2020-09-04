package AmongBot;

import Commands.Clear;
import Commands.Info;
import Commands.Mute;
import Commands.Start;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.security.auth.login.LoginException;

public class Bot {
    public static JDA jda;
    public static String serverId;

    public static void main(String[] args) throws LoginException {
        jda = new JDABuilder(AccountType.BOT).setToken("NzQ5Nzk3MDY1MTkzOTQ3MzE4.X0xM7g._PAcOQJzZawb2mYphPVsFf34qHo").build();

        jda.getPresence().setStatus(OnlineStatus.ONLINE);

        jda.getPresence().setActivity(Activity.playing("Among Us"));

        // TODO: unify these lines
        jda.addEventListener(new Clear(), new Info(), new Mute(), new Start());


        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Capture capture = new Capture();

        GlobalScreen.addNativeKeyListener(capture);
    }

}
