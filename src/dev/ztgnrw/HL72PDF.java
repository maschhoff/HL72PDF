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
 * @author Mathias Aschhoff ZTG 2016 m.aschhoff@ztg-nrw.de
 */
public class HL72PDF {

    /**
     * Main method for CommandLine options it needs 3 args
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length < 3) {
            System.err.println("Usage HL72PDF [cda] [stylesheet] [output filename]");
            return;
        };

        convertToPDF(args[0], args[1], args[2]);

    }

    /**
     * This method converts a xml with xslt stylesheet to PDF As Params you have
     * 3 Strings the CDA filepath, the stylesheet path and the filename for the
     * output file.
     *
     * @param cda_file CDA filepath
     * @param stylesheet XSLT filepath
     * @param output_file filename for outputfile
     */
    public static void convertToPDF(String cda_file, String stylesheet, String output_file) {
        try {
            System.out.println("************* Stating the Transformation *************");
            //XML to HTML          
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(new StreamSource(stylesheet));
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(new StreamSource(cda_file), result);
            //HTML TO PDF with Attachment
            HtmlConverter.fromStringToPDF(writer.toString(), output_file, cda_file);
            System.out.println("************* The result is in " + output_file + ".pdf *************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
