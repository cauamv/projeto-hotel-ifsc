package repository;

import generator.CrudRepository;
import model.Marca;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repositório para a entidade Marca. Simula um banco de dados em memória.
 */
public class MarcaRepository implements CrudRepository<Marca> {

    // Simula nossa tabela do banco de dados para Marcas
    private final Map<Integer, Marca> database = new ConcurrentHashMap<>();
    // Gera IDs únicos para novas marcas
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * Construtor que já inicializa o repositório com alguns dados de exemplo.
     */
    public MarcaRepository() {
      
        Marca marca1 = new Marca(0, "Volkswagen", 'A');
        Marca marca2 = new Marca(0, "Chevrolet", 'A');
        Marca marca3 = new Marca(0, "Fiat", 'A');
        Marca marca4 = new Marca(0, "Ford", 'C'); // Exemplo de marca cancelada/inativa

        // Salva os dados de exemplo no "banco"
        save(marca1);
        save(marca2);
        save(marca3);
        save(marca4);
    }

    @Override
    public void save(Marca entity) {
        // Se o ID é 0, significa que é um novo registro
        if (entity.getId() == 0) {
            // Gera um novo ID e define na entidade
            entity.setId(idGenerator.getAndIncrement());
        }
        // Adiciona ou atualiza a marca no nosso "banco"
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Marca entity) {
        // Apenas atualiza se a marca já existir no banco
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        // Remove a marca do banco de dados pelo ID
        database.remove((Integer) id);
    }

    @Override
    public Marca findById(Object id) {
        // Busca e retorna uma única marca pelo seu ID
        return database.get((Integer) id);
    }

    @Override
    public List<Marca> findAll() {
        // Retorna uma lista com todas as marcas cadastradas
        return new ArrayList<>(database.values());
    }
}