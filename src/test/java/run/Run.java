package run;

import PersonDataFetcher.PersonsData;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;

public class Run {

    public static void main(String[] args) throws Exception {
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        File schemaFile = new File(Run.class.getClassLoader().getResource("schema.graphqls").getFile());

        TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);


        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
        String mutation = "mutation savePerson{\n" +
                "\tsavePerson(name: \"Sanjaya\", age: 30){\n" +
                "\t\tname\n" +
                "\t\tage\n" +
                "\t\tid\n" +
                "\t}\n" +
                "}";
        System.out.println(build.execute(mutation).getData().toString());

        String query = "{\n" +
                "   allPersons{\n" +
                "    name\n" +
                "    age\n" +
                "  \tposts {\n" +
                "  \t  id\n" +
                "      title\n" +
                "  \t}\n" +
                "  }\n" +
                "}";

        ExecutionResult executionResult = build.execute(query);
        System.out.println(executionResult.getData().toString());

    }

    private static RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("QueryType", typeWiring -> typeWiring
                        .dataFetcher("allPersons", PersonsData.getAllPersons())
                ).type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("savePerson", PersonsData.savePersons()))
                .build();
    }

}
