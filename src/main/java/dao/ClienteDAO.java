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

    public ClienteDAO() {
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

    public boolean removerPorId(Integer id){
        try {
            Cliente clienteBancoDeDados = this.abrirT().buscaPorId(id);
            manager.remove(clienteBancoDeDados);
            this.fecharT();
            return true;
        } catch (Exception e){
            manager.getTransaction().rollback();
            return false;
        }
    }

    public Cliente atualizar(Integer id, Cliente cliente){
        try{
            Cliente clienteBancoDeDados = buscaPorId(id);
            clienteBancoDeDados.setNome(cliente.getNome());
            clienteBancoDeDados.setContatos(cliente.getContatos());
            clienteBancoDeDados.setInscricao(cliente.getInscricao());
            cadastrar(clienteBancoDeDados);
            return clienteBancoDeDados;
        }catch (Exception e){
            throw new RuntimeException("Cliente não identificado");
        }
    }
}
