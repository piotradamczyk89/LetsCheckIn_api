package pl.coderslab.LetsCheckIn_api.Nordlinger.Transactions;


import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "transactionAmount",
        "valueDate",
        "remittanceInformationUnstructured"
})
@Generated("jsonschema2pojo")
@ToString
public class Pending {

    @JsonProperty("transactionAmount")
    private TransactionAmountSecond transactionAmount;
    @JsonProperty("valueDate")
    private String valueDate;
    @JsonProperty("remittanceInformationUnstructured")
    private String remittanceInformationUnstructured;

    @JsonProperty("transactionAmount")
    public TransactionAmountSecond getTransactionAmount() {
        return transactionAmount;
    }

    @JsonProperty("transactionAmount")
    public void setTransactionAmount(TransactionAmountSecond transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @JsonProperty("valueDate")
    public String getValueDate() {
        return valueDate;
    }

    @JsonProperty("valueDate")
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    @JsonProperty("remittanceInformationUnstructured")
    public String getRemittanceInformationUnstructured() {
        return remittanceInformationUnstructured;
    }

    @JsonProperty("remittanceInformationUnstructured")
    public void setRemittanceInformationUnstructured(String remittanceInformationUnstructured) {
        this.remittanceInformationUnstructured = remittanceInformationUnstructured;
    }

}