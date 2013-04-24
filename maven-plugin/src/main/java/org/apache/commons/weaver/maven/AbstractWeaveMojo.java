/*
 *  Copyright the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.commons.weaver.maven;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.commons.weaver.WeaveProcessor;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Defines common properties.
 */
public abstract class AbstractWeaveMojo extends AbstractMojo {

    @Parameter(defaultValue = "false")
    protected boolean verbose;

    @Parameter(property = "weaver.config", required = false)
    protected Properties weaverConfig;

    protected abstract List<String> getClasspath();

    protected abstract File getTarget();

    @Override
    public void execute() throws MojoExecutionException {
        final JavaLoggingToMojoLoggingRedirector logRedirector = new JavaLoggingToMojoLoggingRedirector(getLog());
        logRedirector.activate();

        final List<String> classpath = getClasspath();
        final File target = getTarget();
        final Properties config = weaverConfig == null ? new Properties() : weaverConfig;

        getLog().debug(String.format("classpath=%s%ntarget=%s%nconfig=%s", classpath, target, config));

        try {
            final WeaveProcessor wp = new WeaveProcessor(classpath, target, config);
            wp.weave();
        } catch (Exception e) {
            throw new MojoExecutionException("weaving failed due to " + e.getMessage(), e);
        } finally {
            logRedirector.deactivate();
        }
    }

}
