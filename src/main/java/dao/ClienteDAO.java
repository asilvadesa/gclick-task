package dao;

import dominio.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDAO {

    private static EntityManagerFactory factory;
    private EntityManager manager;
    private Cliente cliente;

    static {
        try{
            factory = Persistence.createEntityManagerFactory("gclick-jpa");
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public ClienteDAO(Cliente cliente) {
        this.cliente = cliente;
        manager = factory.createEntityManager();
    }

    private ClienteDAO abrirT(){
        manager.getTransaction().begin();
        return this;
    }

    private ClienteDAO fecharT(){
        manager.getTransaction().commit();
        return this;
    }

    private ClienteDAO incluir(Cliente cliente){
        manager.persist(cliente);
        return this;
    }

    public ClienteDAO cadastrar(Cliente cliente) {
        return this.abrirT().incluir(cliente).fecharT();
    }

    public Cliente buscaPorId(Integer id){
        return manager.find(Cliente.class, id);
    }

    public void remover(Cliente cliente){
        try {
            Cliente c1 = this.abrirT().buscaPorId(cliente.getId());
            manager.remove(c1);
            this.fecharT();
        } catch (Exception e){
            manager.getTransaction().rollback();
        }
    }

    public Cliente atualizar(Integer id, Cliente cliente){
        try{
            Cliente c1 = buscaPorId(id);
            abrirT();
            manager.persist(cliente);
            return c1;
        }catch (Exception e){
            throw new RuntimeException("Cliente não identificado");
        }
    }
}
