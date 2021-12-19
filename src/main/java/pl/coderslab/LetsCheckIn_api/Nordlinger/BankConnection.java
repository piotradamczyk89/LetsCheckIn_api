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
        "redirect",
        "agreements",
        "accounts",
        "reference",
        "user_language",
        "link"
})
@Generated("jsonschema2pojo")
@ToString
public class BankConnection {

    @JsonProperty("id")
    private String id;
    @JsonProperty("redirect")
    private String redirect;
    @JsonProperty("agreements")
    private String agreements;
    @JsonProperty("accounts")
    private List<Object> accounts = null;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("user_language")
    private String userLanguage;
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

    @JsonProperty("redirect")
    public String getRedirect() {
        return redirect;
    }

    @JsonProperty("redirect")
    public void setRedirect(String redirect) {
        this.redirect = redirect;
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
    public List<Object> getAccounts() {
        return accounts;
    }

    @JsonProperty("accounts")
    public void setAccounts(List<Object> accounts) {
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

    @JsonProperty("user_language")
    public String getUserLanguage() {
        return userLanguage;
    }

    @JsonProperty("user_language")
    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
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