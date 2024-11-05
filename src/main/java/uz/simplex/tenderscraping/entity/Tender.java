package uz.simplex.tenderscraping.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tenders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long buyerId;
    String buyerName;
    String shakl;
    String evaluationMethod;
    String reviewMethod;
    String depozit;
    String guaranteeLetter;   // enummi
    Double collateralPercentage;  //bu ham foizda
    Double advancePaymentPercentage;  // bu 30 % degan qaysi tip
    String unlocking;
    String paymentTerms;   // bu enummi
    String postingPeriod;  // bu qaysi tipda
    String openingDate;
    String paymentPeriod;
    String buyerAddress;
    String deliveryAddress;
    String languages;  // bu enummi
    String status;
    String financingSource;
    String additionalInfo;
    String technicalDescription;
    String minPassingScore;
    Long  views;
    String contact;



    public Tender create(Map<String, String> data){
        Tender tender = new Tender();

        tender.setBuyerId(Long.valueOf(data.get("buyerId")));
        tender.setBuyerName(data.get("buyerName"));
        tender.setShakl(data.get("formalizationMethod"));
        tender.setEvaluationMethod(data.get("evaluationMethod"));
        tender.setReviewMethod(data.get("reviewMethod"));
        tender.setDepozit(data.get("collateral"));
        tender.setGuaranteeLetter(data.get("guaranteeLetter"));
        tender.setCollateralPercentage(Double.valueOf(data.get("collateralPercentage")));
        tender.setAdvancePaymentPercentage(Double.valueOf(data.get("advancePaymentPercentage")));
        tender.setUnlocking(data.get("collateralUnlocking"));
        tender.setPaymentTerms(data.get("paymentTerms"));
        tender.setPostingPeriod(data.get("locationDuration"));
        tender.setOpeningDate(data.get("openingDate"));
        tender.setPaymentPeriod(data.get("paymentPeriod"));
        tender.setBuyerAddress(data.get("buyerAddress"));
        tender.setDeliveryAddress(data.get("deliveryAddress"));
        tender.setLanguages(data.get("languages"));
        tender.setStatus(data.get("status"));
        tender.setFinancingSource(data.get("financingSource"));
        tender.setAdditionalInfo(data.get("additionalInfo"));
        tender.setTechnicalDescription(data.get("technicalDescription"));
        tender.setMinPassingScore(data.get("minBidPrice"));
        tender.setViews(Long.valueOf(data.get("bidReviewDays")));
        tender.setContact(data.get("contactPhone"));

        return tender;
    }
}

