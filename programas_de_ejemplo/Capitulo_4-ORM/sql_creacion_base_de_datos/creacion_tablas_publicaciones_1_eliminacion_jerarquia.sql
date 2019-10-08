-- Modelo relacional para correspondencia de la herencia con eliminaci�n de la jerarqu�a,
-- Previamente se borra, si existe, cualquier tabla relacionada con esta jerarqu�a, tanto
-- para esta soluci�n con una �nica tabla como para la soluci�n alternativa con una tabla
-- para la superclase y una tabla por subclase.

drop table if exists revista;
drop table if exists libro;
drop table if exists publicacion;

create table publicacion(
  id_pub integer auto_increment not null,
  nom_pub varchar(50) not null,
  tipo char(3) not null,
  isbn char(13),
  autor varchar(40),
  issn char(10),
  primary key(id_pub),
  constraint check_subtipos check(tipo='pub' or (tipo='lib' and not isnull(isbn) and not isnull(autor)) or (tipo='rev' and not isnull(issn)))
  );

