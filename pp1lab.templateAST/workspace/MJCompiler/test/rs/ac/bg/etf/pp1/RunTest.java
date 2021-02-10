package rs.ac.bg.etf.pp1;

import java.util.Scanner;

import rs.etf.pp1.mj.runtime.Run;
import rs.etf.pp1.mj.runtime.disasm;

public class RunTest{
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Unesite putanju .mj fajla (prazan ulaz znaci da ce se procitati iz komandne linije): ");
		String inputProgram = in.nextLine();
		
		System.out.print("Unesite putanju .obj fajla (prazan ulaz znaci da ce se procitati iz komandne linije): ");
		String outputProgram = in.nextLine();
		
		if (inputProgram.equals("")) {
			inputProgram = args[0];
		}
		
		if (outputProgram.equals("")) {
			outputProgram = args[1];
		}
		
		String[] debug = {"-debug", outputProgram};
		String[] noDebug = {outputProgram};
		String[] disasmArgs = {outputProgram};
		String[] parserArgs = {inputProgram, outputProgram};
		
		boolean doDebug = false;
		boolean doNoDebug = true;
		boolean doDisasm = false;
		
		System.out.print("Da li zelite da se pokrene run sa -debug ruckom (prazna linija znaci da): ");
		doDebug = in.nextLine().equals("");
		System.out.print("Da li zelite da se pokrene run bez -debug ruckom (prazna linija znaci da): ");
		doNoDebug = in.nextLine().equals("");
		System.out.print("Da li zelite da se pokrene disasm (prazna linija znaci da): ");
		doDisasm = in.nextLine().equals("");
		
		System.out.print("\n***********************************************************\n");
		
		try {
			Compiler.main(parserArgs);
			if(Compiler.parseSuccessful)
			{
				if (doDebug) {
					System.out.println("\n***********************************************************\nDebug program.mj:");
					Run.main(debug);
				}
				if (doNoDebug)
				{
					System.out.println("\n***********************************************************\nOutput program.mj:");
					Run.main(noDebug);
				}
				if(doDisasm)
					{
						System.out.println("\n***********************************************************\nDisasm program.mj:");
						disasm.main(disasmArgs);
					}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
