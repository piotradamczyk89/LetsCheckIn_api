package pl.coderslab.LetsCheckIn_api.Nordlinger.Transactions;


import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "booked",
        "pending"
})
@Generated("jsonschema2pojo")
@ToString
public class Transactions__1 {

    @JsonProperty("booked")
    private List<Booked> booked = null;
    @JsonProperty("pending")
    private List<Pending> pending = null;

    @JsonProperty("booked")
    public List<Booked> getBooked() {
        return booked;
    }

    @JsonProperty("booked")
    public void setBooked(List<Booked> booked) {
        this.booked = booked;
    }

    @JsonProperty("pending")
    public List<Pending> getPending() {
        return pending;
    }

    @JsonProperty("pending")
    public void setPending(List<Pending> pending) {
        this.pending = pending;
    }

}