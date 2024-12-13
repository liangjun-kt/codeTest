-- invoices.users definition
USE 'invoices'
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `company_name` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- invoices.invoices definition
DROP TABLE IF EXISTS `invoices`;
CREATE TABLE `invoices` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `user_id` int DEFAULT NULL,
                            `issue_date` date NOT NULL,
                            `payment_amount` decimal(15,2) NOT NULL,
                            `fee` decimal(15,2) NOT NULL,
                            `fee_rate` decimal(5,2) NOT NULL,
                            `tax_amount` decimal(15,2) NOT NULL,
                            `tax_rate` decimal(5,2) NOT NULL,
                            `total_amount` decimal(15,2) NOT NULL,
                            `payment_due_date` date NOT NULL,
                            `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO invoices.users (id, company_name, name, email, password, created_at, updated_at)
VALUES(1, 'test株式会社', 'user1', 'user1@gmail.com', 'X03MO1qnZdYdgyfeuILPmQ==', '2024-12-13 14:00:40', '2024-12-13 14:00:40'),
      (5, 'test株式会社', 'user2', 'user2@gmail.com', 'X03MO1qnZdYdgyfeuILPmQ==', '2024-12-13 18:57:56', '2024-12-13 18:57:56');


INSERT INTO invoices.invoices
(id, user_id, issue_date, payment_amount, fee, fee_rate, tax_amount, tax_rate, total_amount, payment_due_date, created_at, updated_at)
VALUES(2, 1, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-13', '2024-12-13 12:25:43', '2024-12-13 12:25:43'),
VALUES(3, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-13', '2024-12-13 14:17:20', '2024-12-13 14:17:20'),
VALUES(4, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-13', '2024-12-13 14:17:21', '2024-12-13 14:17:21'),
VALUES(5, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-13', '2024-12-13 14:17:22', '2024-12-13 14:17:22'),
VALUES(6, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-13', '2024-12-13 14:17:23', '2024-12-13 14:17:23'),
VALUES(7, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-13', '2024-12-13 14:17:23', '2024-12-13 14:17:23'),
VALUES(8, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-13', '2024-12-13 14:17:24', '2024-12-13 14:17:24'),
VALUES(9, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-25', '2024-12-13 15:20:52', '2024-12-13 15:20:52'),
VALUES(10, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-25', '2024-12-13 15:20:53', '2024-12-13 15:20:53'),
VALUES(11, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-25', '2024-12-13 15:20:54', '2024-12-13 15:20:54'),
VALUES(12, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-25', '2024-12-13 15:21:14', '2024-12-13 15:21:14'),
VALUES(13, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-25', '2024-12-13 15:21:15', '2024-12-13 15:21:15'),
VALUES(14, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-25', '2024-12-13 15:21:16', '2024-12-13 15:21:16'),
VALUES(15, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-25', '2024-12-13 15:21:16', '2024-12-13 15:21:16'),
VALUES(16, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-25', '2024-12-13 15:21:17', '2024-12-13 15:21:17'),
VALUES(17, 5, '2024-12-13', 10000.00, 400.00, 0.04, 40.00, 0.10, 10440.00, '2024-12-25', '2024-12-13 15:21:17', '2024-12-13 15:21:17'),
VALUES(18, 5, '2024-12-13', 11000.00, 440.00, 0.04, 44.00, 0.10, 11484.00, '2024-12-25', '2024-12-13 15:21:22', '2024-12-13 15:21:22'),
VALUES(19, 5, '2024-12-13', 11000.00, 440.00, 0.04, 44.00, 0.10, 11484.00, '2024-12-25', '2024-12-13 15:21:22', '2024-12-13 15:21:22'),
VALUES(20, 5, '2024-12-13', 11000.00, 440.00, 0.04, 44.00, 0.10, 11484.00, '2024-12-25', '2024-12-13 15:21:23', '2024-12-13 15:21:23'),
VALUES(21, 5, '2024-12-13', 11000.00, 440.00, 0.04, 44.00, 0.10, 11484.00, '2024-12-25', '2024-12-13 15:21:24', '2024-12-13 15:21:24'),
VALUES(22, 5, '2024-12-13', 11000.00, 440.00, 0.04, 44.00, 0.10, 11484.00, '2024-12-25', '2024-12-13 15:21:24', '2024-12-13 15:21:24');