package strategy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import command.Command;
import controller.Controller;

public class LoadLogStrategy implements LoadStrategy {
    private final Controller controller;
    private final JFrame parent;

    public LoadLogStrategy(Controller controller, JFrame parent) {
        this.controller = controller;
        this.parent = parent;
    }

    @Override
    public void load(File file) throws Exception {
        List<String> lines = readAllLines(file);

        controller.clearAllForLoad();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            int choice = JOptionPane.showConfirmDialog(
                parent,
                "Step " + (i + 1) + "/" + lines.size() + ":\n" + line + "\n\nExecute this step?",
                "Load log",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (choice != JOptionPane.OK_OPTION) break;

            if (line.startsWith("UNDO ")) {
                controller.undoFromLoad();
                controller.appendLogLine(line);  
                continue;
            }
            if (line.startsWith("REDO ")) {
                controller.redoFromLoad();
                controller.appendLogLine(line);  
                continue;
            }

            Command cmd = controller.parseLogLineToCommand(line);
            if (cmd == null) {
                JOptionPane.showMessageDialog(parent,
                    "Cannot parse line:\n" + line,
                    "Parse error",
                    JOptionPane.ERROR_MESSAGE);
                break;
            }

            controller.executeCommandFromLoad(cmd);
        }
    }
    
    private List<String> readAllLines(File file) throws Exception {
        List<String> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String s;
            while ((s = br.readLine()) != null) out.add(s);
        }
        return out;
    }
}