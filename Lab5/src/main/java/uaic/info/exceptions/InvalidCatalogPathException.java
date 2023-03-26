package uaic.info.exceptions;

public class InvalidCatalogPathException extends Exception{

    public InvalidCatalogPathException(Exception ex)
    {
        super("Invalid catalog path:",ex);
    }
}
