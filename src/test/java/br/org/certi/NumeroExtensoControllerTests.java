/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.org.certi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class NumeroExtensoControllerTests {

	@Autowired
	private MockMvc mockMvc;

    @Test
    public void converter0() throws Exception {
        assertNumber(0, "zero");
    }

	@Test
	public void converter1() throws Exception {
		assertNumber(1, "um");
	}

    @Test
    public void converter_1() throws Exception {
        assertNumber(-1, "menos um");
    }

	@Test
	public void converter2() throws Exception {
		assertNumber(2, "dois");
	}

	@Test
	public void converter10() throws Exception {
		assertNumber(10, "dez");
	}

	@Test
	public void converter11() throws Exception {
		assertNumber(11, "onze");
	}

	@Test
	public void converter20() throws Exception {
		assertNumber(20, "vinte");
	}

    @Test
    public void converter35() throws Exception {
        assertNumber(32, "trinta e dois");
    }

    @Test
    public void converter_43() throws Exception {
        assertNumber(-43, "menos quarenta e três");
    }

    @Test
    public void converter54() throws Exception {
        assertNumber(54, "cinquenta e quatro");
    }

	@Test
	public void converter99() throws Exception {
		assertNumber(99, "noventa e nove");
	}

	@Test
	public void converter100() throws Exception {
		assertNumber(100, "cem");
	}

	@Test
	public void converter101() throws Exception {
		assertNumber(101, "cento e um");
	}

    @Test
    public void converter265() throws Exception {
        assertNumber(265, "duzentos e sessenta e cinco");
    }

    @Test
    public void converter_376() throws Exception {
        assertNumber(-376, "menos trezentos e setenta e seis");
    }

    @Test
    public void converter487() throws Exception {
        assertNumber(487, "quatrocentos e oitenta e sete");
    }

    @Test
    public void converter588() throws Exception {
        assertNumber(588, "quinhentos e oitenta e oito");
    }

    @Test
    public void converter1000() throws Exception {
        assertNumber(1000, "um mil");
    }

    @Test
    public void converter1019() throws Exception {
        assertNumber(1019, "um mil e dezenove");
    }

    @Test
    public void converter_1612() throws Exception {
        assertNumber(-1612, "menos um mil e seiscentos e doze");
    }

    @Test
    public void converter10713() throws Exception {
        assertNumber(10713, "dez mil e setecentos e treze");
    }

    @Test
    public void converter15814() throws Exception {
        assertNumber(15814, "quinze mil e oitocentos e quatorze");
    }

    @Test
    public void converter17916() throws Exception {
        assertNumber(17916, "dezessete mil e novecentos e dezesseis");
    }

    @Test
    public void converter50018() throws Exception {
        assertNumber(50018, "cinquenta mil e dezoito");
    }

    @Test
    public void converter99999() throws Exception {
        assertNumber(99999, "noventa e nove mil e novecentos e noventa e nove");
    }

    @Test
    public void converter_99999() throws Exception {
        assertNumber(-99999, "menos noventa e nove mil e novecentos e noventa e nove");
    }

    //@Test
    public void converter100000Error() throws Exception {
        this.mockMvc.perform(get("/10000")).andDo(print()).andExpect(status().is5xxServerError());
    }

    //@Test
    public void converter_100000Error() throws Exception {
        this.mockMvc.perform(get("/-10000")).andDo(print()).andExpect(status().is5xxServerError());
    }

    private void assertNumber(Integer numero, String extenso) throws Exception {
		this.mockMvc.perform(get("/" +  numero)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.extenso").value(extenso));
	}

}
