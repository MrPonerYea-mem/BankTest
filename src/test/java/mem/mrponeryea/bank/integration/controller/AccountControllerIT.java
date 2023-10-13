package mem.mrponeryea.bank.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import mem.mrponeryea.bank.integration.annotation.IT;
import mem.mrponeryea.bank.model.dto.AccountCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class AccountControllerIT {

    @Autowired
    private MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                                                        MediaType.APPLICATION_JSON.getSubtype(),
                                                                        Charset.forName("utf8"));

    @Test
    void test() throws Exception {
        AccountCreateDto accountCreateDto = new AccountCreateDto("some_new_name", "1234");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(accountCreateDto);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/accounts")
                                                  .contentType(APPLICATION_JSON_UTF8)
                                                  .content(requestJson))
            .andExpect(status().is2xxSuccessful())
            .andReturn();
    }
}
