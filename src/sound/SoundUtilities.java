package sound;

import javax.sound.sampled.AudioFormat;

public class SoundUtilities {

    public SoundUtilities() {

    }

    public double volumeRMS(double[] raw) {
        double sum = 0d;
        if (raw.length == 0) {
            return sum;
        } else {
            for (int ii = 0; ii < raw.length; ii++) {
                sum += raw[ii];
            }
        }
        double average = sum / raw.length;

        double sumMeanSquare = 0d;
        for (int ii = 0; ii < raw.length; ii++) {
            sumMeanSquare += Math.pow(raw[ii] - average, 2d);
        }
        double averageMeanSquare = sumMeanSquare / raw.length;
        double rootMeanSquare = Math.pow(averageMeanSquare, 0.5d);

        return rootMeanSquare;
    }

    private double[] bytesToDouble(byte[] bytes) {
        if (bytes.length % 2 != 0)
            return null;
        double[] result = new double[bytes.length / 2];
        for (int i = 0; i < result.length; i++) {
            double temp = bytesToDouble(bytes[i * 2], bytes[i * 2 + 1]);
            result[i] = temp;
        }
        return result;
    }

    private double bytesToDouble(byte firstByte, byte secondByte) {
        short s = (short) ((secondByte << 8) | firstByte);
        return s / 32768.0;
    }

    public double getRMS(byte[] message) {
        double[] convertedToDouble = bytesToDouble(message);
        return (volumeRMS(convertedToDouble) * 1000);
    }

    public AudioFormat getAudioFormat() {
        float sampleRate = 16000.0f;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

}

