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
package org.jboss.hal.ballroom.chart;

import java.util.HashMap;
import java.util.Map;

import elemental2.core.Array;
import elemental2.dom.HTMLElement;
import jsinterop.base.Any;
import jsinterop.base.JsPropertyMap;
import jsinterop.base.JsPropertyMapOfAny;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.hal.ballroom.Attachable;
import org.jboss.hal.ballroom.JsHelper;

import static elemental2.dom.DomGlobal.window;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.hal.ballroom.chart.Donut.Legend.NONE;

public class Donut implements IsElement<HTMLElement>, Attachable {

    public enum Legend {
        NONE(200, 171), RIGHT(251, 161), BOTTOM(271, 191);

        final int width;
        final int height;
        final double ratio;

        Legend(int width, int height) {
            this.width = width;
            this.height = height;
            this.ratio = (double) width / height;
        }
    }


    public static class Builder {

        private final String unit;
        private final Map<String, String> columns;
        private final Map<String, String> names;
        private Legend legend;
        private int width;
        private boolean responsive;

        public Builder(String unit) {
            this.unit = unit;
            this.columns = new HashMap<>();
            this.names = new HashMap<>();
            this.legend = NONE;
            this.width = -1;
            this.responsive = false;
        }

        public Builder add(String id, String text, String color) {
            columns.put(id, color);
            names.put(id, text);
            return this;
        }

        public Builder legend(Legend legend) {
            this.legend = legend;
            return this;
        }

        public Builder responsive(boolean responsive) {
            this.responsive = responsive;
            return this;
        }

        public Donut build() {
            return new Donut(this);
        }
    }


    private final Builder builder;
    private final HTMLElement root;
    private final Options options;
    private Api api;

    private Donut(Builder builder) {
        this.builder = builder;

        root = div().id().asElement();
        options = Charts.get().defaultDonutOptions();
        options.bindto = "#" + root.id;
        options.donut.title = builder.unit;
        options.legend.show = builder.legend != NONE;
        options.data = new Options.Data();
        options.data.colors = JsHelper.asJsMap(builder.columns);
        options.data.columns = new Array<>();
        options.data.names = JsHelper.asJsMap(builder.names);
        options.data.type = "donut";
        options.size.width = builder.width != -1 ? builder.width : builder.legend.width;
        options.size.height = builder.width != -1 ? (int) (builder.width / builder.legend.ratio) : builder.legend.height;
        options.tooltip.show = true;
        options.tooltip.contents = Charts.tooltipContents;
    }

    @Override
    public void attach() {
        if (api == null) {
            api = C3.generate(options);
            if (builder.responsive) {
                window.onresize = event -> {
                    resizeInParent();
                    return null;
                };
                resizeInParent();
            }
        }
    }

    @Override
    public void detach() {
        if (api != null) {
            api.destroy();
            api = null;
            window.onresize = null;
        }
    }

    private Api api() {
        if (api == null) {
            throw new IllegalStateException(
                    "Donut is not attached. Call Donut.attach() before using any of the API methods!");
        }
        return api;
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    @SuppressWarnings("unchecked")
    public void update(Map<String, Long> data) {
        long total = 0;
        JsPropertyMapOfAny dataMap = JsPropertyMap.of();
        Array<Array<Any>> columns = new Array<>();
        for (Map.Entry<String, Long> entry : data.entrySet()) {
            String key = entry.getKey();
            long value = entry.getValue();
            total += value;
            Array<Any> column = new Array<>();
            column.push(Any.of(key), Any.of(value));
            columns.push(column);
        }
        Charts.setDonutChartTitle("#" + root.id, String.valueOf(total), builder.unit);
        dataMap.set("columns", columns); //NON-NLS
        api().load(dataMap);
    }

    @SuppressWarnings("HardCodedStringLiteral")
    public void resize(int width) {
        JsPropertyMapOfAny dimension = JsPropertyMap.of();
        dimension.set("width", Any.of(width));
        dimension.set("height", Any.of(width / builder.legend.ratio));
        api().resize(dimension);
    }

    private void resizeInParent() {
        HTMLElement parent = (HTMLElement) root.parentNode;
        resize((int) parent.offsetWidth);
    }
}