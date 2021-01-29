package rs.ac.bg.etf.pp1;

import rs.etf.pp1.mj.runtime.Run;
import rs.etf.pp1.mj.runtime.disasm;

public class RunTest{
	
	public static void main (String[] arg) {
		Run run = new Run();
		String[] debug = {"-debug", arg[0]};
		String[] noDebug = {arg[0]};
		String[] disasmArgs = {arg[0]};
		
		MJParserTest mjParserTest = new MJParserTest();
		disasm disasm = new disasm();
		String[] parserArgs = {arg[1], arg[2]};
		
		boolean doDebug = false;
		boolean doNoDebug = true;
		boolean doDisasm = false;
		
		try {
			mjParserTest.main(parserArgs);
			if(mjParserTest.parseSuccessful)
			{
				if (doDebug) {
					System.out.println("\n***********************************************************\nDebug program.mj:");
					run.main(debug);
				}
				if (doNoDebug)
				{
					System.out.println("\n***********************************************************\nOutput program.mj:");
					run.main(noDebug);
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
