package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BuddyInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBuddy() throws Exception {
        this.mockMvc.perform(put("/addressBook")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"id\"")));
        this.mockMvc.perform(post("/buddyInfo?bookId=1").contentType(MediaType.APPLICATION_JSON).content("""
                {
                \t"name":"SpongeBob",
                \t"number":1234456,
                \t"address":"Bikini Bottom"
                }""")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/buddyInfo?bookId=1&buddyId=1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"name\":\"SpongeBob\"")));
    }

    @Test
    public void addBuddy() throws Exception {
        this.mockMvc.perform(put("/addressBook")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"id\"")));
        this.mockMvc.perform(post("/buddyInfo?bookId=1").contentType(MediaType.APPLICATION_JSON).content("""
                {
                \t"name":"SpongeBob",
                \t"number":1234456,
                \t"address":"Bikini Bottom"
                }""")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void removeBuddy() throws Exception {
        this.mockMvc.perform(put("/addressBook")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"id\"")));
        this.mockMvc.perform(post("/buddyInfo?bookId=1").contentType(MediaType.APPLICATION_JSON).content("""
                {
                \t"name":"SpongeBob",
                \t"number":1234456,
                \t"address":"Bikini Bottom"
                }""")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(delete("/buddyInfo?bookId=1&buddyId=1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("\"name\":\"SpongeBob\"")));
    }
}
