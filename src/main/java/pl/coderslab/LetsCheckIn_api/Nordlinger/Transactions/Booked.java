package pl.coderslab.LetsCheckIn_api.Nordlinger.Transactions;


import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "transactionId",
        "debtorName",
        "debtorAccount",
        "transactionAmount",
        "bookingDate",
        "valueDate",
        "remittanceInformationUnstructured",
        "bankTransactionCode"
})
@Generated("jsonschema2pojo")
@ToString
public class Booked {

    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("debtorName")
    private String debtorName;
    @JsonProperty("debtorAccount")
    private DebtorAccount debtorAccount;
    @JsonProperty("transactionAmount")
    private TransactionAmount transactionAmount;
    @JsonProperty("bookingDate")
    private String bookingDate;
    @JsonProperty("valueDate")
    private String valueDate;
    @JsonProperty("remittanceInformationUnstructured")
    private String remittanceInformationUnstructured;
    @JsonProperty("bankTransactionCode")
    private String bankTransactionCode;

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("debtorName")
    public String getDebtorName() {
        return debtorName;
    }

    @JsonProperty("debtorName")
    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    @JsonProperty("debtorAccount")
    public DebtorAccount getDebtorAccount() {
        return debtorAccount;
    }

    @JsonProperty("debtorAccount")
    public void setDebtorAccount(DebtorAccount debtorAccount) {
        this.debtorAccount = debtorAccount;
    }

    @JsonProperty("transactionAmount")
    public TransactionAmount getTransactionAmount() {
        return transactionAmount;
    }

    @JsonProperty("transactionAmount")
    public void setTransactionAmount(TransactionAmount transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @JsonProperty("bookingDate")
    public String getBookingDate() {
        return bookingDate;
    }

    @JsonProperty("bookingDate")
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
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

    @JsonProperty("bankTransactionCode")
    public String getBankTransactionCode() {
        return bankTransactionCode;
    }

    @JsonProperty("bankTransactionCode")
    public void setBankTransactionCode(String bankTransactionCode) {
        this.bankTransactionCode = bankTransactionCode;
    }
}
