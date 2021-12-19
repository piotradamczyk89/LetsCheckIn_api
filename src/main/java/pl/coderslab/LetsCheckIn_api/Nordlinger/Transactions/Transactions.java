package pl.coderslab.LetsCheckIn_api.Nordlinger.Transactions;


import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "transactions"
})
@Generated("jsonschema2pojo")
@ToString
public class Transactions {

    @JsonProperty("transactions")
    private Transactions__1 transactions;

    @JsonProperty("transactions")
    public Transactions__1 getTransactions() {
        return transactions;
    }

    @JsonProperty("transactions")
    public void setTransactions(Transactions__1 transactions) {
        this.transactions = transactions;
    }

}