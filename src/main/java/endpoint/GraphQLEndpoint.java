package endpoint;

import PersonRepository.PersonsInMemoryRepository;
import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;
import mutation.Mutation;
import query.Query;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private final static PersonsInMemoryRepository personsRepository = new PersonsInMemoryRepository();

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                        new Query(personsRepository), new Mutation(personsRepository))
                .build()
                .makeExecutableSchema();
    }

    @Override
    protected GraphQLContext createContext(Optional<HttpServletRequest> request, Optional<HttpServletResponse> response) {
        return super.createContext(request, response);
    }
}
