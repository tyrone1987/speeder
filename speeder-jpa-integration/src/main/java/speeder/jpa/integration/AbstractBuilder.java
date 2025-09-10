package io.github.tyrone1987.integration;

import jakarta.persistence.EntityManager;
import io.github.tyrone1987.persistence.IEntity;

/**
 * Esta clase permite manipular un objeto T de manera facil mediante una
 * interfaz fluida (Fluent Interface)
 *
 * @param <T>
 */
public abstract class AbstractBuilder<T extends IEntity> implements EntityBuilder<T> {

    /**
     * El objeto entidad T interno a ser manipulado por esta instancia.
     */
    protected T entity;

    /**
     * El contexto de persistencia a usar.
     */
    protected EntityManager entityManager;

    /**
     * Crea un objeto AbstractBuilder, para crear un nuevo objeto vease el
     * metodo create(EntityManager)
     *
     * @param entity El objeto T a manipular.
     * @param entityManager Referencia al entityManager de la aplicacion
     */
    public AbstractBuilder(EntityManager entityManager, T entity) {
        if (entityManager == null) {
            throw new IllegalArgumentException("El parametro entityManager no puede ser null.");
        }

        if (entity == null) {
            throw new IllegalArgumentException("El parametro entity no puede ser null.");
        }

        this.entity = entity;
        this.entityManager = entityManager;
    }

    /**
     * Obtiene el objeto T interno.
     *
     * @return Una referencia al objeto T interno
     */
    @Override
    public T getEntity() {
        return entity;
    }

    /**
     * Resfresca el objeto T interno.
     *
     * @return Una referencia al objeto T interno
     */
    @Override
    public T refresh() {
        if (entity.getId() == null) {
            return null;
        }
        entity = (T) entityManager.find(entity.getClass(), entity.getId());
        entityManager.refresh(entity);
        return entity;
    }

    /**
     * Guarda el objeto T interno en la base de datos.
     *
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    @Override
    public AbstractBuilder<T> save() {
        try {
            if (!existsInDataBase()) {
                System.out.println("creaaaa");
                entityManager.persist(entity);
            } else {
                System.out.println("edita");
                entityManager.merge(entity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    /**
     * Elimina el objeto T interno de la base de datos.
     *
     * @return Una referencia al objeto this para continuas modificaciones.
     */
    @Override
    public AbstractBuilder<T> delete() {
        if (existsInDataBase()) {
            if (!entityManager.contains(entity)) {
                entityManager.remove(entityManager.merge(entity));
            } else {
                entityManager.remove(entity);
            }
        }
        return this;
    }

    /**
     * Determina cuando el objeto T interno existe en la base de datos o no.
     *
     * @return true Si el objeto T interno existe en la base de datos, false en
     * caso contrario.
     */
    @Override
    public boolean existsInDataBase() {
        if (entity.getId() == null) {
            return false;
        }

        return entityManager.find(entity.getClass(), entity.getId()) != null;
    }
}
