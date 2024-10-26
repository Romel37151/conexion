drop table if exists historial_movimientos;
drop table if exists productos; 
drop table if exists categorias;

create table productos(
id char(4) not null primary key,
	nombre varchar(100) not null,
	precio_venta money not null,
	precio_compra money not null,
	id_categoria char(4) not null
	
	
);
	
create table categorias(
id char(4) not null primary key,
	nombre varchar(100) not null	
);

create table historial_movimientos(
id serial not null primary key,
	id_productos char(4) not null,
	cantidad int not null,
	fecha_movimiento timestamp not null
	
);

alter table productos
add constraint productos_fk foreign key(id_categoria)
references 	categorias(id) ;

alter table historial_movimientos
add constraint historial_movimientos_fk foreign key(id_productos)
references 	productos(id) ;










insert into categorias (id, nombre) 
values ('C001','Bebidas');
insert into categorias (id, nombre) 
values ('C002','Snacks');
insert into categorias (id, nombre) 
values ('C003','Golosinas');

insert into productos (id,nombre,precio_venta,precio_compra,id_categoria)
values('P001','Coca cola 300 ml',0.7,0.55,'C001');
insert into productos (id,nombre,precio_venta,precio_compra,id_categoria)
values('P002','Coca cola 1000 ml',1,0.8,'C001');
insert into productos (id,nombre,precio_venta,precio_compra,id_categoria)
values('P003','Doritos 50g',0.5,0.42,'C002');
insert into productos (id,nombre,precio_venta,precio_compra,id_categoria)
values('P004','Manicho',0.25,0.21,'C003');
insert into productos (id,nombre,precio_venta,precio_compra,id_categoria)
values('P005','Tango',0.5,0.42,'C003');


insert into historial_movimientos(id_productos,cantidad,fecha_movimiento)
values ('P001',10,'2020/10/28 9:45');
insert into historial_movimientos(id_productos,cantidad,fecha_movimiento)
values ('P002',-3,'2020/10/28 10:49');
insert into historial_movimientos(id_productos,cantidad,fecha_movimiento)
values ('P001',5,'2020/10/28 12:23');


select * from productos;
select * from categorias; 
select * from historial_movimientos;



