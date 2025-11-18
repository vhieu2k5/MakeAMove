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
            System.out.println("Cannot Start the engine!");
            e.printStackTrace();
            return false;
        }
    }

    public void sendCommand(String command) {
        if (processWriter!=null && engineProcess.isAlive())
        {
             try {
            
            processWriter.write(command + "\n");
            processWriter.flush();
            
            
        } catch (IOException e) {
            System.out.println("Cannot send the command!");
            e.printStackTrace();
        }
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
            System.out.println("Cannot getoutput the engine!");
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
            System.out.println("Cannot get the best move!");
            e.printStackTrace();
        }
        return bestmove;
    }

public void stopEngine() {
    try {
        // 1. Gửi lệnh quit để engine tự tắt
        if (engineProcess.isAlive()) {
            sendCommand("quit"); 
            // Cần flush ngay lập tức để đảm bảo lệnh được gửi
            processWriter.flush(); 
        }

        // 2. Đóng luồng ghi (Đây là nơi lỗi xảy ra: writer.close())
        // Nếu đã gọi lệnh quit, tiến trình con sẽ đóng pipe, gây ra lỗi khi bạn gọi close()
        // Cần đóng luồng một cách an toàn hơn.
        
        // 3. Chờ tiến trình kết thúc (timeout là an toàn)
        engineProcess.waitFor(1, java.util.concurrent.TimeUnit.SECONDS); 
        
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    } catch (IOException e) {
        // Chỉ ghi log lỗi khi cố gắng đóng, không cần phải crash.
        System.err.println("Warning: Error closing pipe (may already be closed): " + e.getMessage());
    } finally {
        // Đảm bảo đóng writer và hủy tiến trình nếu cần
        try {
            if (processWriter != null) {
                processWriter.close();
            }
        } catch (IOException e) {
            // Log nhưng không làm gián đoạn
        }
        if (engineProcess != null && engineProcess.isAlive()) {
            engineProcess.destroy();
        }
    }
}
}
