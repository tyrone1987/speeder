
package io.github.tyrone1987.generator.spi;

import java.util.Map;
import org.apache.maven.project.MavenProject;
import io.github.tyrone1987.data.model.DataModel;

public class SrcCodeGenProperties
{
    private MavenProject project;

    private String generatedSourcesDir;

    private String generatedResourcesDir;

    private DataModel dataModel;
    
    private Map<String, String> config;

    public SrcCodeGenProperties()
    {
    }

    public DataModel getDataModel()
    {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel)
    {
        this.dataModel = dataModel;
    }

    public String getGeneratedResourcesDir()
    {
        return generatedResourcesDir;
    }

    public void setGeneratedResourcesDir(String generatedResourcesDir)
    {
        this.generatedResourcesDir = generatedResourcesDir;
    }

    public String getGeneratedSourcesDir()
    {
        return generatedSourcesDir;
    }

    public void setGeneratedSourcesDir(String generatedSourcesDir)
    {
        this.generatedSourcesDir = generatedSourcesDir;
    }

    public MavenProject getProject()
    {
        return project;
    }

    public void setProject(MavenProject project)
    {
        this.project = project;
    }

    public Map<String, String> getConfig()
    {
        return config;
    }

    public void setConfig(Map<String, String> config)
    {
        this.config = config;
    }
}
