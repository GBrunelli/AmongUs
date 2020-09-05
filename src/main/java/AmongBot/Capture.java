package AmongBot;

import Commands.Mute;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * TEST CLASS
 * */
public class Capture implements NativeKeyListener {

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) == "1"){
            if(Mute.getState() == false)
                Mute.muteEveryone();
            else
                Mute.unmuteEveryone();
        }
    }
}
