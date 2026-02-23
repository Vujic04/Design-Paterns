package strategy;

import java.io.File;

public interface LoadStrategy {
	void load(File file) throws Exception;
}
