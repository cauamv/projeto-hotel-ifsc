package repository;

import generator.CrudRepository;
import model.Servico;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repositório para a entidade Servico. Simula um banco de dados em memória.
 */
public class ServicoRepository implements CrudRepository<Servico> {

    // Simula nossa tabela do banco de dados para os serviços
    private final Map<Integer, Servico> database = new ConcurrentHashMap<>();
    // Gera IDs únicos para novos serviços
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * Construtor que já inicializa o repositório com dados de exemplo.
     */
    public ServicoRepository() {
        // --- Serviço 1 ---
        Servico s1 = new Servico();
        s1.setDescricao("Lavanderia (por peça)");
        s1.setValor(15.00f);
        s1.setStatus('A');
        save(s1);

        // --- Serviço 2 ---
        Servico s2 = new Servico();
        s2.setDescricao("Estacionamento (diária)");
        s2.setValor(35.50f);
        s2.setStatus('A');
        save(s2);

        // --- Serviço 3 ---
        Servico s3 = new Servico();
        s3.setDescricao("Café da Manhã (extra por pessoa)");
        s3.setValor(45.00f);
        s3.setStatus('A');
        save(s3);
        
    }

    @Override
    public void save(Servico entity) {
        // Se o ID é 0, significa que é um novo registro
        if (entity.getId() == 0) {
            // Gera um novo ID e define na entidade
            entity.setId(idGenerator.getAndIncrement());
        }
        // Adiciona ou atualiza o serviço no nosso "banco"
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Servico entity) {
        // Apenas atualiza se o serviço já existir no banco
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        // Remove o serviço do banco de dados pelo ID
        database.remove((Integer) id);
    }

    @Override
    public Servico findById(Object id) {
        // Busca e retorna um único serviço pelo seu ID
        return database.get((Integer) id);
    }

    @Override
    public List<Servico> findAll() {
        // Retorna uma lista com todos os serviços cadastrados
        return new ArrayList<>(database.values());
    }
}