package platformer;

import java.io.*;

public class Filer {

	public static String stringFromFile(String path) {
		BufferedReader in = null;
		String result = "";
		String line;
		try {
			in = new BufferedReader(new FileReader(path));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
