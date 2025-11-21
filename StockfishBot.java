package jvtest;

import java.io.*;
import java.util.concurrent.*;

public class StockfishBot {

    private Process engineProcess;
    private BufferedWriter processWriter;
    private BlockingQueue<String> outputQueue = new LinkedBlockingQueue<>();

    public boolean startEngine(String pathToEngine) {
        try {
            ProcessBuilder pb = new ProcessBuilder(pathToEngine);
            pb.redirectErrorStream(true);
            engineProcess = pb.start();

            processWriter = new BufferedWriter(new OutputStreamWriter(engineProcess.getOutputStream()));

            // THREAD ĐỌC OUTPUT
            new Thread(() -> {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(engineProcess.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        outputQueue.offer(line);
                    }
                } catch (Exception ignored) {}
            }).start();

            sendCommand("uci");
            return true;
        } catch (Exception e) {
            System.out.println("Cannot start Stockfish!");
            e.printStackTrace();
            return false;
        }
    }

    public void sendCommand(String command) {
        try {
            if (engineProcess.isAlive()) {
                processWriter.write(command + "\n");
                processWriter.flush();
            }
        } catch (Exception e) {
            System.out.println("Cannot send command: " + command);
            e.printStackTrace();
        }
    }

    public String getBestMove(String fen, int depth) {
        outputQueue.clear(); // clear output cũ

        sendCommand("position fen " + fen);
        sendCommand("go depth " + depth);

        try {
            while (true) {
                String line = outputQueue.poll(3, TimeUnit.SECONDS);
                if (line == null) return null; // timeout

                if (line.startsWith("bestmove")) {
                    return line.split(" ")[1];
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void stopEngine() {
        try {
            sendCommand("quit");
            processWriter.flush();
        } catch (Exception ignored) {}

        try {
            engineProcess.waitFor(1, TimeUnit.SECONDS);
        } catch (Exception ignored) {}

        if (engineProcess.isAlive()) {
            engineProcess.destroy();
        }
    }
}
