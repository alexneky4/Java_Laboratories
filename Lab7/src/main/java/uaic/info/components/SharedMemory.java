package uaic.info.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SharedMemory {

    private final List<Token> tokens = new ArrayList<>();

    public SharedMemory(int n)
    {
        for(int i = 1; i <= n * n * n; i++)
        {
            Token token = new Token(i);
            tokens.add(token);
        }

        Collections.shuffle(tokens);
    }

    public synchronized List<Token> extractTokens(int howMany)
    {
        List<Token> extracted = new ArrayList<>();
        for(int i = 0; i < howMany; i++)
        {
            if(tokens.isEmpty() == true)
                break;

            extracted.add(tokens.get(0));
            tokens.remove(0);
        }
        return extracted;
    }
}
