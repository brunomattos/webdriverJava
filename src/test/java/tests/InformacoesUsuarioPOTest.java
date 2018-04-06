package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;
import pages.LoginFormPage;

import static org.junit.Assert.*;

public class InformacoesUsuarioPOTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        String toastMessage = new LoginPage(navegador)
                .clickSignIn()
                .fazerLogin("bruno", "123456")
                .clicarMe()
                .clickAbaMoreDataAboutYou()
                .clickBotaoAddMoreDataAboutYou()
                .adicionarContato("Phone", "+5553991716269")
                .capturarTextoToast();

        assertEquals("Your contact has been added!", toastMessage);
    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
