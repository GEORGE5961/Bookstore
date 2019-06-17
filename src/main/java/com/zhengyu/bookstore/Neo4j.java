package com.zhengyu.bookstore;

import org.neo4j.driver.v1.*;

import static org.neo4j.driver.v1.Values.parameters;


public class Neo4j implements AutoCloseable
{
    private final Driver driver;

    public Neo4j( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void printUsername( final String name )
    {
        try ( Session session = driver.session() )
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    /*
                    StatementResult result = tx.run( "CREATE (a:User) " +
                                    "SET a.name = $name " +
                                    "RETURN a.message + ', from node ' + id(a)",
                            parameters( "name", name ) );*/
                    StatementResult result = tx.run( "MATCH (a:User),(b:User) " +
                                    "WHERE a.name = 'Nancy' AND b.name = 'Tom' " +
                                    "CREATE (a)-[r:Follow]->(b);");
                    return "Success";
                }
            } );
            System.out.println( greeting );
        }
    }

    public static void test( String name )
    {
        Neo4j greeter = new Neo4j( "bolt://localhost:7687", "neo4j", "000" );

        greeter.printUsername( name );

    }
}
