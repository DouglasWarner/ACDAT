create database empresa2;

drop table if exists empleado_plantilla;
drop table if exists empleado;

create table empleado (
    dni char(9) not null,
    nomEmp varchar(255) not null,

    primary key (dni)
);

create table empleado_plantilla (
    dni char(9) not null,
    numEmp char(9) not null,

    primary key (dni),
    foreign key (dni) references empleado(dni) on update cascade
);