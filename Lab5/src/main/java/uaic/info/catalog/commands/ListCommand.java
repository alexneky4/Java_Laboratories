package uaic.info.catalog.commands;

import uaic.info.catalog.Catalog;
import uaic.info.document.Document;

public class ListCommand implements CatalogCommand{

    public static void list(Catalog catalog)
    {
        for(Document document : catalog.getDocs())
        {
            System.out.println(document);
        }
    }
}
