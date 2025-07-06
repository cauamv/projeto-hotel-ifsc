package repository;

import generator.CrudRepository;
import model.VagaEstacionamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repositório para a entidade VagaEstacionamento. Simula um banco de dados em memória.
 */
public class VagaRepository implements CrudRepository<VagaEstacionamento> {

    // Simula nossa tabela do banco de dados para as vagas
    private final Map<Integer, VagaEstacionamento> database = new ConcurrentHashMap<>();
    // Gera IDs únicos para novas vagas
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * Construtor que já inicializa o repositório com dados de exemplo.
     */
    public  VagaRepository() {
        // --- Vaga 1 ---
        VagaEstacionamento v1 = new VagaEstacionamento();
        v1.setDescricao("Vaga 01 - Coberta");
        v1.setMetragemvaga(12.5f); // 5m x 2.5m
        v1.setStatus('A'); // 'A' de Ativa/Disponível
        save(v1);

        // --- Vaga 2 ---
        VagaEstacionamento v2 = new VagaEstacionamento();
        v2.setDescricao("Vaga 02 - Coberta");
        v2.setMetragemvaga(12.5f);
        v2.setStatus('A');
        save(v2);

        // --- Vaga 3 ---
        VagaEstacionamento v3 = new VagaEstacionamento();
        v3.setDescricao("Vaga Moto 01");
        v3.setMetragemvaga(3.0f); // 2m x 1.5m
        v3.setStatus('A');
        save(v3);

        // --- Vaga 4 (Exemplo Inativa/Manutenção) ---
        VagaEstacionamento v4 = new VagaEstacionamento();
        v4.setDescricao("Vaga 03 - Em manutenção");
        v4.setMetragemvaga(12.5f);
        v4.setStatus('C'); // 'C' de Cancelada/Inativa
        save(v4);
    }

    @Override
    public void save(VagaEstacionamento entity) {
        // Se o ID é 0, significa que é um novo registro
        if (entity.getId() == 0) {
            // Gera um novo ID e define na entidade
            entity.setId(idGenerator.getAndIncrement());
        }
        // Adiciona ou atualiza a vaga no nosso "banco"
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(VagaEstacionamento entity) {
        // Apenas atualiza se a vaga já existir no banco
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        // Remove a vaga do banco de dados pelo ID
        database.remove((Integer) id);
    }

    @Override
    public VagaEstacionamento findById(Object id) {
        // Busca e retorna uma única vaga pelo seu ID
        return database.get((Integer) id);
    }

    @Override
    public List<VagaEstacionamento> findAll() {
        // Retorna uma lista com todas as vagas cadastradas
        return new ArrayList<>(database.values());
    }
}