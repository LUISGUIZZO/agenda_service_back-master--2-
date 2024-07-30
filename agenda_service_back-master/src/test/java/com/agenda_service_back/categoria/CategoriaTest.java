package com.agenda_service_back.categoria;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.awt.*;
import static org .springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org .springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org .springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaTest{


    @Autowired
    private MockMvc mockMvc;

    @Autowired
private ObjectMapper objectMapper;


    @Test
    @DisplayName("Verificar se a rota de categoria está respondendo corretamente")
    void index() throws Exception{
        mockMvc.perform(get("/categoria")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Verificar se está criando uma categoria")
    @Transactional
    @Rollback
    void create() throws Exception{
        Categoria categoriaExemplo= new Categoria();
        categoriaExemplo.setCategoria_nome"Jardinagem");
        categoriaExemplo.setCategoria_descricao("Serviços de Jardinagem");

        String jsonRequest = objectMapper.writeValueasString(categoriaExemplo);

        mockMvc.perform(post("/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)).andExpect(status().isCreated());

        TestTransaction.end();



    }



    @Test
    @DisplayName("Verifica se está alterando o registro corretamente ")
    @Transactional
    @Rollback
    void update() throws Exception{
        Categoria categoriaExemplo = new Categoria();
        categoriaExemplo.setCategoria_nome("Pintora");
categoriaExemplo.setCategoria_descricao("serviço");

String jsonRequest = objectMapper.writevalueAssString(categoriaExemplo);


String response = mockMvc.perform(post(";categoria")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest)).andExpect(status().isCreated())
        .andReturn()
        .getResponse()
        .getContentAsString();



JsonNode jsonNode = objectMapper.readtree(response);
long id = jsonNode.get("categoria_id").asLong();

Categoria categoriaUpdate = new Categoria();
        categoriaUpdate.setCategoria_nome("pintura");
categoriaUpdate.setCategoria_descricao("Serviços de Pintura");





    }
}
