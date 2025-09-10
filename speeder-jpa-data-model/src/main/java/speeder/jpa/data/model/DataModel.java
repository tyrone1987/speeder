package speeder.jpa.data.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "dataModel")
public class DataModel {

    private String modelPackage;

    private List<Entity> entitys;

    private List<Enumerator> enums;

    private Map<String, Entity> entitysMap;

    private List<EntityInterface> interfaces;

    private List<ImportPackage> imports;

    @XmlAttribute
    public String getPackage() {
        return modelPackage;
    }

    public void setPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    @XmlElementWrapper(name = "entitys")
    @XmlElements({
        @XmlElement(name = "entity", type = Entity.class)
    })
    public List<Entity> getEntitys() {
        return entitys;
    }

    public void setEntitys(List<Entity> entitys) {
        this.entitys = entitys;
    }

    @XmlElementWrapper(name = "interfaces")
    @XmlElements({
        @XmlElement(name = "interface", type = EntityInterface.class)
    })
    public List<EntityInterface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<EntityInterface> interfaces) {
        this.interfaces = interfaces;
    }

    @XmlElementWrapper(name = "enums")
    @XmlElements({
        @XmlElement(name = "enum", type = Enumerator.class)
    })
    public List<Enumerator> getEnums() {
        return enums;
    }

    public void setEnums(List<Enumerator> enums) {
        this.enums = enums;
    }

    @XmlTransient
    public Map<String, Entity> getEntitysMap() {
        if (entitysMap == null) {
            entitysMap = new HashMap<String, Entity>();
            for (Entity entity : entitys) {
                entitysMap.put(entity.getName(), entity);
            }
        }
        return entitysMap;
    }

    @XmlElementWrapper(name = "imports")
    @XmlElements({
        @XmlElement(name = "import", type = ImportPackage.class)
    })
    public List<ImportPackage> getImports() {
        return imports;
    }

    public void setImports(List<ImportPackage> imports) {
        this.imports = imports;
    }
}
