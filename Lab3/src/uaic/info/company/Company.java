package uaic.info.company;

import uaic.info.node.Node;

public class Company implements Node,Comparable<Company> {

    private String name;

    @Override
    public String getName()
    {
        return this.name;
    }


    @Override
    public int compareTo(Company company) {
        return this.name.compareTo(company.name);
    }
}
