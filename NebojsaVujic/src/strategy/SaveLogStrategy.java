package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class SaveLogStrategy implements SaveStrategy {
	
	private final List<String> logLines;
	
	public SaveLogStrategy(List<String> logLines) {
		this.logLines=logLines;
	}
	
	
	@Override
	public void save(File file) throws Exception {
		try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            for (String line : logLines) {
                bw.write(line);
                bw.newLine();
            }
        }
	}

}
