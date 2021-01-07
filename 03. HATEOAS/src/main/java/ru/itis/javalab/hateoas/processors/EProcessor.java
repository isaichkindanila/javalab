package ru.itis.javalab.hateoas.processors;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab.hateoas.models.E;

@Component
@AllArgsConstructor
public class EProcessor implements RepresentationModelProcessor<EntityModel<E>> {

    private final RepositoryEntityLinks links;

    @SuppressWarnings("ConstantConditions")
    @Override
    public EntityModel<E> process(EntityModel<E> model) {
        var data = model.getContent();
        return model.removeLinks()
                .add(links.linkToItemResource(E.class, data.getId()).withSelfRel())
                .add(links.linkToCollectionResource(E.class).withRel("all"));
    }
}
