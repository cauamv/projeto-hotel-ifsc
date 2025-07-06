package repository;

import generator.CrudRepository;
import model.Veiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repositório para a entidade Veiculo. Simula um banco de dados em memória.
 */
public class VeiculoRepository implements CrudRepository<Veiculo> {

    // Simula nossa tabela do banco de dados para os veículos
    private final Map<Integer, Veiculo> database = new ConcurrentHashMap<>();
    // Gera IDs únicos para novos veículos
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * Construtor que já inicializa o repositório com dados de exemplo.
     */
    public VeiculoRepository() {
        // --- Veículo 1 ---
        Veiculo v1 = new Veiculo();
        v1.setPlaca("QHE1J23");
        v1.setCor("Prata");
        v1.setStatus('A'); // 'A' de Ativo/Disponível
        save(v1);

        // --- Veículo 2 ---
        Veiculo v2 = new Veiculo();
        v2.setPlaca("BRA2E19"); // Placa Mercosul
        v2.setCor("Preto");
        v2.setStatus('A');
        save(v2);

        // --- Veículo 3 ---
        Veiculo v3 = new Veiculo();
        v3.setPlaca("JKL4567");
        v3.setCor("Branco");
        v3.setStatus('C'); // 'C' de Inativo/Manutenção
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