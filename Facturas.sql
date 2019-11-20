drop database facturacion;
create database facturacion;
use facturacion;

create table cliente(
 id_cliente integer not null auto_increment,
 dni char(9) not null,
 nom_cliente varchar(50) not null,

 primary key(id_cliente)
);

create unique index i_u_cliente_dni on cliente(dni);

create table producto(
 id_producto integer not null auto_increment,
 ean char(13) not null,
 nom_producto varchar(64) not null,
 precio_unitario decimal(8,2) not null,

 primary key(id_producto)
);

create unique index i_u_producto_ean on producto(ean);

create table factura(
 num_factura integer not null auto_increment,
 id_cliente integer not null,
 fecha_factura date not null,

 primary key(num_factura),
 foreign key f_factura_cliente(id_cliente) references cliente(id_cliente)
);

create table linea_factura(
 num_factura integer not null,
 num_linea_factura smallint not null,
 id_producto integer not null,
 cantidad integer not null,

 primary key(num_factura, num_linea_factura),
 foreign key f_linfact_factura(num_factura) references factura(num_factura),
 foreign key f_linfact_producto(id_producto) references producto(id_producto)
);
