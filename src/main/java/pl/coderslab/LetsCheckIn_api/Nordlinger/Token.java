package pl.coderslab.LetsCheckIn_api.Nordlinger;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "access",
        "access_expires",
        "refresh",
        "refresh_expires"
})
@Generated("jsonschema2pojo")
@ToString
public class Token {

    @JsonProperty("access")
    private String access;
    @JsonProperty("access_expires")
    private Integer accessExpires;
    @JsonProperty("refresh")
    private String refresh;
    @JsonProperty("refresh_expires")
    private Integer refreshExpires;

    @JsonProperty("access")
    public String getAccess() {
        return access;
    }

    @JsonProperty("access")
    public void setAccess(String access) {
        this.access = access;
    }

    @JsonProperty("access_expires")
    public Integer getAccessExpires() {
        return accessExpires;
    }

    @JsonProperty("access_expires")
    public void setAccessExpires(Integer accessExpires) {
        this.accessExpires = accessExpires;
    }

    @JsonProperty("refresh")
    public String getRefresh() {
        return refresh;
    }

    @JsonProperty("refresh")
    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    @JsonProperty("refresh_expires")
    public Integer getRefreshExpires() {
        return refreshExpires;
    }

    @JsonProperty("refresh_expires")
    public void setRefreshExpires(Integer refreshExpires) {
        this.refreshExpires = refreshExpires;
    }

}
