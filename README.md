# Desafio técnico CERTI


### Construção

Foi desenvolvido com **Springboot** versão 2.2.1.RELEASE




## Executando via maven

O projeto pode ser execultado com o comando abaixo


```
mvn spring-boot:run
```


## Docker



## Solução


##### Usado a API

```
curl -X GET http://localhost:3000/101
```
Este retorna status code 200 com json abaixo

```json
{
    "extenso": "cento e um"
}
```

##### Testes

Foram criados 28 testes unitários, abrangendo todos os nomes numéricos e limite superior e inferior

Arquivos de configuração estão em [NumeroExtensoControllerTests](src/test/java/br/org/certi/NumeroExtensoControllerTests.java)


```
mvn test
```



