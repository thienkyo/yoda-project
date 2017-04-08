INSERT INTO `categories` (`category_id`, `category_name`, `mod_date`, `status`) VALUES
(1, 'Electric skateboard', '2017-02-24 22:41:00', 1),
(2, 'Smart Switch', '2017-02-24 22:42:00', 1);

INSERT INTO `ship_cost` (`ship_cost_id`, `distance`, `price`, `region`) VALUES
(1, 8, 15000, 'Q5 - Q6 - Q10 - Q11 - Q8 - Q1 và Q3 '),
(2, 15, 20000, 'Q Bình Tân, Tân Phú, Tân Bình, Phú Nhuận, Bình Thạnh, Q1, Q4, Q7'),
(3, 20, 30000, 'Q. Gò Vấp, Q9, Q Thủ Đức, Q2, Thanh Đa, Huyện Hóc Môn, Nhà Bè, Cần Giờ, Bình Chánh, Củ Chi'),
(4, 300, 60000, 'Miền Nam'),
(5, 600, 70000, 'Miền Trung'),
(6, 900, 100000, 'Miền Bắc');

INSERT INTO `members` (`address`, `city`, `country`, `district`, `email`, `full_name`, `mod_date`, `pass`, `phone`, `post_code`, `status`, `street`, `ship_cost_id`) VALUES
('91/5 trần chánh chiếu, phường 14, quận5, tp Hồ Chí Minh', NULL, NULL, NULL, 'thienkyo@gmail.com', 'thien le', '2017-03-09 04:06:00', 'MTIzNDU2bHR0', '0909957872', NULL, 1, NULL, 1),
('85/15 nguyễn Ảnh Thủ, ấp Tây Lân, xã Bà Điểm, tp Hồ Chí Minh', NULL, NULL, NULL, 'member@gmail.com', 'member', '2017-04-03 23:59:40', 'MTIzNDU2bHR0', '444444444444', NULL, 1, NULL, 3),
('', '', '', '', 'thuan@gmail.com', 'thuan', '2017-03-25 21:43:33', 'MTIzNDU2bHR0', '', '', 1, '', 3),
('', '', '', '', 'thien2@gmail.com', 'thien2', '2017-03-29 22:28:20', 'MTIzNDU2bHR0', '0909957872', '', 1, '', 1);

INSERT INTO `member_role` (`id`, `email`, `role`, `role_id`) VALUES
(1, 'thienkyo@gmail.com', 'ADMIN', 1),
(2, 'thienkyo@gmail.com', 'MEMBER', 1),
(3, 'member@gmail.com', 'MEMBER', 2),
(4, 'thuan@gmail.com', 'MEMBER', 3),
(5, 'thien2@gmail.com', 'MEMBER', 4);

INSERT INTO `products` (`prod_name`, `description`, `discount`, `image`, `notification`, `price`, `quantity`, `status`, `category_id`, `mod_date`, `weight`) VALUES
('Dây đai răng/Belt 3M 213 ', 'dây belt đai răng 3M 213', 0, 'testBoard.jpg', 'sắp có hàng', 90000, 10, 2, 1, '2017-02-24 22:48:00', 1.0),
('Ván trượt/Deck ', 'Ván chưa bao gồm bánh xe', 0, 'testBoard.jpg', 'sắp có hàng', 600000, 5, 2, 1, '2017-02-24 00:00:00', 1.0),
('Gá động cơ/motor mount', 'Gá động cơ/motor mount', 0, 'testBoard.jpg', 'sắp có hàng', 400000, 10, 2, 1, '2017-02-24 00:00:00', 1.0),
('Động cơ/motor', '5060 2000w trục cam 8mm', 0, 'testBoard.jpg', 'sắp có hàng', 1200000, 10, 2, 1, '2017-02-24 00:00:00', 1.0),
('Trục bánh xe/Truck', '180mm caliber, \r\nstyle: longboard\r\nreverse king pin', 0, 'testBoard.jpg', 'sắp có hàng', 500000, 10, 2, 1, '2017-02-24 00:00:00', 1.0),
('bánh xe/Wheels', 'set 4 bánh, 90mm\r\nstyle:flywheel', 0, 'testBoard.jpg', 'sắp có hàng', 500000, 10, 2, 1, '2017-02-24 00:00:00', 1.0),
('pin', 'spec: 6s3p 18 viên 18650', 0, 'testBoard.jpg', 'sắp có hàng', 1200000, 10, 2, 1, '2017-02-24 00:00:00', 1.0);




