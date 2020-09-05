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
import net.sourceforge.jpcap.capture.PacketCapture;

import javax.security.auth.login.LoginException;

public class Bot {
    public static JDA jda;
    public static String serverID;
    public static String serverIP;

    public static void main(String[] args) throws LoginException {
        jda = new JDABuilder(AccountType.BOT).setToken("NzQ5Nzk3MDY1MTkzOTQ3MzE4.X0xM7g._PAcOQJzZawb2mYphPVsFf34qHo").build();

        jda.getPresence().setStatus(OnlineStatus.ONLINE);

        jda.getPresence().setActivity(Activity.playing("Among Us"));

        // TODO: unify these lines
        jda.addEventListener(new Clear(), new Info(), new Mute(), new Start());

        try {
            if (args.length == 1) {
                new Sniffer(args[0]);
            }
            else {
                System.out.println("Available network devices on your machine:");
                String[] devices = PacketCapture.lookupDevices();

                for (String dev : devices) {
                    System.out.println("Device :\t" + dev);
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }
    }
}
