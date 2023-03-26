package uaic.info.catalog.commands;

import uaic.info.document.Document;
import uaic.info.exceptions.InvalidCatalogException;
import uaic.info.exceptions.InvalidCatalogPathException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ViewCommand implements CatalogCommand{
    public static void view(String path) throws InvalidCatalogPathException
    {
        Desktop desktop = Desktop.getDesktop();
        try
        {
            URI uri = new URI(path);
            desktop.browse(uri);
        }
        catch (URISyntaxException ex)
        {
            try
            {
                File file = new File(path);
                desktop.open(file);
            }
            catch (IOException e)
            {
                throw new InvalidCatalogPathException(e);
            }
        }
        catch (IOException e)
        {
            throw new InvalidCatalogPathException(e);
        }
    }
}
