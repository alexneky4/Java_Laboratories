package uaic.info.catalog.commands;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import uaic.info.catalog.Catalog;
import uaic.info.document.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MetadataExtractor implements CatalogCommand{

    public static void info(Catalog catalog) throws TikaException, IOException, SAXException {
        for(Document doc : catalog.getDocs())
        {
            File file = new File(doc.getLocation());
            Parser parser = new AutoDetectParser();
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(file);
            ParseContext context = new ParseContext();

            parser.parse(inputstream, handler, metadata, context);
            for(String name : metadata.names())
            {
                String value = metadata.get(name);
                if(!value.contains("parser"))
                {
                    doc.addTag(name,value);
                    System.out.println(name + ": " + value);
                }

            }
        }
    }
}
