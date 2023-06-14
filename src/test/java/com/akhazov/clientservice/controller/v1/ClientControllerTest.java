package com.akhazov.clientservice.controller.v1;

import com.akhazov.clientservice.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
@MockBeans({@MockBean(ClientService.class)})
@DisplayName("Тесты контроллера операций с клиентом.")
class ClientControllerTest {
    public static final String ID = "bf59219c-c9ff-4f4a-b307-07e7dbf8b1b1";


    @Autowired
    private MockMvc clientController;

    @Test
    @DisplayName("Поиск клиента по id.")
    void getClientById() throws Exception {
        clientController.perform(get("/v1/clients/{id}", ID))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Создание клиента.")
    void createClient() throws Exception {
        clientController.perform(post("/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-Source", "mail")
                        .content("""
                                {
                                  "bankId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                                  "firstName": "Ivan",
                                  "middleName": "Ivanovich",
                                  "lastName": "Ivanov",
                                  "birthdate": "2000-06-14",
                                  "passportId": "1111 111111",
                                  "birthplace": "birthplace",
                                  "phoneNumber": "79876543210",
                                  "email": "string@gmail.com",
                                  "registrationAddress": "registrationAddress",
                                  "residentialAddress": "registrationAddress"
                                }
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Создание клиента без обязательного заголовка.")
    void createClient_withoutHeader() throws Exception {
        clientController.perform(post("/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "bankId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                                  "firstName": "Ivan",
                                  "middleName": "Ivanovich",
                                  "lastName": "Ivanov",
                                  "birthdate": "2000-06-14",
                                  "passportId": "1111 111111",
                                  "birthplace": "birthplace",
                                  "phoneNumber": "79876543210",
                                  "email": "string@gmail.com",
                                  "registrationAddress": "registrationAddress",
                                  "residentialAddress": "registrationAddress"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Поиск клиента.")
    void find() throws Exception {
        clientController.perform(post("/v1/clients/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "firstName": "Ivan",
                                   "middleName": "",
                                   "lastName": "",
                                   "phoneNumber": "",
                                   "email": ""
                                 }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Поиск клиента c невалидным телом запроса.")
    void find_invalidBody() throws Exception {
        clientController.perform(post("/v1/clients/find")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "firstName": "Ivan",
                                   "middleName": "",
                                   "lastName": "",
                                   "phoneNumber": ""
                                 }
                                """))
                .andExpect(status().isBadRequest());
    }

}