package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Employee> list = new ArrayList<>();

		System.out.println("Enter file path: ");
		String sourceFileStr = sc.nextLine(); //Lendo o caminho do arquivo fonte//

		File sourceFile = new File(sourceFileStr); //Instanciando o arquivo com o caminho lido//
		String sourceFolderStr = sourceFile.getParent(); //Instanciando a pasta do arquivo com o arquivo fonte//
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir(); //Criando uma pasta a partir do arquivo fonte//
		
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv"; //Criando um arquivo a partir da pasta fonte//
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) { //Instanciando o recurso atraves do "caminho" sourceFileStr//
			
			String dice = br.readLine(); //Criou uma String para ler o arquivo//
			while (dice != null) { //Enquanto o arquivo não for nulo//
				
				String[] fields = dice.split(",");
				String name = fields[0];
				String email = fields[1];
				int years = Integer.parseInt(fields[2]);
				
				list.add(new Employee (name, email, years));//Adicionando na lista //

				dice = br.readLine(); //Lendo o arquivo//
		}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) { //Instanciando o recurso atraves do "caminho" targetFileStr//

				for (Employee epy : list) {
					bw.write(epy.getName() + "," + epy.getYears() + "," + epy.getEmail()); //Escrever na tela//
					bw.newLine(); //Quebra de linha //
				}

				System.out.println(targetFileStr + " CREATED!");

		} catch (IOException e) { //Tratando as exceções//
			System.out.println("Error writing file: " + e.getMessage());
		}

		} catch (IOException e) { //Tratando as exceções//
		System.out.println("Error reading file: " + e.getMessage());
	}

	sc.close();
	}
}
	
