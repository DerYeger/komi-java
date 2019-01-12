package eu.yeger.komi.controller;

import javafx.scene.media.AudioClip;

public class AudioController {

    static void playPawnPlacedAudioClip() {
        new AudioClip(AudioController.class.getResource("/sounds/pawn_placed.wav").toExternalForm()).play();
    }

    static void playRoundOverAudioClip() {
        new AudioClip(AudioController.class.getResource("/sounds/round_over.wav").toExternalForm()).play();
    }
}
