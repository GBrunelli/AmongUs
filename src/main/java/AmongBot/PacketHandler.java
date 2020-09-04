package AmongBot;

import net.sourceforge.jpcap.capture.PacketListener;
import net.sourceforge.jpcap.net.Packet;
import net.sourceforge.jpcap.net.TCPPacket;

import java.nio.charset.StandardCharsets;

class PacketHandler implements PacketListener {

    /**
     *
     * */
    public void packetArrived(Packet packet) {
        try {
            // only handle TCP packets

            if (packet instanceof TCPPacket) {
                TCPPacket tcpPacket = (TCPPacket) packet;
                byte[] data = tcpPacket.getTCPData();

                String srcHost = tcpPacket.getSourceAddress();
                String dstHost = tcpPacket.getDestinationAddress();
                String isoData = new String(data, StandardCharsets.ISO_8859_1);

                System.out.println(srcHost + " -> " + dstHost + ": " + isoData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}