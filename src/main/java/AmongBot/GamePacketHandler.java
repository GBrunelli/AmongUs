package AmongBot;

import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.UDPPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GamePacketHandler implements PacketListener {

    private static final List<String> MATCHMAKING_IP = new ArrayList<>(Arrays.asList(
            "50.116.1.42",
            "45.79.40.75",
            "104.237.135.186",
            "198.58.115.57",
            "192.58.99.71")
    );

    public void packetArrived(Packet packet) {
        try {
            if (packet instanceof UDPPacket) {
                UDPPacket udpPacket = (UDPPacket) packet;
                PacketAnalyzer.analyze(udpPacket);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}