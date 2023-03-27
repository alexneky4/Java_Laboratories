package uaic.info.testing;

import freemarker.template.TemplateException;
import org.junit.Test;
import uaic.info.catalog.Catalog;
import uaic.info.catalog.CatalogUtil;
import uaic.info.catalog.commands.ListCommand;
import uaic.info.catalog.commands.ReportCommand;
import uaic.info.catalog.commands.ViewCommand;
import uaic.info.document.Document;
import uaic.info.exceptions.InvalidCatalogException;
import uaic.info.exceptions.InvalidCatalogPathException;

import java.io.IOException;
import java.util.List;

public class FunctionalityTest {

    @Test
    public void addingDocuments() {
        Document document1 = new Document();
        Document document2 = new Document();

        document1.setId("1");
        document1.setTitle("Document1");
        document1.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document2.setId("2");
        document2.setTitle("Document2");
        document2.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document1.addTag("Attempts", 2);
        document2.addTag("Jumps", 3);


        Catalog catalog = new Catalog();
        catalog.setName("SNotes");
        System.out.println(catalog.getDocs());
        catalog.add(document1);
        catalog.add(document2);
        System.out.println(catalog.getDocs());
    }
    @Test
    public void saveAndLoadJSON(){
        Document document1 = new Document();
        Document document2 = new Document();

        document1.setId("1");
        document1.setTitle("Document1");
        document1.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document2.setId("2");
        document2.setTitle("Document2");
        document2.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document1.addTag("Attempts", 2);
        document2.addTag("Jumps", 3);

        Catalog catalog = new Catalog();
        catalog.setName("SNotes");
        catalog.add(document1);
        catalog.add(document2);

        try{
            CatalogUtil.saveJSON(catalog,"C:\\Users\\alexa\\Desktop\\JavaTest\\testing.json");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try {
            Catalog catalog2 = CatalogUtil.loadJSON("C:\\Users\\alexa\\Desktop\\JavaTest\\testing.json");
            System.out.println(catalog2);
        }
        catch (InvalidCatalogException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void saveAndLoadBinary()
    {
        Document document1 = new Document();
        Document document2 = new Document();

        document1.setId("1");
        document1.setTitle("Document1");
        document1.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document2.setId("2");
        document2.setTitle("Document2");
        document2.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document1.addTag("Attempts", 4);
        document2.addTag("Jumps", 5);

        Catalog catalog = new Catalog();
        catalog.setName("SNotes");
        catalog.add(document1);
        catalog.add(document2);

        try{
            CatalogUtil.saveBinary(catalog,"C:\\Users\\alexa\\Desktop\\JavaTest\\testing2");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try {
            Catalog catalog2 = CatalogUtil.loadBinary("C:\\Users\\alexa\\Desktop\\JavaTest\\testing2");
            System.out.println(catalog2);
        }
        catch (InvalidCatalogException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void viewCommands()
    {
        Document document1 = new Document();
        Document document2 = new Document();

        document1.setId("1");
        document1.setTitle("Document1");
        document1.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document2.setId("2");
        document2.setTitle("Document2");
        document2.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document1.addTag("Attempts", 4);
        document2.addTag("Jumps", 5);

        Catalog catalog = new Catalog();
        catalog.setName("SNotes");
        catalog.add(document1);
        catalog.add(document2);

        try
        {
            ViewCommand.view("C:\\Users\\alexa\\Desktop\\JavaTest\\testing2");
        }
        catch (InvalidCatalogPathException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void loadCommand()
    {
        Document document1 = new Document();
        Document document2 = new Document();

        document1.setId("1");
        document1.setTitle("Document1");
        document1.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document2.setId("2");
        document2.setTitle("Document2");
        document2.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document1.addTag("Attempts", 4);
        document2.addTag("Jumps", 5);

        Catalog catalog = new Catalog();
        catalog.setName("SNotes");
        catalog.add(document1);
        catalog.add(document2);
        ListCommand.list(catalog);
    }

    @Test
    public void reportCommand()
    {
        Document document1 = new Document();
        Document document2 = new Document();

        document1.setId("1");
        document1.setTitle("Document1");
        document1.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document2.setId("2");
        document2.setTitle("Document2");
        document2.setLocation("C:\\Users\\alexa\\Desktop\\JavaTest");

        document1.addTag("Attempts", 4);
        document2.addTag("Jumps", 5);

        Catalog catalog = new Catalog();
        catalog.setName("SNotes");
        catalog.add(document1);
        catalog.add(document2);

        try
        {
            ReportCommand.report(catalog,"C:\\Users\\alexa\\Desktop\\Java_Laboratories\\Lab5\\src\\main\\java\\uaic\\info\\catalog\\commands");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
    }
}
