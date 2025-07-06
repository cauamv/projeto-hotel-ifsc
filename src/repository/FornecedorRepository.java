package repository;

import generator.CrudRepository;
import model.Fornecedor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repositório para a entidade Fornecedor. Simula um banco de dados em memória.
 */
public class FornecedorRepository implements CrudRepository<Fornecedor> {

    // Simula nossa tabela do banco de dados para Fornecedores
    private final Map<Integer, Fornecedor> database = new ConcurrentHashMap<>();
    // Gera IDs únicos para novos fornecedores
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * Construtor que já inicializa o repositório com dados de exemplo.
     */
    public FornecedorRepository() {
        // Pega a data atual para o campo dataCadastro
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = sdf.format(new Date());

        // Criando um fornecedor de exemplo completo
        Fornecedor f1 = new Fornecedor(
                0, // id (0 para ser gerado automaticamente)
                "LimpaTudo Produtos de Limpeza", // nome (fantasia)
                "(48) 3344-5566", // fone1
                "", // fone2
                "contato@limpatudo.com", // email
                "88010-000", // cep
                "Rua das Indústrias, 789", // logradouro
                "Distrito Industrial", // bairro
                "São José", // cidade
                "Galpão 2", // complemento
                dataAtual, // dataCadastro
                "", // cpf (pessoa jurídica não tem)
                "", // rg (pessoa jurídica não tem)
                "LimpaTudo Comércio de Produtos de Higiene LTDA", // razaoSocial
                "12.345.678/0001-99", // cnpj
                "123.456.789.111", // inscricaoEstadual
                "Sr. Mário", // contato
                "Fornecedor de material de limpeza geral.", // obs
                'A' // status
        );

        // Salva o fornecedor de exemplo no nosso "banco"
        save(f1);
    }

    @Override
    public void save(Fornecedor entity) {
        // Se o ID é 0, significa que é um novo registro
        if (entity.getId() == 0) {
            // Gera um novo ID e define na entidade
            entity.setId(idGenerator.getAndIncrement());
        }
        // Adiciona ou atualiza o fornecedor no nosso "banco"
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Fornecedor entity) {
        // Apenas atualiza se o fornecedor já existir no banco
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        // Remove o fornecedor do banco de dados pelo ID
        database.remove((Integer) id);
    }

    @Override
    public Fornecedor findById(Object id) {
        // Busca e retorna um único fornecedor pelo seu ID
        return database.get((Integer) id);
    }

    @Override
    public List<Fornecedor> findAll() {
        // Retorna uma lista com todos os fornecedores cadastrados
        return new ArrayList<>(database.values());
    }
}