## Resume Table

|         Database Entity         | Rule                                                                                                                                                                 |
|:-------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|         **Table Name**          | Must not include protected keywords  (User, Dual, Start, etc.)                                                                                                       |
|                                 | Must follow the proper Camel case with the first letter of name capitalized.                                                                                         |
|       **Table Attribute**       | Must follow proper camel case with first character not capitalized.                                                                                                  |
| **Table Attributes Constraint** | If inside table creation, may (or may not) have a dedicated name (ex. check (regex_like(code,"\d{8}\w{3}"))).                                                        |
|         **Primary Key**         | Must be as simple as possible, using camel case, with first character uncapitalized, and, in preference, one word.                                                   |
|         **Foreign Key**         | Must follow the same rules as Primary Keyand the name must be related to the relation that results in the Foreign Key .                                              |
|          **Function**           | Functions must follow the convention fncUS, where NNN is the number of the US stated in the requirements and the description the function main goal, in Camel Case   |                                                                                                                                                                                                                                                                                                                        |
|          **Procedure**          | Procedures must follow the convention prcUS, where NNN is the number of the US stated in the requirements and the description the procedure main goal, in Camel Case |
|           **Trigger**           | Triggers must follow the convention trg, where designation is the triggers main goal, in Camel Case                                                                  |
|         **Pascalcase**          | Table names                                                                                                                                                          |
|          **Snakecase**          | Table columns (attributes)                                                                                                                                           |


## Terms, Expressions and Acronyms (TEA)

| **_TEA_** (EN) | **_Description_** (EN) |
|:---------------|:-----------------------|
| **PK**         | Primary Key            |
| **FK**         | Foreign Key            |
| **UN**         | Unique                 |
| **NN**         | Not Null               |
| **DF**         | Default                |
| **ID**         | Index                  |

Note: The SQL syntax is case-insensitive,so anything related to Camel case, it only applies to diagrams and documentation.
## Data Glossary

| **Business Concept**  | **Description**                                                                                                                                                                                    |
|:----------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Agricultura Biológica | modo de produção fomenta a biodiversidade, os ciclos biológicos e a actividade biológicas dos solos.                                                                                               |
| Parcela Agrícola      | também designados por sectores ou campos, são caracterizados por uma designação, área (ha) e cultura (macieira, pereira, trigo, feijão).                                                           |
| Cultura               | espécie vegetal utilizada na agricultura com o objectivo de ser consumida porhumanos/animais  ou para produzir adubação verde.                                                                     |
| Factores de Produção  | os produtos que são aplicados no solo ou nas plantas, por forma a melhorar e nutrir o solo e as plantas                                                                                            |
| Sistema de rega       | um conjunto de equipamentos capaz de fazer chegar às culturas água ou soluções aquosas contendo factores de produção .                                                                             |
| Estação Meteorológica | um conjunto de sensores que permitem medir grandezas atmosférica.                                                                                                                                  |
| Sensores              | um dispositivo que responde a um estímulo físico ou químico de maneira específica, produzindo um sinal que pode ser transformado em outra grandeza física para fins de medição e/ou monitoramento. |
| Caderno de Campo      | é um documento formal (obrigatório) que permite registar todas as operações agrícolas relevantes ocorridas na exploração.                                                                          |



## Database Exception Codes
| **Code** | **Exception** | **Message** |
|:---------|:--------------|:------------|


## Database Change Log

### Sprint 1 -> Sprint 2




## Database Technology

This database uses **ORACLE SQL**, for the following reasons:

- Possibility to work with complex queries and reports (essential for a management application for a logistics company);

- High transaction application (must be able to store large amounts of data -  by the hour/minute/second) in a stable way and ensuring data integrity;

- Rigid business concepts (not a lot of changes to the business model are anticipated since it is a traditional industry);

- No need for many data types.



