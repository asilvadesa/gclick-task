package dominio;

import dao.ClienteDAO;
import execeptions.UsuarioNaoEncontradoExecption;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
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
        dao = ClienteTest.dao.cadastrar(cliente);
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

    @Test
    public void t5_naoDeve_CadastrarClienteSemAtributoInscricao(){
        Cliente c1 = new Cliente("Anderson", null, Arrays.asList("asilvadesa@gmail.com", "asilvagit@gmail.com"));
        assertNull(dao.cadastrar(c1));
    }

    @Test
    public void t6_naoDeve_RecuperarClienteComIdInvalido(){
        Integer idNaoExistente = 100;
        Cliente c1 = dao.buscaPorId(idNaoExistente);
        assertNull(c1);
    }

    @Test(expected = UsuarioNaoEncontradoExecption.class)
    public void t7_naoDeveAtualizarClienteComIdInvalido(){
        Integer idNaoExistente = 100;
        Cliente c1 = new Cliente("Anderson", true, Arrays.asList("asilvadesa@gmail.com", "asilvagit@gmail.com"));
        Cliente atualizado = dao.atualizar(idNaoExistente, c1);
    }

    @Test
    public void t8_naoDeveRemoverClienteComIdInvalido(){
        boolean resposta = dao.removerPorId(1000);
        assertFalse(resposta);
    }

}
