package org.apache.storm.jms.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FixMsg {
	private String message;
	private String tplFile;
	
	public FixMsg(String tplFile){
		this.tplFile = tplFile;
	}
	
	public String getMessage() {
		if((message== null) || (message.isEmpty()))
			readTemplate();
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private void readTemplate() {
		Path path = null;
		try {
			URI uri = getClass().getClassLoader().getResource(tplFile).toURI();
			System.out.println("path is" + uri.toString());
			//path = Paths.get(getClass().getClassLoader().getResource(tplFile).toURI());
			path = Paths.get("/opt/code/storm/stormexp/jms-producer/src/main/resources/fixorder1");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final StringBuilder data = new StringBuilder();
		Stream<String> lines = null;
		try {
			lines = Files.lines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lines.forEach(line -> data.append(line).append("\n"));
		lines.close();
		message = data.toString();
	}

}
