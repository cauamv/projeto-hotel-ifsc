package repository;

import generator.CrudRepository;
import model.ProdutoCopa;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdutoCopaRepository implements CrudRepository<ProdutoCopa> {

    private final Map<Integer, ProdutoCopa> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public ProdutoCopaRepository() {
        ProdutoCopa p1 = new ProdutoCopa();
        p1.setDescricao("Coca-Cola Lata 350ml");
        p1.setValor(5.50f); // 'f' no final para indicar que é um float
        p1.setObs("Servir gelado");
        p1.setStatus('A');
        save(p1);

        ProdutoCopa p2 = new ProdutoCopa();
        p2.setDescricao("Água Mineral sem Gás 500ml");
        p2.setValor(4.00f);
        p2.setObs("");
        p2.setStatus('A');
        save(p2);

        ProdutoCopa p3 = new ProdutoCopa();
        p3.setDescricao("Salgado Misto (Queijo e Presunto)");
        p3.setValor(8.00f);
        p3.setObs("Aquecer no micro-ondas por 30 segundos");
        p3.setStatus('A');
        save(p3);
        
        ProdutoCopa p4 = new ProdutoCopa();
        p4.setDescricao("Suco de Laranja 1L (Fora de estoque)");
        p4.setValor(12.00f);
        p4.setObs("Produto descontinuado");
        p4.setStatus('C');
        save(p4);
    }

    @Override
    public void save(ProdutoCopa entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(ProdutoCopa entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }

    @Override
    public ProdutoCopa findById(Object id) {
        return database.get((Integer) id);
    }

    @Override
    public List<ProdutoCopa> findAll() {
        return new ArrayList<>(database.values());
    }
}