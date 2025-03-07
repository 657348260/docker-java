package com.github.dockerjava.core.command;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.dockerjava.api.command.ListImagesCmd;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.util.FiltersBuilder;

/**
 * List images
 */
public class ListImagesCmdImpl extends AbstrDockerCmd<ListImagesCmd, List<Image>> implements ListImagesCmd {

    private String imageNameFilter;

    private Boolean showAll = false;

    private FiltersBuilder filters = new FiltersBuilder();

    public ListImagesCmdImpl(ListImagesCmd.Exec exec) {
        super(exec);
    }

    @Override
    public Map<String, List<String>> getFilters() {
        return filters.build();
    }

    @Override
    public Boolean hasShowAllEnabled() {
        return showAll;
    }

    @Override
    public ListImagesCmd withShowAll(Boolean showAll) {
        this.showAll = showAll;
        return this;
    }

    @Override
    public ListImagesCmd withDanglingFilter(Boolean dangling) {
        checkNotNull(dangling, "dangling have not been specified");
        withFilter("dangling", Collections.singletonList(dangling.toString()));
        return this;
    }

    @Override
    public ListImagesCmd withLabelFilter(String... labels) {
        checkNotNull(labels, "labels have not been specified");
        filters.withLabels(labels);
        return this;
    }

    @Override
    public ListImagesCmd withLabelFilter(Map<String, String> labels) {
        checkNotNull(labels, "labels have not been specified");
        filters.withLabels(labels);
        return this;
    }

    @Override
    public ListImagesCmd withImageNameFilter(String imageNameFilter) {
        checkNotNull(imageNameFilter, "image name filter not specified");
        this.imageNameFilter = imageNameFilter;
        return this;
    }

    @Override
    public ListImagesCmd withReferenceFilter(String reference) {
        checkNotNull(reference, "reference filter not specified");
        withFilter("reference", Collections.singletonList(reference));
        return this;
    }

    @Override
    public ListImagesCmd withFilter(String key, Collection<String> values) {
        checkNotNull(key, "key not specified");
        checkNotNull(values, "values not specified");
        filters.withFilter(key, values);
        return this;
    }

    @Override
    public String getImageNameFilter() {
        return this.imageNameFilter;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
