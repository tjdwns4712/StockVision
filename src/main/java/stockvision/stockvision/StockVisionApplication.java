package stockvision.stockvision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StockVisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockVisionApplication.class, args);
    }

    // RestTemplate 빈 생성
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
