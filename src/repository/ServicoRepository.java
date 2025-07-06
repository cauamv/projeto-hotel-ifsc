package repository;

import generator.CrudRepository;
import model.Servico;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServicoRepository implements CrudRepository<Servico> {

    private final Map<Integer, Servico> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public ServicoRepository() {
        Servico s1 = new Servico();
        s1.setDescricao("Lavanderia (por peça)");
        s1.setValor(15.00f);
        s1.setStatus('A');
        save(s1);

        Servico s2 = new Servico();
        s2.setDescricao("Estacionamento (diária)");
        s2.setValor(35.50f);
        s2.setStatus('I');
        save(s2);

        Servico s3 = new Servico();
        s3.setDescricao("Café da Manhã (extra por pessoa)");
        s3.setValor(45.00f);
        s3.setStatus('A');
        save(s3);
        
    }

    @Override
    public void save(Servico entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Servico entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }

    @Override
    public Servico findById(Object id) {
        return database.get((Integer) id);
    }

    @Override
    public List<Servico> findAll() {
        return new ArrayList<>(database.values());
    }
}