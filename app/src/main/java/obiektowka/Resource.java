package obiektowka;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Resource implements Writer {
	private static int next = 0;
	public int id;

	@Override
	public void write(BufferedWriter writer) throws IOException {
		writer.write("resource\n");
		writer.write(this.id + "\n");
	}
}
