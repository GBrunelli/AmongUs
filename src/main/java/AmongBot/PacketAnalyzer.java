package AmongBot;

import net.sourceforge.jpcap.net.UDPPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacketAnalyzer {

    public static final int MATCHMAKING_PACKET = 100;
    public static final int PING_PACKET = 101;
    public static final int MEETING_START_PACKET = 102;
    public static final int MEETING_END_PACKET = 103;
    public static final int USELESS_PACKET = -1;

    private static final List<String> MATCHMAKING_IP = new ArrayList<>(Arrays.asList(
            "50.116.1.42",
            "45.79.40.75",
            "104.237.135.186",
            "198.58.115.57",
            "192.58.99.71")
    );

    /**
     *  Returns true if the packet indicates a meeting, false if it's not.
     */
    public static int analyze(UDPPacket udpPacket){
        byte[] data = udpPacket.getData();

        if(MATCHMAKING_IP.contains(udpPacket.getSourceAddress())){

            // DEBUGGING TOOL
            String srcHost = udpPacket.getSourceAddress();
            String dstHost = udpPacket.getDestinationAddress();
            // DEBUGGING TOOL

            if (isIpPacket(data)) {

                String ip;
                ip = Byte.toUnsignedInt(data[6]) + "." + Byte.toUnsignedInt(data[7]) + "." +
                        Byte.toUnsignedInt(data[8]) + "." + Byte.toUnsignedInt(data[9]);

                // DEBUGGING TOOL
                System.out.println(srcHost + " -> " + dstHost + ": SERVER IP = " + ip + "\n");
                // DEBUGGING TOOL

                Bot.serverIP = ip;

            }
            return MATCHMAKING_PACKET;
        }
        return USELESS_PACKET;
    }

    private static boolean isIpPacket(byte[] data){
        if (data.length == 14) {
            return (data[0] == 1) && (data[1] == 0) && (data[4] == 0) &&
                    (data[5] == 13) && (data[12] == 0) && (data[13] == 0);
        }
        return false;
    }
}