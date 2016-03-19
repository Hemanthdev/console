/*
 * Copyright 2015-2016 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.hal.core.modelbrowser;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import elemental.dom.Element;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.HasElements;
import org.jboss.hal.ballroom.IdBuilder;
import org.jboss.hal.ballroom.PatternFly;
import org.jboss.hal.ballroom.Tabs;
import org.jboss.hal.ballroom.tree.Node;
import org.jboss.hal.core.mbui.form.ModelNodeForm;
import org.jboss.hal.dmr.ModelNode;
import org.jboss.hal.dmr.dispatch.Dispatcher;
import org.jboss.hal.dmr.model.Operation;
import org.jboss.hal.dmr.model.ResourceAddress;
import org.jboss.hal.meta.Metadata;
import org.jboss.hal.resources.Ids;
import org.jboss.hal.resources.Resources;

import static org.jboss.hal.core.modelbrowser.ModelBrowser.PLACE_HOLDER_ELEMENT;
import static org.jboss.hal.dmr.ModelDescriptionConstants.INCLUDE_RUNTIME;
import static org.jboss.hal.dmr.ModelDescriptionConstants.READ_RESOURCE_OPERATION;
import static org.jboss.hal.resources.CSS.lead;

/**
 * Panel which holds the resource description, the model node form and a detailed description of the attributes and
 * operations.
 *
 * @author Harald Pehl
 */
class ResourcePanel implements HasElements {

    private static final String DESCRIPTION_ELEMENT = "descriptionElement";
    private static final String EMPTY_ELEMENT = "emptyElement";

    private final ModelBrowser modelBrowser;
    private final Dispatcher dispatcher;
    private final Resources resources;
    private final Elements.Builder builder;
    private final Element description;
    private final Element empty;
    private final String dataId;
    private final String attributesId;
    private final String operationsId;
    final Tabs tabs;

    ResourcePanel(final ModelBrowser modelBrowser,
            final Dispatcher dispatcher,
            final Resources resources) {
        this.modelBrowser = modelBrowser;
        this.dispatcher = dispatcher;
        this.resources = resources;

        dataId = IdBuilder.build(Ids.MODEL_BROWSER, "resource", "data", "tab");
        attributesId = IdBuilder.build(Ids.MODEL_BROWSER, "resource", "attributes", "tab");
        operationsId = IdBuilder.build(Ids.MODEL_BROWSER, "resource", "operations", "tab");

        tabs = new Tabs();
        tabs.add(dataId, resources.constants().data(), PLACE_HOLDER_ELEMENT);
        tabs.add(attributesId, resources.constants().attributes(), PLACE_HOLDER_ELEMENT);
        tabs.add(operationsId, resources.constants().operations(), PLACE_HOLDER_ELEMENT);

        // @formatter:off
        builder = new Elements.Builder()
            .p().css(lead).rememberAs(DESCRIPTION_ELEMENT).end()
            .p().rememberAs(EMPTY_ELEMENT).textContent(resources.constants().noAttributes()).end()
            .add(tabs.asElement());
        // @formatter:on

        description = builder.referenceFor(DESCRIPTION_ELEMENT);
        empty = builder.referenceFor(EMPTY_ELEMENT);
        Elements.setVisible(empty, false);
    }

    @Override
    public Iterable<Element> asElements() {
        return builder.elements();
    }

    void update(Node<Context> node, ResourceAddress address, Metadata metadata) {
        SafeHtml safeHtml = SafeHtmlUtils.fromSafeConstant(metadata.getDescription().getDescription());
        Elements.innerHtml(this.description, safeHtml);

        tabs.setContent(dataId, PLACE_HOLDER_ELEMENT);
        tabs.setContent(attributesId, PLACE_HOLDER_ELEMENT);
        tabs.setContent(operationsId, PLACE_HOLDER_ELEMENT);
        Elements.setVisible(tabs.asElement(), description.hasAttributes());
        Elements.setVisible(empty, !description.hasAttributes());

        if (description.hasAttributes()) {
            Operation operation = new Operation.Builder(READ_RESOURCE_OPERATION, address)
                    .param(INCLUDE_RUNTIME, true)
                    .build();
            dispatcher.execute(operation, result -> {
                ModelNodeForm<ModelNode> form = new ModelNodeForm.Builder<>(
                        IdBuilder.build(Ids.MODEL_BROWSER, node.id, "form"), metadata)
                        .includeRuntime()
                        .onReset(modelBrowser::reset)
                        .onSave((f, changedValues) -> modelBrowser.save(address, changedValues))
                        .build();
                tabs.setContent(dataId, form.asElement());
                PatternFly.initComponents();
                form.attach();
                form.view(result);
            });

            tabs.setContent(attributesId,
                    new AttributesTable(metadata.getDescription().getAttributes(), resources).asElement());
            if (metadata.getDescription().hasOperations()) {
                tabs.setContent(operationsId,
                        new OperationsTable(metadata.getDescription().getOperations(), resources).asElement());
            }
        }
    }

    void show() {
        Elements.setVisible(description, true); // the remaining elements are managed in update()
    }

    void hide() {
        for (Element element : asElements()) {
            Elements.setVisible(element, false);
        }
    }
}