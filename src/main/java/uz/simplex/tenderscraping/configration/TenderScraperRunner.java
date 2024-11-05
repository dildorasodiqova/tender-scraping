package uz.simplex.tenderscraping.configration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.simplex.tenderscraping.WebScraper;

@Component
public class TenderScraperRunner implements CommandLineRunner {

    private final WebScraper webScraper;

    public TenderScraperRunner(WebScraper webScraper) {
        this.webScraper = webScraper;
    }

    @Override
    public void run(String... args) throws Exception {
        String url = "https://etender.uzex.uz/lot/396717";
        webScraper.scrapeAndSaveTender(url);
    }
}
