create database empresa;

drop table if exists empleado;

create table empleado (
    dni char(9) not null,
    nomEmp varchar(255) not null,
    numEmp char(9),
    tipo char(2) not null,

    primary key (dni),
    constraint check_tipos check((tipo='E' and isnull(numEmp)) or (tipo='EP' and not isnull(numEmp)))
);

