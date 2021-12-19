package pl.coderslab.LetsCheckIn_api.Nordlinger;



import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "bic",
        "transaction_total_days",
        "countries",
        "logo"
})
@Generated("jsonschema2pojo")
@ToString
public class Bank {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("bic")
    private String bic;
    @JsonProperty("transaction_total_days")
    private String transactionTotalDays;
    @JsonProperty("countries")
    private List<String> countries = null;
    @JsonProperty("logo")
    private String logo;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("bic")
    public String getBic() {
        return bic;
    }

    @JsonProperty("bic")
    public void setBic(String bic) {
        this.bic = bic;
    }

    @JsonProperty("transaction_total_days")
    public String getTransactionTotalDays() {
        return transactionTotalDays;
    }

    @JsonProperty("transaction_total_days")
    public void setTransactionTotalDays(String transactionTotalDays) {
        this.transactionTotalDays = transactionTotalDays;
    }

    @JsonProperty("countries")
    public List<String> getCountries() {
        return countries;
    }

    @JsonProperty("countries")
    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    @JsonProperty("logo")
    public String getLogo() {
        return logo;
    }

    @JsonProperty("logo")
    public void setLogo(String logo) {
        this.logo = logo;
    }

}
