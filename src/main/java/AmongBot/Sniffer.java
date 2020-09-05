package AmongBot;

import net.sourceforge.jpcap.capture.PacketCapture;

public class Sniffer {

    /**
     * Constants
     * */
    private static final int INFINITE = -1;
    private static final int PACKET_COUNT = INFINITE;
    private static final String FILTER = "";

    /**
     * Constructor
     * */
    public Sniffer(String device) throws Exception {

        PacketCapture packetCapture = new PacketCapture();

        System.out.println("Using device '" + device + "'");

        packetCapture.open(device, true);
        packetCapture.setFilter(FILTER, true);
        packetCapture.addPacketListener(new GamePacketHandler());

        System.out.println("Capturing packets...");
        packetCapture.capture(PACKET_COUNT);

    }

    public static void main(String[] args) {
        try {
            if (args.length == 1) {
                Sniffer sniffer = new Sniffer(args[0]);
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
