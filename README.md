# HL72PDF // CDA2PDF // FHIR2PDF // XML2PDF

This is XML2PDF Renderer which is speacial build up for converting HL7 CDA and HL7 FHIR into PDF and embedding the xml file.
This Project is based on my HTMLConverter and the Apache Xalan Projekt.
You´ll find examples in the example.zip file.

# Run it
To run this Projekt simply set your JAVA_HOME and run  
```  
java -jar HL72PDF.jar -c [cda file] [xslt stylesheet] [output filename without path]  
```  
Example  
```
java -jar ./HL72PDF.jar -c ./eLabormeldung.xml ./eLabormeldung.xsl DEeLabormeldung
```
To extract the attachment on CommandLine use:
```
java -jar HL72PDF.jar -e [path to pdf file]
```
# Embed it
To embed this into you application simply add HL72PDF.jar to your classpath as library and call the static method convertToPDF(String cda_file, String stylesheet, String output_file)
```
HL72PDF.convertToPDF("C:/tmp/cda.xml", "C:/tmp/stylesheets/xda.xml", "MyPDFFile");
```

to extract the content use extractEmbeddedFiles(String filepath)
```
ExtractEmbeddedFiles.extractEmbeddedFiles("C:/tmp/MyPDFFile.pdf");
```
