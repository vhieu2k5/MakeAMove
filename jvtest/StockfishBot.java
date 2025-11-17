package jvtest;

import java.io.*;

public class StockfishBot {
    private Process engineProcess;
    private BufferedReader processReader;
    private BufferedWriter processWriter;

    public boolean startEngine(String pathToEngine) {
        try {
            ProcessBuilder pb = new ProcessBuilder(pathToEngine);
            pb.redirectErrorStream(true);
            engineProcess = pb.start();
            processReader = new BufferedReader(new InputStreamReader(engineProcess.getInputStream()));
            processWriter = new BufferedWriter(new OutputStreamWriter(engineProcess.getOutputStream()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendCommand(String command) {
        try {
            processWriter.write(command + "\n");
            processWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOutput(int waitTimeMs) {
        StringBuilder buffer = new StringBuilder();
        try {
            Thread.sleep(waitTimeMs);
            while (processReader.ready()) {
                buffer.append(processReader.readLine()).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public String getBestMove(String fen, int depth) {
        sendCommand("position fen " + fen);
        sendCommand("go depth " + depth);

        String output;
        String bestmove = null;
        try {
            while (true) {
                output = processReader.readLine();
                if (output == null) break;
                if (output.startsWith("bestmove")) {
                    bestmove = output.split(" ")[1];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bestmove;
    }

    public void stopEngine() {
        sendCommand("quit");
        try {
            processReader.close();
            processWriter.close();
            engineProcess.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
