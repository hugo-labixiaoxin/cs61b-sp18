import synthesizer.GuitarString;

public class GuitarHero {

    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static void main(String[] args) {
        GuitarString[] gs = new GuitarString[keyboard.length()];
        for (int i = 0; i < keyboard.length(); i += 1) {
            GuitarString stringAlpha = new GuitarString(440 * Math.pow(2, (i - 24) / 12));
            gs[i] = stringAlpha;
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index < 0) {
                    continue;
                }
                gs[index].pluck();
            }

            double sample = 0.0;
            for (int i = 0; i < 37; i += 1) {
                sample += gs[i].sample();
            }

            StdAudio.play(sample);
            for (int i = 0; i < 37; i += 1) {
                gs[i].tic();
            }

        }

    }
}
