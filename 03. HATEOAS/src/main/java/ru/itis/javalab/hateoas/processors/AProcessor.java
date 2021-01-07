package ru.itis.javalab.hateoas.processors;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab.hateoas.models.A;

@Component
@AllArgsConstructor
public class AProcessor implements RepresentationModelProcessor<EntityModel<A>> {

    private final RepositoryEntityLinks links;

    @SuppressWarnings("ConstantConditions")
    @Override
    public EntityModel<A> process(EntityModel<A> model) {
        var data = model.getContent();
        return model.removeLinks()
                .add(links.linkToItemResource(A.class, data.getId()).withSelfRel())
                .add(links.linkToCollectionResource(A.class).withRel("all"));
    }
}
