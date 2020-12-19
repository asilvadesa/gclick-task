package dominio;

import dao.ClienteDAO;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTest {

    private static Cliente cliente;
    private static ClienteDAO dao;

    @BeforeClass
    public static void setUp(){
        dao = new ClienteDAO();
        cliente =  new Cliente("Maria", true, Arrays.asList("maria@gmail.com", "9999-8888"));
    }


    @Test
    public void t1_deveCadastrarClienteComSucesso(){
        ClienteDAO dao = ClienteTest.dao.cadastrar(cliente);
        assertNotNull(dao);
    }

    @Test
    public void t2_deve_RecuperarClienteComIdValido(){
        Cliente clienteBancoDeDados = dao.buscaPorId(1);
        assertEquals(cliente, clienteBancoDeDados);
    }

    @Test
    public void t3_deve_AtualizarClienteComIdValido(){
        Integer id = 1;
        Cliente clienteAtualizado = new Cliente("Maria Sa", true, Arrays.asList("maria@gmail.com", "9999-8888"));

        Cliente atualizado = dao.atualizar(id, clienteAtualizado);
        System.out.println(atualizado);
    }

    @Test
    public void t4_deve_RemoverClienteComIDvalido(){
        Integer id = 1;
        boolean resposta = dao.removerPorId(id);
        assertTrue(resposta);
    }

}
