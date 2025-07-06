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

public class FornecedorRepository implements CrudRepository<Fornecedor> {

    private final Map<Integer, Fornecedor> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public FornecedorRepository() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = sdf.format(new Date());

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

        save(f1);
    }

    @Override
    public void save(Fornecedor entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }

    @Override
    public void update(Fornecedor entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }

    @Override
    public Fornecedor findById(Object id) {
        return database.get((Integer) id);
    }

    @Override
    public List<Fornecedor> findAll() {
        return new ArrayList<>(database.values());
    }
}