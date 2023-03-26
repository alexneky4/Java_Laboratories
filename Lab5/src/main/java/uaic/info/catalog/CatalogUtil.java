package uaic.info.catalog;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uaic.info.exceptions.InvalidCatalogException;

import java.io.*;

public class CatalogUtil {

    public static void saveJSON(Catalog catalog, String path) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog loadJSON(String path) throws InvalidCatalogException,IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            Catalog catalog = objectMapper.readValue(
                    new File(path),
                    Catalog.class
            );

            return catalog;
        } catch (DatabindException | StreamReadException ex){
            throw new InvalidCatalogException(ex);
        }
        catch (IOException ex){
            throw ex;
        }
    }

    public static void saveBinary(Catalog catalog, String path) throws IOException
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)))
        {
            oos.writeObject(catalog);
        }
    }

    public static Catalog loadBinary(String path) throws InvalidCatalogException
    {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path)))
        {
            Catalog catalog = (Catalog) ois.readObject();
            return catalog;
        }
        catch(FileNotFoundException ex)
        {
            throw new InvalidCatalogException(ex);
        }
        catch(IOException ex)
        {
            throw new InvalidCatalogException(ex);
        }
        catch(ClassNotFoundException ex)
        {
            throw new InvalidCatalogException(ex);
        }
    }
}
