package repository;
import generator.CrudRepository;
import model.Hospede;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.SimpleDateFormat;

public class HospedeRepository implements CrudRepository<Hospede> {
    private Map<Integer, Hospede> database = new ConcurrentHashMap<>();
    private AtomicInteger idGenerator = new AtomicInteger(1);
    
    public HospedeRepository() {
        // Dados de exemplo
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = sdf.format(new Date());
        
        Hospede hospede1 = new Hospede(0, "João Silva Empresa LTDA", "(11) 99999-9999", "(11) 3333-3333", 
            "joao@empresa.com", "01310-100", "Av. Paulista, 1000", "Bela Vista", "São Paulo", 
            "Sala 101", dataAtual, "123.456.789-00", "12.345.678-9", "João Silva Empresa LTDA", 
            "12.345.678/0001-90", "123.456.789.123", "João Silva", "", 'A');
        
        Hospede hospede2 = new Hospede(0, "Maria Santos Hotel ME", "(21) 88888-8888", "(21) 2222-2222", 
            "maria@hotel.com", "22071-900", "Av. Copacabana, 500", "Copacabana", "Rio de Janeiro", 
            "Loja A", dataAtual, "987.654.321-00", "98.765.432-1", "Maria Santos Hotel ME", 
            "98.765.432/0001-10", "987.654.321.987", "Maria Santos", "", 'A');
        
        save(hospede1);
        save(hospede2);
    }
    
    @Override
    public void save(Hospede entity) {
        if (entity.getId() == 0) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
    }
    
    @Override
    public void update(Hospede entity) {
        if (entity.getId() != 0 && database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }
    
    @Override
    public void delete(Object id) {
        database.remove((Integer) id);
    }
    
    @Override
    public Hospede findById(Object id) {
        return database.get((Integer) id);
    }
    
    @Override
    public List<Hospede> findAll() {
        return new ArrayList<>(database.values());
    }
}
