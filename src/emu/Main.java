package emu;

import chip.Chip;

public class Main extends Thread {

    private Chip chip;
    private ChipFrame frame;

    public Main() {
        chip = new Chip();
        chip.init();
        chip.loadProgram("pong2.c8");

        frame = new ChipFrame(chip);
    }

    @Override
    public void run() {
        // 60Hz = 60 updates per second
        while (true) {
            chip.setKeyBuffer(frame.getKeyBuffer());
            chip.run();

            if(chip.isNeedsRedraw()) {
                frame.repaint();
                chip.removeDrawFlag();
            }

            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                // Unhandled Exception
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
