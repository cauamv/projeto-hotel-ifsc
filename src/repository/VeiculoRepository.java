package repository;

import generator.CrudRepository;
import model.Veiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class VeiculoRepository implements CrudRepository<Veiculo> {

    private final Map<Integer, Veiculo> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public VeiculoRepository() {
        Veiculo v1 = new Veiculo();
        v1.setPlaca("QHE1J23");
        v1.setCor("Prata");
        v1.setStatus('A'); 
        save(v1);

        Veiculo v2 = new Veiculo();
        v2.setPlaca("BRA2E19"); 
        v2.setCor("Preto");
        v2.setStatus('A');
        save(v2);

        Veiculo v3 = new Veiculo();
        v3.setPlaca("JKL4567");
        v3.setCor("Branco");
        v3.setStatus('I'); 
        save(v3);
    }

    @Override
    public void save(Veiculo entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Veiculo entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }

    @Override
    public Veiculo findById(Object id) {
        return database.get((Integer) id);
    }

    @Override
    public List<Veiculo> findAll() {
        return new ArrayList<>(database.values());
    }
}