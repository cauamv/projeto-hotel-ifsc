package repository;

import generator.CrudRepository;
import model.VagaEstacionamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class VagaEstacionamentoRepository implements CrudRepository<VagaEstacionamento> {

    private final Map<Integer, VagaEstacionamento> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public  VagaEstacionamentoRepository() {
        VagaEstacionamento v1 = new VagaEstacionamento();
        v1.setDescricao("Vaga 01 - Coberta");
        v1.setMetragemvaga(12.5f); 
        v1.setStatus('A'); 
        save(v1);

        VagaEstacionamento v2 = new VagaEstacionamento();
        v2.setDescricao("Vaga 02 - Coberta");
        v2.setMetragemvaga(12.5f);
        v2.setStatus('A');
        save(v2);

        VagaEstacionamento v3 = new VagaEstacionamento();
        v3.setDescricao("Vaga Moto 01");
        v3.setMetragemvaga(3.0f); 
        v3.setStatus('A');
        save(v3);

        VagaEstacionamento v4 = new VagaEstacionamento();
        v4.setDescricao("Vaga 03 - Em manutenção");
        v4.setMetragemvaga(12.5f);
        v4.setStatus('I'); 
        save(v4);
    }

    @Override
    public void save(VagaEstacionamento entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(VagaEstacionamento entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }

    @Override
    public VagaEstacionamento findById(Object id) {
        return database.get((Integer) id);
    }

    @Override
    public List<VagaEstacionamento> findAll() {
        return new ArrayList<>(database.values());
    }
}