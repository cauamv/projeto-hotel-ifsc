package repository;

import generator.CrudRepository;
import model.ProdutoCopa;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repositório para a entidade ProdutoCopa. Simula um banco de dados em memória.
 */
public class ProdutoCopaRepository implements CrudRepository<ProdutoCopa> {

    // Simula nossa tabela do banco de dados para os produtos da copa
    private final Map<Integer, ProdutoCopa> database = new ConcurrentHashMap<>();
    // Gera IDs únicos para novos produtos
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * Construtor que já inicializa o repositório com dados de exemplo.
     */
    public ProdutoCopaRepository() {
        // --- Produto 1 ---
        ProdutoCopa p1 = new ProdutoCopa();
        p1.setDescricao("Coca-Cola Lata 350ml");
        p1.setValor(5.50f); // 'f' no final para indicar que é um float
        p1.setObs("Servir gelado");
        p1.setStatus('A');
        save(p1);

        // --- Produto 2 ---
        ProdutoCopa p2 = new ProdutoCopa();
        p2.setDescricao("Água Mineral sem Gás 500ml");
        p2.setValor(4.00f);
        p2.setObs("");
        p2.setStatus('A');
        save(p2);

        // --- Produto 3 ---
        ProdutoCopa p3 = new ProdutoCopa();
        p3.setDescricao("Salgado Misto (Queijo e Presunto)");
        p3.setValor(8.00f);
        p3.setObs("Aquecer no micro-ondas por 30 segundos");
        p3.setStatus('A');
        save(p3);
        
        // --- Produto 4 (Exemplo Inativo) ---
        ProdutoCopa p4 = new ProdutoCopa();
        p4.setDescricao("Suco de Laranja 1L (Fora de estoque)");
        p4.setValor(12.00f);
        p4.setObs("Produto descontinuado");
        p4.setStatus('C');
        save(p4);
    }

    @Override
    public void save(ProdutoCopa entity) {
        // Se o ID é 0, significa que é um novo registro
        if (entity.getId() == 0) {
            // Gera um novo ID e define na entidade
            entity.setId(idGenerator.getAndIncrement());
        }
        // Adiciona ou atualiza o produto no nosso "banco"
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(ProdutoCopa entity) {
        // Apenas atualiza se o produto já existir no banco
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        // Remove o produto do banco de dados pelo ID
        database.remove((Integer) id);
    }

    @Override
    public ProdutoCopa findById(Object id) {
        // Busca e retorna um único produto pelo seu ID
        return database.get((Integer) id);
    }

    @Override
    public List<ProdutoCopa> findAll() {
        // Retorna uma lista com todos os produtos cadastrados
        return new ArrayList<>(database.values());
    }
}