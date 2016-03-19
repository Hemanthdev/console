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
package org.jboss.hal.ballroom.layout;

import elemental.dom.Element;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.hal.ballroom.Tabs;
import org.jboss.hal.resources.CSS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.jboss.hal.ballroom.layout.LayoutBuilder.State.*;
import static org.jboss.hal.resources.CSS.offset;
import static org.jboss.hal.resources.CSS.row;

/**
 * Builder for complex layouts. Supports rows, columns, (sub)headers, tabs and content in form
 * of {@link IsElement} and {@link Element}.
 * <p>
 * Rows can contain columns and columns can contain (sub)headers, tabs and content.
 *
 * @author Harald Pehl
 */
public class LayoutBuilder {

    enum State {NONE, ROW, COLUMN, TABS}


    private static final String HEADER_ELEMENT = "headerElement";

    private final Elements.Builder eb;
    private State state;
    private Tabs tabs;

    public LayoutBuilder() {
        this.eb = new Elements.Builder();
        this.state = NONE;
    }

    /**
     * Starts a row. Needs to be closed with {@link #end()}. You always need at least one row. Rows can contain columns
     * only.
     */
    public LayoutBuilder row() {
        assertState(NONE);
        state = ROW;
        eb.div().css(row);
        return this;
    }

    /**
     * Starts a column. Needs to be closed with {@link #end()}. Columns can contain (sub)headers, elements or tabs.
     */
    public LayoutBuilder column() {
        return column(0, 12);
    }

    public LayoutBuilder column(int offset, int columns) {
        assertState(ROW);
        state = COLUMN;
        eb.div().css(rowCss(offset, columns));
        return this;
    }

    /**
     * Adds a header which doesn't need to be closed. Only allowed inside columns.
     */
    public LayoutBuilder header(String title) {
        assertState(COLUMN);
        eb.h(1).textContent(title).rememberAs(HEADER_ELEMENT).end();
        return this;
    }

    /**
     * @return a reference to the header element.
     */
    public Element headerElement() {
        return eb.referenceFor(HEADER_ELEMENT);
    }

    /**
     * Adds a sub-header which doesn't need to be closed. Only allowed inside columns.
     */
    public LayoutBuilder subheader(String title) {
        assertState(COLUMN);
        eb.h(3).textContent(title).end();
        return this;
    }

    /**
     * Adds arbitrary content. Only allowed inside columns.
     */
    public LayoutBuilder add(Element first, Element... rest) {
        List<Element> elements = new ArrayList<>();
        elements.add(first);
        if (rest != null) {
            Collections.addAll(elements, rest);
        }
        return add(elements);
    }

    /**
     * Adds arbitrary content. Only allowed inside columns.
     */
    public LayoutBuilder add(Iterable<Element> elements) {
        assertState(COLUMN);
        for (Element element : elements) {
            eb.add(element);
        }
        return this;
    }

    /**
     * Enters the tab mode. Needs to be closed with {@link #end()}.
     */
    public LayoutBuilder tabs() {
        assertState(COLUMN);
        state = TABS;
        tabs = new Tabs();
        return this;
    }

    /**
     * Adds a tab, which doesn't need to be closed. Only allowed inside tab mode.
     */
    public LayoutBuilder tab(String id, String title, Element first, Element... rest) {
        assertState(TABS);
        tabs.add(id, title, first, rest);
        return this;
    }

    public LayoutBuilder end() {
        switch (state) {
            case NONE:
                throw new IllegalStateException("LayoutBuilder.end() not allowed for state NONE");
            case ROW:
                eb.end();
                state = NONE;
                break;
            case COLUMN:
                eb.end();
                state = ROW;
                break;
            case TABS:
                eb.add(tabs.asElement());
                tabs = null;
                break;
        }
        return this;
    }

    public Element build() {
        return eb.build();
    }

    public Iterable<Element> elements() {
        return eb.elements();
    }

    private String rowCss(int offset, int columns) {
        return offset == 0 ? CSS.column(columns) : offset(offset) + " " + CSS.column(columns);
    }

    private void assertState(State first, State... rest) {
        Set<State> required = EnumSet.of(first);
        if (rest != null) {
            required.addAll(asList(rest));
        }
        if (!required.contains(this.state)) {
            throw new IllegalStateException("Illegal state in LayoutBuilder. Required: " + required + ", given: " +
                    this.state + ".");
        }
    }

    private void assertNoTabs() {
        if (tabs != null) {
            throw new IllegalStateException("Not allowed inside tabs");
        }
    }
}