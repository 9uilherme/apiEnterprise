# apiEnterprise

Esta é uma aplicação Full-stack, há uma api com a arquitetura MVC, construída com as tecnologias Spring Boot e Hibernate. 
São apenas dois modelos, a Empresa e a filial, sendo a relação de OneToMany e ManyToOne, respectivamente. Os endpoints, aqueles que 
recebem as requisições, estão na camada de Controller, que por sua vez injetam Serviços. A camada de serviço é responsável pelas regras
de negócio com suas possiveis validações. A camada de serviço injeta a de persistência e esta realiza as consultas no banco, sendo possivel
criar query nativa, hql ou usar as abstrações do JpaRepository.

o maven e tomcat estão embutidos, portanto não é necessária a instalação de nada. Todas as dependências estão configuradas no pom.xml. 

O Front-end é composto de view e controllers, sendo a view com páginas html e controllers do angularJs (1.6)

Executar: 

Para rodar o sistema basta importar como projeto maven no eclipse;
mvn clean install para baixar as dependências
executar a classe ApiEnterpriseApplication como java application, pois ela está anotada como a classe Principal

Usando:

O sistema inicia-se na página de listagem, e todas as ações estão em botões coloridos. Apenas o cadastro de uma nova empresa
encontra-se no menu superior. É possível importar e exportar xml. Para importar, segue o padrão: 

<list>
  <empresa>
    <razaoSocial>Carrefour</razaoSocial>
    <cnpj>41344144</cnpj>
    <status>ativo</status>
  </empresa>
  <empresa>
    <razaoSocial>Oobj</razaoSocial>
    <cnpj>3214441</cnpj>
    <status>ativo</status>
    <filial>
      <cnpj>12455</cnpj>
      <municipio>goiania</municipio>
      <uf>goias</uf
    </filial>
  </empresa>
</list>

*não coloque datacriacao ou dataalteracao no xml
