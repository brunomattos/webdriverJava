package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome(){
        //abrindo o navegador chrome
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize(); //maximizar janela do navegador
        navegador.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); //definindo um tempo de 5 segundos
        //navegando até a página
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
}
