package uk.ac.gla.terrier.jtreceval;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

/**
 * Hello world!
 *
 */
public class trec_eval 
{
	static File trec_eval_temp = null;
	
	static String getOSShort()
	{
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows"))
			return "win";
		if (osName.startsWith("Linux"))
			return "linux";
		if (osName.equals("Mac OS X"))
			return "macosx";
		throw new UnsupportedOperationException("Unsupported os: " + osName);
	}
	
	static File getTrecEvalBinary()
	{
		if (trec_eval_temp != null)
			return trec_eval_temp;
		final String resName = "trec_eval-" + getOSShort() + "-" + System.getProperty("os.arch");
		if (trec_eval.class.getClassLoader().getResource(resName) == null)
			throw new UnsupportedOperationException("Unsupported os/arch: " + resName);
		
		File tempExec = null;
		try{
			tempExec = File.createTempFile("trec_eval", ".exe");
			InputStream in = trec_eval.class.getClassLoader().getResourceAsStream(resName);
			OutputStream out = new BufferedOutputStream(new FileOutputStream(tempExec));			
			IOUtils.copy(in, out);
			in.close();
			out.close();
			tempExec.setExecutable(true);			
			
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
		return tempExec;
	}
	
	File ourTE;
	int exit = Integer.MAX_VALUE;
	
	public trec_eval()
	{
		ourTE = getTrecEvalBinary();
	}
	
	ProcessBuilder getBuilder(String[] args)
	{
		List<String> cmd = new ArrayList<String>();
		cmd.add(ourTE.getAbsolutePath().toString());
		for(String arg : args)
			cmd.add(arg);
		return new ProcessBuilder(cmd);
	}
	
	public String[][] runAndGetOutput(String[] args)
	{
		List<String[]> output = new ArrayList<String[]>();
		try{
			ProcessBuilder pb = getBuilder(args);
			Process p = pb.start();
			InputStream in = p.getInputStream();
			LineIterator it = IOUtils.lineIterator(new InputStreamReader(in));			
			while(it.hasNext())
			{
				output.add(it.next().split("\\s+"));
			}
			p.waitFor();
			exit = p.exitValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (exit != 0)
			throw new RuntimeException("trec_eval ended with non-zero exit code ("+exit+")");
		return output.toArray(new String[output.size()][]);
	}
	
	public int getLastExitCode()
	{
		return exit;
	}
	
	public int run(String[] args) {		
		try{
			ProcessBuilder pb = getBuilder(args);
			pb.redirectError(Redirect.INHERIT);
			pb.redirectOutput(Redirect.INHERIT);		
			Process p = pb.start();
			p.waitFor();
			exit = p.exitValue();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			exit = -1;
		}
		return exit;		
	}
	
    public static void main( String[] args )
    {
        System.exit(new trec_eval().run(args));
    }
}
