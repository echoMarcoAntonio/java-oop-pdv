# java-oop-pdv
Sistema de Ponto de Venda desenvolvido para experimentação e fixação de conceitos de Programação Orientada a Objetos e comportamento da JVM. O projeto foca em integridade de dados e design de software, ignorando frameworks ou bibliotecas externas.

### Diretrizes de Engenharia
* **Fail-Fast:** Invariantes de negócio validadas no construtor para impedir a existência de objetos em estado inválido.
* **Encapsulamento:** Atributos privados, imutabilidade onde possível e proteção de coleções contra mutação externa.
* **Domínio Rico:** Lógicas de cálculo (como subtotalização) movidas para as entidades responsáveis (ItemVenda).
* **Tipagem Financeira:** Uso exclusivo de `BigDecimal` para garantir precisão decimal em operações monetárias.

### Estrutura
- `Produto`: Gerenciamento de estado e validação de catálogo.
- `ItemVenda`: Abstração de linha de venda (Produto + Quantidade).
- `exception/`: Hierarquia de falhas de domínio para tratamento semântico de erros.

### CLI
```bash
cd src/
javac main/java/pdv/exception/**/*.java main/java/pdv/*.java
java main.java.pdv.Main
