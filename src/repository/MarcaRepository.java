package repository;

import generator.CrudRepository;
import model.Marca;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MarcaRepository implements CrudRepository<Marca> {

    private final Map<Integer, Marca> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public MarcaRepository() {
      
        Marca marca1 = new Marca(0, "Volkswagen", 'A');
        Marca marca2 = new Marca(0, "Chevrolet", 'A');
        Marca marca3 = new Marca(0, "Fiat", 'A');
        Marca marca4 = new Marca(0, "Ford", 'C'); 

        save(marca1);
        save(marca2);
        save(marca3);
        save(marca4);
    }

    @Override
    public void save(Marca entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Marca entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }

    @Override
    public Marca findById(Object id) {
        return database.get((Integer) id);
    }

    @Override
    public List<Marca> findAll() {
        return new ArrayList<>(database.values());
    }
}