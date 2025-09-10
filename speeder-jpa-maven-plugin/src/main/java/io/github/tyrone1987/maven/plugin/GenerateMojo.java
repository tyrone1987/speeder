package io.github.tyrone1987.maven.plugin;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import io.github.tyrone1987.data.model.*;
import io.github.tyrone1987.generator.spi.*;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Generar clases de persistencia
 *
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GenerateMojo extends AbstractMojo {

    /**
     * @parameter default-value="${project}"
     * @goal generate
     */
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    /**
     * El camino al modelo de datos de speeder.
     *
     * @parameter dataModel="${dataModel}"
     *
     * default-value="src/main/speeder/model.xml"
     */
    @Parameter(
            property = "dataModel",
            defaultValue = "src/main/speeder/model.xml",
            required = true
    )
    private File dataModel;

    /**
     * Lista de generadores de codigo a usar
     *
     * @parameter
     */
    @Parameter
    private List<String> codeGenerators;

    /**
     * Configuracion de los generadores de codigo
     *
     * @parameter
     */
    @Parameter
    private Map<String, String> codeGeneratorsConfig;

    @Override
    public void execute() throws MojoExecutionException {
        try {
            DataModel dm = this.parserXmlDataModel();

            String genSrcRoot = project.getBuild().getDirectory() + "/generated-sources/speeder/";
            String genResRoot = project.getBuild().getDirectory() + "/generated-resources/speeder/";

            project.addCompileSourceRoot(genSrcRoot);
            Resource r = new Resource();
            r.setDirectory(genResRoot);
            project.addResource(r);

            SrcCodeGenProperties prop = new SrcCodeGenProperties();

            prop.setProject(project);
            prop.setDataModel(dm);
            prop.setGeneratedSourcesDir(genSrcRoot);
            prop.setGeneratedResourcesDir(genResRoot);
            if (codeGeneratorsConfig == null) {
                codeGeneratorsConfig = new HashMap();
            }
            prop.setConfig(codeGeneratorsConfig);

            this.executeSpiDataModelSourceCodeGenerator(prop);
            this.executeSpiEntitySourceCodeGenerator(prop);
            this.executeSpiEnumSourceCodeGenerator(prop);
            this.executeSpiInterfaceSourceCodeGenerator(prop);
        } catch (JAXBException ex) {
            getLog().error(ex.getLocalizedMessage());
            throw new MojoExecutionException(ex.getMessage());
        } catch (MojoExecutionException ex) {
            getLog().error(ex.getLocalizedMessage());
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(GenerateMojo.class.getName()).log(Level.SEVERE, null, ex);
            getLog().error(ex.getLocalizedMessage());
            throw new MojoExecutionException(ex.getMessage());
        }
    }

    private void executeSpiDataModelSourceCodeGenerator(SrcCodeGenProperties prop) {
        for (var plugin : ServiceLoader.load(SrcCodeGenDataModel.class)) {
            try {
                if (codeGenerators == null || codeGenerators.isEmpty() || codeGenerators.contains(plugin.getGeneratorName())) {
                    plugin.execute(prop);
                }
            } catch (Exception ex) {
                Logger.getLogger(GenerateMojo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void executeSpiEntitySourceCodeGenerator(SrcCodeGenProperties prop) {
        if (prop.getDataModel().getEntitys() != null) {
            for (SrcCodeGenEntity plugin : ServiceLoader.load(SrcCodeGenEntity.class)) {
                prop.getDataModel().getEntitys().stream().forEachOrdered(entity -> {
                    try {
                        if (codeGenerators == null || codeGenerators.isEmpty() || codeGenerators.contains(plugin.getGeneratorName())) {
                            plugin.execute(prop, entity);
                        } else {
                            getLog().error("no code");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(GenerateMojo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
    }

    private void executeSpiEnumSourceCodeGenerator(SrcCodeGenProperties prop) {
        if (prop.getDataModel().getEnums() != null) {
            for (var plugin : ServiceLoader.load(SrcCodeGenEnum.class)) {
                prop.getDataModel().getEnums().forEach(enumerator -> {
                    try {
                        if (codeGenerators == null || codeGenerators.isEmpty() || codeGenerators.contains(plugin.getGeneratorName())) {
                            plugin.execute(prop, enumerator);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(GenerateMojo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
    }

    private void executeSpiInterfaceSourceCodeGenerator(SrcCodeGenProperties prop) {
        if (prop.getDataModel().getInterfaces() != null) {
            for (var plugin : ServiceLoader.load(SrcCodeGenInterface.class)) {
                prop.getDataModel().getInterfaces().forEach(entityInterface -> {
                    try {
                        if (codeGenerators == null || codeGenerators.isEmpty() || codeGenerators.contains(plugin.getGeneratorName())) {
                            plugin.execute(prop, entityInterface);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(GenerateMojo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
    }

    private DataModel parserXmlDataModel() throws JAXBException, MojoExecutionException, Exception {
        try {
            JAXBContext context = JAXBContext.newInstance(DataModel.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            DataModel dm = (DataModel) unmarshaller.unmarshal(dataModel);

            if (StringUtils.isBlank(dm.getPackage())) {
                throw new MojoExecutionException("No se especifico el paquete java para el modelo de datos.");
            }

            getLog().info("Successfully parsed data model with package: " + dm.getPackage());
            return dm;
        } catch (JAXBException | MojoExecutionException e) {
            getLog().error("Error parsing XML: " + e.getMessage(), e);
            throw e;
        }
    }
}
