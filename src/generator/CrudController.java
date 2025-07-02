package generator;

import java.util.List;

public class CrudController<T> {
    private CrudRepository<T> repository;
    private Class<T> entityClass;
    
    public CrudController(CrudRepository<T> repository, Class<T> entityClass) {
        this.repository = repository;
        this.entityClass = entityClass;
    }
    
    public void create(T entity) {
        repository.save(entity);
    }
    
    public void update(T entity) {
        repository.update(entity);
    }
    
    public void delete(Object id) {
        repository.delete(id);
    }
    
    public T findById(Object id) {
        return repository.findById(id);
    }
    
    public List<T> findAll() {
        return repository.findAll();
    }
    
    public Class<T> getEntityClass() {
        return entityClass;
    }
}