package uaic.info.testing;

import org.chocosolver.solver.constraints.nary.nvalue.amnv.differences.D;
import org.junit.Test;
import uaic.info.algorithm.Algorithm;
import uaic.info.catalog.Catalog;
import uaic.info.document.Document;

public class AlgorithmTest {

    @Test
    public void coloringTest()
    {
        Document doc1 = new Document();
        Document doc2 = new Document();
        Document doc3 = new Document();
        Document doc4 = new Document();
        Document doc5 = new Document();

        doc1.addTag("Tag1","tag");
        doc1.addTag("Tag2","tag");
        doc2.addTag("Tag1", "tag");
        doc2.addTag("Tag3", "tag");
        doc3.addTag("Tag3", "tag");
        doc4.addTag("Tag1","tag");
        doc5.addTag("Tag2","tag");

        Catalog catalog = new Catalog();
        catalog.add(doc1);
        catalog.add(doc2);
        catalog.add(doc3);
        catalog.add(doc4);
        catalog.add(doc5);

        Algorithm.coloringAlgorithm(catalog);
    }
}
