INSERT INTO `store` (`id`, `name`) 
VALUES (NULL, 'ONE STORE BR');

INSERT INTO `user` (`id`, `name`, `email`, `password`, `profile`, `store_id`) 
VALUES (NULL, 'Admin', 'admin@onestore.com', '$2a$06$xIvBeNRfS65L1N17I7JzgefzxEuLAL0Xk0wFAgIkoNqu9WD6rmp4m', 'ROLE_ADMIN',(select `id` from store where name = 'ONE STORE BR'));
