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

public class FuncionarioRepository implements CrudRepository<Funcionario> {

    private final Map<Integer, Funcionario> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public FuncionarioRepository() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = sdf.format(new Date());

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

        save(func1);
    }

    @Override
    public void save(Funcionario entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Funcionario entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }

    @Override
    public Funcionario findById(Object id) {
        return database.get((Integer) id);
    }

    @Override
    public List<Funcionario> findAll() {
        return new ArrayList<>(database.values());
    }
}