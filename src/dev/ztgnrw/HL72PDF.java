/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.ztgnrw;

import dev.ztgnrw.htmlconverter.HtmlConverter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        if (args.length < 1) {
            usage();
            System.exit(1);
        } else {
            switch (args[0]) {
                case "-h":
                    usage();
                    break;
                case "-c":
                    checkParameterAndConvert(args);
                    break;
                case "-e":
                    checkParameterAndExtract(args);
                    break;
                default:
                    usage();
                    break;
            }
        }

    }

    private static void checkParameterAndConvert(String[] args) {
        if (args.length < 4) {
            usage();
            System.exit(1);
        } else {

            convertToPDF(args[1], args[2], args[3]);
        }
    }

    private static void checkParameterAndExtract(String[] args) {
        if (args.length < 2) {
            usage();
            System.exit(1);
        } else {

            try {
                ExtractEmbeddedFiles.extractEmbeddedFiles(args[1]);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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

    /**
     * This will print the usage for this program.
     */
    private static void usage() {
        System.err.println("Usage: java " + ExtractEmbeddedFiles.class.getName() + " [option] [params*]");
        System.err.println(" -c [cda] [stylesheet] [output filename] | Create PDF from XML");
        System.err.println(" -e [pdf file] | Extract attachement from PDF");
        System.err.println(" -h | Prints this help");
    }

}
