public class Main {

    public static void main(String[] args) {
        WaveGrid grid = new WaveGrid(1000, 1000);

        WaveFrame frame = new WaveFrame(grid);

        long beginTime;
        long timeDiff;
        int sleepTime = 0;
        int framesSkipped;

        int max_fps = 25;
        int max_frame_skips = 10;
        int frame_period = 1000 / max_fps;

        while (true) {
            try {
                beginTime = System.currentTimeMillis();
                framesSkipped = 0;

                frame.updateLogic();
                frame.updateGraphics();

                timeDiff = System.currentTimeMillis() - beginTime;
                sleepTime = (int) (frame_period - timeDiff);

                if (sleepTime > 0) {
                    delay(sleepTime);
                }

                while (sleepTime < 0 && framesSkipped < max_frame_skips) {
                    frame.updateLogic();
                    sleepTime += frame_period;
                    framesSkipped++;
                }
            } catch (Error err) {
                System.out.println(err);
            }
        }
    }

    private static void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
