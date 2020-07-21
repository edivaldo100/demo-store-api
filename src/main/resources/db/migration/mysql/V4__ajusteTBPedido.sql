  
insert into product (id, name, price) 
	values (null, '1 garfo de plastico', 30);
	
insert into product (id, name, price) 
	values (null, '30 Pencas de Banana', 1530);
	
insert into product (id, name, price) 
	values (null, 'Iceberg (1000M2)', 8230);
	
insert into product (id, name, price) 
	values (null, 'Lote na Lua (100X30)', 600000);

insert into product (id, name, price) 
	values (null, 'Foguete modelo 2000', 700000);
	
	
insert into store.ordered (id, user_id) 
	values (null,1);
	
 insert into store.product_item (id, ordered_id, product_id) 
	values (null, 1,1);
