package br.com.processadordocumentos;

import br.com.processadordocumentos.service.RegistroService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessadorDocumentos {

	public static void main(String[] args) throws IOException {
		try (WatchService watchService = FileSystems.getDefault().newWatchService()){
	        RegistroService service = new RegistroService();
	
	        Path inputPath = Paths.get(System.getProperty("user.home")
	                        .concat(File.separator)
	                        .concat("data")
	                        .concat(File.separator)
	                        .concat("in"));
	        Path outputPath = Paths.get(System.getProperty("user.home")
	                        .concat(File.separator)
	                        .concat("data")
	                        .concat(File.separator)
	                        .concat("out"));
	        
	        List<File> arquivos = Files.walk(inputPath)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
	        if (!arquivos.isEmpty()) {
	        	for (File file : arquivos) {
	        		String nomeArquivo = file.getName(); 
	        		if (".dat".equalsIgnoreCase(nomeArquivo.substring(nomeArquivo.length() - 4))) {
	        			
	                    Path inputFilePath = inputPath.resolve((Path) file.toPath());
	                    Path outputFilePath = outputPath.resolve(nomeArquivo.replace(".dat", ".done.dat"));
	
	                    System.out.println("Lendo arquivo de ".concat(inputFilePath.toString()));
	                    List<String> linhas = Files.readAllLines(inputFilePath);
	
	                    System.out.println("processando...");
	                    service.registrarTudo(linhas);
	                    
	                    try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath)) {
	                        writer.write(service.getRelatrio());
	                        System.out.println("Resultado do processamento em ".concat(outputFilePath.toString()));
	                    }
	                }
	        	}
	        }
	        
	
	        inputPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
	        WatchKey key;
	        try {
		        while ((key = watchService.take()) != null) {
		            for (WatchEvent<?> event : key.pollEvents()) {
		
		                String nomeArquivo = event.context().toString();
		
		                if (".dat".equalsIgnoreCase(nomeArquivo.substring(nomeArquivo.length() - 4))) {
		
		                    Path inputFilePath = inputPath.resolve((Path) event.context());
		                    Path outputFilePath = outputPath.resolve(nomeArquivo.replace(".dat", ".done.dat"));
		
		                    System.out.println("Lendo arquivo de ".concat(inputFilePath.toString()));
		                    List<String> linhas = Files.readAllLines(inputFilePath);
		
		                    System.out.println("processando...");
		                    service.registrarTudo(linhas);
		                    
		                    try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath)) {
		                        writer.write(service.getRelatrio());
		                        System.out.println("Resultado do processamento em ".concat(outputFilePath.toString()));
		                    }
		                }
		            }
		            key.reset();
		        }
	        }catch (ClosedWatchServiceException e) {
	        	e.printStackTrace();
	        }  
	        catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}
}
