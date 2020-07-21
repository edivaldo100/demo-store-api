create table ordered (
        id bigint(20) NOT NULL,
        user_id bigint,
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    create table product (
        id bigint(20) NOT NULL,
        name varchar(255) not null,
        price bigint not null,
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
    create table product_item (
        id bigint(20) NOT NULL,
        ordered_id bigint,
        product_id bigint,
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    
    create table stock (
        id bigint(20) NOT NULL,
        quantity integer not null,
        primary key (id)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Indexes for table `ordered`
--
ALTER TABLE `ordered`
  	add constraint FKjqbh5jbj1olkur6fpmle5r9ev 
	foreign key (user_id) 
	references user (id);
  


  --
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
  
  
  
  --
-- Indexes for table `product_item`
--
ALTER TABLE `product_item`
  	add constraint FK2sj1le2ohu2hby2uxwk7h91e6 
        foreign key (ordered_id) 
        references ordered (id);	
alter table product_item 
        add constraint FKa9mjpi98ark8eovbtnnreygbb 
        foreign key (product_id) 
        references product(`id`);	
