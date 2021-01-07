package ru.itis.javalab.mongo.processors;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab.mongo.models.C;

@Component
@AllArgsConstructor
public class CProcessor implements RepresentationModelProcessor<EntityModel<C>> {

    private final RepositoryEntityLinks links;

    @SuppressWarnings("ConstantConditions")
    @Override
    public EntityModel<C> process(EntityModel<C> model) {
        var data = model.getContent();
        model.removeLinks()
                .add(links.linkToItemResource(C.class, data.getId()).withSelfRel())
                .add(links.linkToCollectionResource(C.class).withRel("all"));

        switch (data.getValue()) {
            case ASD:
                model.add(links.linkToSearchResource(C.class, LinkRelation.of("asd")).withRel("similar"));
                break;

            case QWE:
                model.add(links.linkToSearchResource(C.class, LinkRelation.of("qwe")).withRel("similar"));
                break;
        }

        return model;
    }
}
