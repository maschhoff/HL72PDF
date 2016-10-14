/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.ztgnrw;

import dev.ztgnrw.htmlconverter.HtmlConverter;
import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author web
 */
public class HL72PDF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length < 3) {
            System.err.println("Usage HL72PDF [cda] [stylesheet] [output filename]");
            return;
        }

        String cda_file, stylesheet, output_file;
        cda_file = args[0];
        stylesheet = args[1];
        output_file = args[2];

        try {
            System.out.println("************* Stating the Transformation *************");
            //XML to HTML          
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(new StreamSource(stylesheet));
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(new StreamSource(cda_file), result);
            String html = writer.toString();
            //HTML TO PDF with Attachment
            HtmlConverter.fromStringToPDF(html,output_file,cda_file);  
            System.out.println("************* The result is in "+output_file+".pdf *************");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
