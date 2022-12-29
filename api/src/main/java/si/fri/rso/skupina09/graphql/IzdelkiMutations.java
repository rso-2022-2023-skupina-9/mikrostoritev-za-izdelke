package si.fri.rso.skupina09.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import si.fri.rso.skupina09.lib.Izdelek;
import si.fri.rso.skupina09.services.beans.IzdelekBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@GraphQLClass
@ApplicationScoped
public class IzdelkiMutations {

    @Inject
    private IzdelekBean izdelekBean;

    @GraphQLMutation
    public Izdelek addIzdelek(@GraphQLArgument(name = "izdelek") Izdelek izdelek) {
        izdelek = izdelekBean.createIzdelek(izdelek);
        return izdelek;
    }

    @GraphQLMutation
    public DeleteResponse deleteIzdelek(@GraphQLArgument(name = "id") Integer id) {
        return new DeleteResponse(izdelekBean.deleteIzdelek(id));
    }
}
