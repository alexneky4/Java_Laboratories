package uaic.info.catalog.commands;

import uaic.info.catalog.Catalog;
import uaic.info.document.Document;

public class ListCommand implements CatalogCommand{

    public static void list(Catalog catalog)
    {
            System.out.println(catalog.getDocs());
    }
}
