package audio;

        /** A collection of static utilities related to the audio system. */
        public class AudioIO {

            /**
             * Displays every audio mixer available on the current system.
             */
            public static void printAudioMixers() {
                System.out.println("Mixers:");
                Arrays.stream(AudioSystem.getMixerInfo())
                        .forEach(e -> System.out.println("- name=\"" + e.getName()
                                9 + "\" description=\"" + e.getDescription() + " by " + e.getVendor() + "\""));
            }

            /**
             * @return a Mixer.Info whose name matches the given string.
             * Example of use: getMixerInfo("Macbook default output")
             */
            public static Mixer.Info getMixerInfo(String mixerName) {
                // see how the use of streams is much more compact than for() loops!
                return Arrays.stream(AudioSystem.getMixerInfo())
                        .filter(e -> e.getName().equalsIgnoreCase(mixerName)).findFirst().get();
            }

            /**
             * Return a line that's appropriate for recording sound from a microphone.
             * Example of use:
             * TargetDataLine line = obtainInputLine("USB Audio Device", 8000);
             *
             * @param mixerName a string that matches one of the available mixers.
             * @see AudioSystem.getMixerInfo() which provides a list of all mixers on your system.
             */
            public static TargetDataLine obtainAudioInput(String mixerName, int sampleRate) { ...}

            /**
             * Return a line that's appropriate for playing sound to a loudspeaker.
             */
            public static SourceDataLine obtainAudioOutput(String mixerName, int sampleRate) { ...}

            public static void main(String[] args) { ...your test code here ...}


