package repository;

import generator.CrudRepository;
import model.Funcionario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repositório para a entidade Funcionario. Simula um banco de dados em memória.
 */
public class FuncionarioRepository implements CrudRepository<Funcionario> {

    // Simula nossa tabela do banco de dados para Funcionarios
    private final Map<Integer, Funcionario> database = new ConcurrentHashMap<>();
    // Gera IDs únicos para novos funcionários
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * Construtor que já inicializa o repositório com dados de exemplo.
     */
    public FuncionarioRepository() {
        // Pega a data atual para o campo dataCadastro
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = sdf.format(new Date());

        // Criando um funcionário de exemplo completo
        Funcionario func1 = new Funcionario(
                0, // id (0 para ser gerado automaticamente)
                "Carlos Andrade", // nome
                "(48) 99911-2233", // fone1
                "(48) 3624-0011", // fone2
                "carlos.andrade@hotel.com", // email
                "88790-000", // cep
                "Rua Principal, 150", // logradouro
                "Centro", // bairro
                "Jaguaruna", // cidade (usando sua localização atual ;) )
                "Apto 2", // complemento
                dataAtual, // dataCadastro
                "123.456.789-10", // cpf
                "1.234.567", // rg
                "carlos.a", // usuario
                "senhaForte123", // senha
                "Funcionário do turno da manhã.", // obs
                'A' // status
        );

        // Salva o funcionário de exemplo no nosso "banco"
        save(func1);
    }

    @Override
    public void save(Funcionario entity) {
        // Se o ID é 0, significa que é um novo registro
        if (entity.getId() == 0) {
            // Gera um novo ID e define na entidade
            entity.setId(idGenerator.getAndIncrement());
        }
        // Adiciona ou atualiza o funcionário no nosso "banco"
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Funcionario entity) {
        // Apenas atualiza se o funcionário já existir no banco
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        // Remove o funcionário do banco de dados pelo ID
        database.remove((Integer) id);
    }

    @Override
    public Funcionario findById(Object id) {
        // Busca e retorna um único funcionário pelo seu ID
        return database.get((Integer) id);
    }

    @Override
    public List<Funcionario> findAll() {
        // Retorna uma lista com todos os funcionários cadastrados
        return new ArrayList<>(database.values());
    }
}