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
        "status",
        "agreements",
        "accounts",
        "reference",
        "link"
})
@Generated("jsonschema2pojo")
@ToString
public class Account {

    @JsonProperty("id")
    private String id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("agreements")
    private String agreements;
    @JsonProperty("accounts")
    private List<String> accounts;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("link")
    private String link;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("agreements")
    public String getAgreements() {
        return agreements;
    }

    @JsonProperty("agreements")
    public void setAgreements(String agreements) {
        this.agreements = agreements;
    }

    @JsonProperty("accounts")
    public List<String> getAccounts() {
        return accounts;
    }

    @JsonProperty("accounts")
    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    @JsonProperty("reference")
    public String getReference() {
        return reference;
    }

    @JsonProperty("reference")
    public void setReference(String reference) {
        this.reference = reference;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

}
