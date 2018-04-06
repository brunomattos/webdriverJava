package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class) //notação runwith apontando pra classe de teste do easytest
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv") //arquivos utilizados como dados de teste

public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        navegador = Web.createChrome();

        //clicar no link que possui o texto "Sign in"


        //clicar no campo com name login que está dentro do formulario de id signinbox



        //clicar no campo com name password que está dentro do formulario de id signinbox


        //clicar em sign in button


        //clicar em um link que possui a class me


        //clicar em um link que possui o texto "more data about you"

    }
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name = "tipo")String tipo, @Param(name = "contato")String contato, @Param(name = "mensagem")String mensagemEsperada) {
        //clicar no botão atraves do xpath
        navegador.findElement(By.xpath("//*[@id=\"moredata\"]/div[2]/button")).click();

        //identificar a pop up onde esta o formulario de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name type escolher a opção "Phone"
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //No campo de name contact digitar "+5553999999999"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE" que está no popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id toast-container validar que o texto é "Your contact has been added!"
        WebElement toast = navegador.findElement(By.id("toast-container"));
        String toastMessage = toast.getText();
        assertEquals(mensagemEsperada, toastMessage);
    }

    @Test
    public void removerUmContatoDeUmUsuario() {
        //clicar no elemento pelo xpath //span[text()='test3']/following-sibling::a
        navegador.findElement(By.xpath("//span[text()='+555391746269']/following-sibling::a")).click();

        //confirmar a janela JavaScript
        navegador.switchTo().alert().accept();

        //Validar mensagem "Rest in peace, dear phone!"
        WebElement toast2 = navegador.findElement(By.id("toast-container"));
        String toastMessage2 = toast2.getText();
        assertEquals("Rest in peace, dear phone!", toastMessage2);

        String screenshotArquivo = "/C:/workspace/webdriverJava/screenshots/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        //Aguardar até 10 segundos para que a janela apareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(toast2));

        //Clicar no link com texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

    @Test
    public void testEditarNome(){
        //Clicar na aba de texto ABOUT YOU
        navegador.findElement(By.linkText("ABOUT YOU")).click();
        //Editar informção no campo de name "name"
        navegador.findElement(By.name("name")).clear();
        navegador.findElement(By.name("name")).sendKeys("Jean");
        //Clicar no botão de id changeAboutYou
        navegador.findElement(By.id("changeAboutYou")).click();

        //Validar mensagem de sucesso
        WebElement toast3 = navegador.findElement(By.id("toast-container"));
        String toastMessage3 = toast3.getText();
        String sucessMessage = "Now you will be called " + "Jean" + "!";
        assertEquals(sucessMessage,toastMessage3);

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(toast3));
    }

    @After
    public void tearDown(){
        // fechar o navegador
        navegador.quit();
    }
}
