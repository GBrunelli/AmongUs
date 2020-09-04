package AmongBot;

import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.UDPPacket;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GamePacketHandler implements PacketListener {

    private static final List<String> MATCHMAKING_IP = new ArrayList<>(Arrays.asList(
            "50.116.1.42",
            "45.79.40.75",
            "104.237.135.186",
            "198.58.115.57")
    );

    public void packetArrived(Packet packet) {
        try {
            if (packet instanceof UDPPacket) {
                UDPPacket udpPacket= (UDPPacket) packet;
                byte[] data = udpPacket.getUDPData();

                if(     udpPacket.getDestinationAddress().equals(MATCHMAKING_IP.get(0)) ||
                        udpPacket.getSourceAddress().equals(MATCHMAKING_IP.get(0))      ||

                        udpPacket.getDestinationAddress().equals(MATCHMAKING_IP.get(1)) ||
                        udpPacket.getSourceAddress().equals(MATCHMAKING_IP.get(1))      ||

                        udpPacket.getDestinationAddress().equals(MATCHMAKING_IP.get(2)) ||
                        udpPacket.getSourceAddress().equals(MATCHMAKING_IP.get(2))      ||

                        udpPacket.getDestinationAddress().equals(MATCHMAKING_IP.get(3)) ||
                        udpPacket.getSourceAddress().equals(MATCHMAKING_IP.get(3))
                ) {
                    String srcHost = udpPacket.getSourceAddress();
                    String dstHost = udpPacket.getDestinationAddress();
                    String ascData = new String(data, StandardCharsets.US_ASCII);

                    System.out.println(srcHost + " -> " + dstHost + ": " + ascData + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}