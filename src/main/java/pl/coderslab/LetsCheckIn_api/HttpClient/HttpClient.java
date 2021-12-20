package pl.coderslab.LetsCheckIn_api.HttpClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;
import pl.coderslab.LetsCheckIn_api.Nordlinger.*;
import pl.coderslab.LetsCheckIn_api.Nordlinger.Transactions.Booked;
import pl.coderslab.LetsCheckIn_api.Nordlinger.Transactions.Transactions;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Component
public class HttpClient {

    public Token requestToken(String secretKey, String secretId) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://ob.nordigen.com/api/v2/token/new/");
        httpPost.addHeader("accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity params = null;
        try {
            //     params = new StringEntity("{\"secret_id\":\"e69fdb20-886c-4c88-8e9f-532aa28234a1\",\"secret_key\":\"055f99dfa537f04159f057c65bb2b10e2e85117fd09a23d14b5cae565c1a33622fea35fb157f6af5c36092c3e6e1828c25244c38a46e1d272d115523a21cba1f\"}");
            params = new StringEntity("{\"secret_id\":\"" + secretId + "\",\"secret_key\":\"" + secretKey + "\"}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(params);
        ObjectMapper mapper = new ObjectMapper();
        Token token = null;
        try {
            token = client.execute(httpPost, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Token.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }
    public Token requestRefreshToken(String refreshToken) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://ob.nordigen.com/api/v2/token/refresh/");
        httpPost.addHeader("accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity params = null;
        try {
            params = new StringEntity("{\"refresh\":\"" + refreshToken + "\"}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(params);
        ObjectMapper mapper = new ObjectMapper();
        Token token = null;
        try {
            token = client.execute(httpPost, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Token.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    public List<Bank> requestBanks(String token) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://ob.nordigen.com/api/v2/institutions/?country=pl");
        httpGet.addHeader("accept", "application/json");
        //httpGet.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM5OTQ2OTM0LCJqdGkiOiIxOGI5YTFjNjM4M2U0YjQ2ODk1MjhjMjk5MTkwMDJlMCIsImlkIjo1ODE3LCJzZWNyZXRfaWQiOiJlNjlmZGIyMC04ODZjLTRjODgtOGU5Zi01MzJhYTI4MjM0YTEiLCJhbGxvd2VkX2NpZHJzIjpbIjAuMC4wLjAvMCJdfQ.ATvB8kbR2ei4dKd7QuRStEGWiT9zBWeq0elZkSpwX_k");
        httpGet.addHeader("Authorization", "Bearer " + token);
        ObjectMapper mapper = new ObjectMapper();
        List<Bank> banks = new ArrayList<>();
        try {
            banks = client.execute(httpGet, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<List<Bank>>() {
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(banks);
        return banks;
    }

    public BankConnection connectToBank(String institutionIdName, String token) {


        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://ob.nordigen.com/api/v2/requisitions/");
        httpPost.addHeader("accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", "Bearer " + token);
    //    httpPost.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM5OTQ2OTM0LCJqdGkiOiIxOGI5YTFjNjM4M2U0YjQ2ODk1MjhjMjk5MTkwMDJlMCIsImlkIjo1ODE3LCJzZWNyZXRfaWQiOiJlNjlmZGIyMC04ODZjLTRjODgtOGU5Zi01MzJhYTI4MjM0YTEiLCJhbGxvd2VkX2NpZHJzIjpbIjAuMC4wLjAvMCJdfQ.ATvB8kbR2ei4dKd7QuRStEGWiT9zBWeq0elZkSpwX_k");
        StringEntity params = null;
        try {
            params = new StringEntity("{\"redirect\":\"http://localhost:8080/user/account/accounts\",\"institution_id\":\""+institutionIdName+"\"}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(params);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        BankConnection bankConnection = null;
        try {
            bankConnection = client.execute(httpPost, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), BankConnection.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bankConnection);
        return bankConnection; // tu cos dziwnego sie dzieje ?
    }

    public Account requestAccounts(String token, String requisitionIdName) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://ob.nordigen.com/api/v2/requisitions/"+requisitionIdName+"/");
       // HttpGet httpGet = new HttpGet("https://ob.nordigen.com/api/v2/requisitions/a357f9d5-dfdc-4a0d-9b21-6c2381399d4f/");
        httpGet.addHeader("accept", "application/json");
        httpGet.addHeader("Authorization", "Bearer " + token);
       // httpGet.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM5OTQ2OTM0LCJqdGkiOiIxOGI5YTFjNjM4M2U0YjQ2ODk1MjhjMjk5MTkwMDJlMCIsImlkIjo1ODE3LCJzZWNyZXRfaWQiOiJlNjlmZGIyMC04ODZjLTRjODgtOGU5Zi01MzJhYTI4MjM0YTEiLCJhbGxvd2VkX2NpZHJzIjpbIjAuMC4wLjAvMCJdfQ.ATvB8kbR2ei4dKd7QuRStEGWiT9zBWeq0elZkSpwX_k");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Account account = null;
        try {
            account = client.execute(httpGet, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Account.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(account);
        return account;
    }

    public Transactions requestTransactions(String account, String token) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://ob.nordigen.com/api/v2/accounts/"+account+"/transactions/");
        httpGet.addHeader("accept", "application/json");
        httpGet.addHeader("Authorization", "Bearer " + token);
     //   httpGet.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjM5OTQ2OTM0LCJqdGkiOiIxOGI5YTFjNjM4M2U0YjQ2ODk1MjhjMjk5MTkwMDJlMCIsImlkIjo1ODE3LCJzZWNyZXRfaWQiOiJlNjlmZGIyMC04ODZjLTRjODgtOGU5Zi01MzJhYTI4MjM0YTEiLCJhbGxvd2VkX2NpZHJzIjpbIjAuMC4wLjAvMCJdfQ.ATvB8kbR2ei4dKd7QuRStEGWiT9zBWeq0elZkSpwX_k");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Transactions transactions = null;
        List<Booked> booked = null;
        try {
            transactions = client.execute(httpGet, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Transactions.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(transactions);
        return transactions;
    }


}