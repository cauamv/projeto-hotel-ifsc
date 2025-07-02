package generator;

import java.util.List;

public interface CrudRepository<T> {
    void save(T entity);
    void update(T entity);
    void delete(Object id);
    T findById(Object id);
    List<T> findAll();
}