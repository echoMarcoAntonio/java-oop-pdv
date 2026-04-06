# java-oop-pdv
Sistema de Ponto de Venda desenvolvido para experimentação e fixação de conceitos de Programação Orientada a Objetos e comportamento da JVM.

### Estrutura
- `Produto`: representação do catálogo com validação.
- `ItemVenda`: abstração de linha de venda (Produto + Quantidade). cálculo de subtotal
- `exception/`: hierarquia de exceções de domínio para tratamento de erros de acordo com contexto.

### CLI
```bash
cd src/
javac main/java/pdv/exception/**/*.java main/java/pdv/*.java
java domain.br.com.pdv.ses.Main
