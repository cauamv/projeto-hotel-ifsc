package repository;

import generator.CrudRepository;
import model.Quarto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repositório para a entidade Quarto (versão detalhada). Simula um banco de dados em memória.
 */
public class QuartoRepository implements CrudRepository<Quarto> {

    // Simula nossa tabela do banco de dados para os quartos
    private final Map<Integer, Quarto> database = new ConcurrentHashMap<>();
    // Gera IDs únicos para novos quartos
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * Construtor que já inicializa o repositório com dados de exemplo.
     */
    public QuartoRepository() {
        // --- Quarto 1 ---
        Quarto q1 = new Quarto();
        q1.setIdentificacao("101");
        q1.setDescricao("Quarto Casal Standard");
        q1.setCapacidadehospedes(2);
        q1.setMetragem(22.5f);
        q1.setAndar(1);
        q1.setFlagmanutencao(false); // false = disponível para uso
        q1.setStatus('A');
        save(q1);

        // --- Quarto 2 ---
        Quarto q2 = new Quarto();
        q2.setIdentificacao("102");
        q2.setDescricao("Quarto Solteiro com Acessibilidade");
        q2.setCapacidadehospedes(1);
        q2.setMetragem(25.0f);
        q2.setAndar(1);
        q2.setFlagmanutencao(false);
        q2.setStatus('A');
        save(q2);

        // --- Quarto 3 ---
        Quarto q3 = new Quarto();
        q3.setIdentificacao("205");
        q3.setDescricao("Suíte Master com Vista para o Mar");
        q3.setCapacidadehospedes(4);
        q3.setMetragem(45.8f);
        q3.setAndar(2);
        q3.setFlagmanutencao(false);
        q3.setStatus('A');
        save(q3);
        
        // --- Quarto 4 (Exemplo em Manutenção) ---
        Quarto q4 = new Quarto();
        q4.setIdentificacao("301");
        q4.setDescricao("Quarto Família");
        q4.setCapacidadehospedes(5);
        q4.setMetragem(40.0f);
        q4.setAndar(3);
        q4.setFlagmanutencao(true); // true = em manutenção/indisponível
        q4.setStatus('A');
        save(q4);
    }

    @Override
    public void save(Quarto entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Quarto entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }

    @Override
    public Quarto findById(Object id) {
        return database.get((Integer) id);
    }

    @Override
    public List<Quarto> findAll() {
        return new ArrayList<>(database.values());
    }
}