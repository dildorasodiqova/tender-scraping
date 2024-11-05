package uz.simplex.tenderscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.simplex.tenderscraping.entity.Tender;
import uz.simplex.tenderscraping.repository.TenderRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebScraper {

    @Autowired
    private TenderRepository tenderRepository;

    public void scrapeAndSaveTender(String url) throws IOException {
        Map<String, String> data = scrapeData(url);

        Tender tender = new Tender();
        Tender tender1 = tender.create(data);
        tenderRepository.save(tender1);
        }

    public Map<String, String> scrapeData(String url) throws IOException {
        Map<String, String> data = new HashMap<>();
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .get();

        Map<String, String> labels = new HashMap<>();
        labels.put("buyerId", "Buyurtmachi STIR:");
        labels.put("buyerName", "Buyurtmachi nomi:");
        labels.put("formalizationMethod", "Rasmiylashtirish tartibi:");
        labels.put("evaluationMethod", "Takliflarni baholash mezoni:");
//        labels.put("reviewMethod", "Ishtirokchilarning takliflarini ko'rib chiqish tartibi:");
        labels.put("collateral", "Zaklat:");
        labels.put("guaranteeLetter", "Kafolat xati:");
        labels.put("collateralPercentage", "Zaklat miqdori:");
//        labels.put("advancePaymentPercentage", "Avans tolovi miqdori:");
        labels.put("collateralUnlocking", "Zaklat summasini yechish:");
//        labels.put("paymentTerms", "To'lov tartibi:");
        labels.put("locationDuration", "Joylashtirish muddati:");
        labels.put("openingDate", "Ochilish sanasi:");
//        labels.put("paymentPeriod", "To'lov muddati (to'liq to'lov):");
        labels.put("buyerAddress", "Buyurtmachi manzili:");
        labels.put("deliveryAddress", "Yukni yetkazib berish manzili:");
        labels.put("languages", "Belgilanadigan tillar:");
        labels.put("status", "Holat:");
        labels.put("financingSource", "Moliya manbasi:");
//        labels.put("additionalInfo", "Qo'shimcha ma'lumotlar:");
        labels.put("technicalDescription", "Texnik tavsif:");
//        labels.put("minBidPrice", "Min. o'tish ball:");
//        labels.put("bidReviewDays", "Ko'rib chiqish kuni:");
        labels.put("contactPhone", "Telefon:");


        for (Map.Entry<String, String> label : labels.entrySet()) {
            Element element = document.selectFirst(":containsOwn(" + label.getValue() + ")");
            if (element != null) {
                Element valueElement = element.nextElementSibling();
                if (valueElement != null) {
                    data.put(label.getKey(), valueElement.text().trim());
                } else {
                    data.put(label.getKey(), "Not found");
                }
            } else {
                data.put(label.getKey(), "Label not found");
            }
        }

        return data;
    }
}
