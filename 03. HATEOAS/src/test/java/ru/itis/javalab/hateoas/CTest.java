package ru.itis.javalab.hateoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.javalab.hateoas.models.C;
import ru.itis.javalab.hateoas.services.CService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.itis.javalab.hateoas.models.C.Value.ASD;
import static ru.itis.javalab.hateoas.models.C.Value.QWE;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs("target/snippets")
public class CTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CService service;

    @BeforeEach
    public void setup() {
        when(service.update(1L, QWE)).then(x -> new C(1L, QWE));
        when(service.update(1L, ASD)).then(x -> new C(1L, ASD));
    }

    @Test
    public void testMakeASD() throws Exception {
        mockMvc.perform(put("/api/c/1/makeASD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("ASD"))
                .andExpect(jsonPath("$._links.makeQWE").isNotEmpty())
                .andDo(document("makeASD"));
    }

    @Test
    public void testMakeQWE() throws Exception {
        mockMvc.perform(put("/api/c/1/makeQWE"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("QWE"))
                .andExpect(jsonPath("$._links.makeASD").isNotEmpty())
                .andDo(document("makeQWE"));
    }
}
