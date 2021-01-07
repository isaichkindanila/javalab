package ru.itis.javalab.hateoas.processors;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab.hateoas.models.B;

@Component
@AllArgsConstructor
public class BProcessor implements RepresentationModelProcessor<EntityModel<B>> {

    private final RepositoryEntityLinks links;

    @SuppressWarnings("ConstantConditions")
    @Override
    public EntityModel<B> process(EntityModel<B> model) {
        var data = model.getContent();
        return model.removeLinks()
                .add(links.linkToItemResource(B.class, data.getId()).withSelfRel())
                .add(links.linkToCollectionResource(B.class).withRel("all"));
    }
}
