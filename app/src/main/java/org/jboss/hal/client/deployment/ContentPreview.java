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
package org.jboss.hal.client.deployment;

import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import org.jboss.hal.ballroom.Alert;
import org.jboss.hal.core.finder.FinderPath;
import org.jboss.hal.core.finder.PreviewAttributes;
import org.jboss.hal.core.finder.PreviewContent;
import org.jboss.hal.core.mvp.Places;
import org.jboss.hal.meta.token.NameTokens;
import org.jboss.hal.resources.Icons;
import org.jboss.hal.resources.Ids;
import org.jboss.hal.resources.Names;
import org.jboss.hal.resources.Resources;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.jboss.hal.dmr.ModelDescriptionConstants.EXPLODED;
import static org.jboss.hal.dmr.ModelDescriptionConstants.MANAGED;
import static org.jboss.hal.dmr.ModelDescriptionConstants.NAME;
import static org.jboss.hal.dmr.ModelDescriptionConstants.RUNTIME_NAME;
import static org.jboss.hal.resources.CSS.clickable;

/**
 * @author Harald Pehl
 */
class ContentPreview extends PreviewContent<Content> {

    ContentPreview(final ContentColumn column, final Content content, final Places places, final Resources resources) {
        super(content.getName());

        if (content.getAssignments().isEmpty()) {
            previewBuilder().add(
                    new Alert(Icons.DISABLED, resources.messages().unassignedContent(content.getName()),
                            resources.constants().assign(), event -> column.assign(content)));
        }

        PreviewAttributes<Content> attributes = new PreviewAttributes<>(content,
                asList(NAME, RUNTIME_NAME, MANAGED, EXPLODED)).end();
        previewBuilder().addAll(attributes);

        if (!content.getAssignments().isEmpty()) {
            previewBuilder().h(2).textContent(resources.constants().assignments()).end()
                    .p().innerHtml(resources.messages().assignedToDescription(content.getName())).end()
                    .ul();
            content.getAssignments().forEach(assignment -> {
                String serverGroup = assignment.getServerGroup();
                PlaceRequest serverGroupPlaceRequest = places.finderPlace(NameTokens.DEPLOYMENTS, new FinderPath()
                        .append(Ids.DEPLOYMENT_BROWSE_BY, Ids.asId(Names.SERVER_GROUPS))
                        .append(Ids.DEPLOYMENT_SERVER_GROUP, Ids.serverGroup(serverGroup)))
                        .build();
                String serverGroupToken = places.historyToken(serverGroupPlaceRequest);
                previewBuilder().li()
                        .a(serverGroupToken).textContent(serverGroup).end()
                        .span().textContent(" (").end()
                        .a().css(clickable)
                        .on(click, event -> column.unassign(content, serverGroup))
                        .textContent(resources.constants().unassign())
                        .end() // </a>
                        .span().textContent(")").end()
                        .end(); // </li>

            });
            previewBuilder().end(); // </ul>
        }
    }
}
