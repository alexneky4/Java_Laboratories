package uaic.info.node;

public interface Node {

    String getName();

    default int getWeight()
    {
        return 0;
    }
}
