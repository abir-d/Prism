import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;


import com.lowagie.text.DocumentException;


public class WebToPDF {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, DocumentException{
		String inputFile = "test.htm";
		String url = new File(inputFile).toURI().toURL().toString();
        String outputFile = "firstdoc.pdf";
        OutputStream os = new FileOutputStream(outputFile);
        
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(os);
        
        os.close();

	}

}
