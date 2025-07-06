package repository;

import generator.CrudRepository;
import model.Marca;
import model.Modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ModeloRepository implements CrudRepository<Modelo> {

    private final Map<Integer, Modelo> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public ModeloRepository() {
        MarcaRepository marcaRepo = new MarcaRepository();
        
        Marca vw = marcaRepo.findById(1);
        Marca chevrolet = marcaRepo.findById(2);
        Marca fiat = marcaRepo.findById(3);

        if (vw != null) {
            save(new Modelo(0, "Gol", 'A', vw));
            save(new Modelo(0, "Polo", 'A', vw));
            save(new Modelo(0, "Nivus", 'A', vw));
        }
        
        if (chevrolet != null) {
            save(new Modelo(0, "Onix", 'A', chevrolet));
            save(new Modelo(0, "Tracker", 'A', chevrolet));
        }

        if (fiat != null) {
            save(new Modelo(0, "Argo", 'A', fiat));
            save(new Modelo(0, "Strada", 'I', fiat)); // Exemplo de modelo inativo
        }
    }

    @Override
    public void save(Modelo entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Modelo entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }



    @Override
    public Modelo findById(Object id) {
        return database.get((Integer) id);
    }

    @Override
    public List<Modelo> findAll() {
        return new ArrayList<>(database.values());
    }
}