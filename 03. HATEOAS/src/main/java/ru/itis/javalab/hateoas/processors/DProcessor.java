package ru.itis.javalab.hateoas.processors;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab.hateoas.models.D;

@Component
@AllArgsConstructor
public class DProcessor implements RepresentationModelProcessor<EntityModel<D>> {

    private final RepositoryEntityLinks links;

    @SuppressWarnings("ConstantConditions")
    @Override
    public EntityModel<D> process(EntityModel<D> model) {
        var data = model.getContent();
        return model.removeLinks()
                .add(links.linkToItemResource(D.class, data.getId()).withSelfRel())
                .add(links.linkToCollectionResource(D.class).withRel("all"));
    }
}
