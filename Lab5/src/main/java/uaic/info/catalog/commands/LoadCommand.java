package uaic.info.catalog.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import uaic.info.catalog.Catalog;
import uaic.info.catalog.commands.CatalogCommand;
import uaic.info.exceptions.InvalidCatalogException;

import java.io.File;

public class LoadCommand implements CatalogCommand {

    public static Catalog load(String path) throws InvalidCatalogException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            Catalog catalog = objectMapper.readValue(
                    new File(path),
                    Catalog.class
            );

            return catalog;
        } catch (Exception ex){
            throw new InvalidCatalogException(ex);
        }
    }
}
