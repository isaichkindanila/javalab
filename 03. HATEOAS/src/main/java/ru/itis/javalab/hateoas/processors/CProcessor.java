package ru.itis.javalab.hateoas.processors;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab.hateoas.controllers.CController;
import ru.itis.javalab.hateoas.models.C;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
                model.add(linkTo(methodOn(CController.class).makeQWE(data.getId())).withRel("makeQWE"));
                break;

            case QWE:
                model.add(links.linkToSearchResource(C.class, LinkRelation.of("qwe")).withRel("similar"));
                model.add(linkTo(methodOn(CController.class).makeASD(data.getId())).withRel("makeASD"));
                break;
        }

        return model;
    }
}
