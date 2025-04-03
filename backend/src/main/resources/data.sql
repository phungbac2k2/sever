



INSERT INTO `account` VALUES (1,'Le Van','0397679056','van@gmail.com','$2a$10$hKkrJ0a6zhruSKpwuf.HnussGui6pFiz8TB63rf9c.zyWxSDUUKmW','ROLE_ADMIN','cus_Od3Iganq9ICuch','2023-05-13'),(2,'Le Vu','0975701636','vu@gmail.com','$2a$10$hKkrJ0a6zhruSKpwuf.HnussGui6pFiz8TB63rf9c.zyWxSDUUKmW','ROLE_USER','cus_Oci2n2Wr8YwkOu','2023-06-23'),(3,'Tan Vu','0563839745','tan@gmail.com','$2a$10$hKkrJ0a6zhruSKpwuf.HnussGui6pFiz8TB63rf9c.zyWxSDUUKmW','ROLE_USER','cus_OlaXIsYakEJMs7','2023-07-23'),(4,'Van','0563727844','fs@gmail.com','$2a$10$hKkrJ0a6zhruSKpwuf.HnussGui6pFiz8TB63rf9c.zyWxSDUUKmW','ROLE_USER','x','2023-08-23'),(5,'Vu Lan','0547467583','dddgmail.com','$2a$10$hKkrJ0a6zhruSKpwuf.HnussGui6pFiz8TB63rf9c.zyWxSDUUKmW','ROLE_USER','x','2023-09-23'),(6,'Tinh','0165883737','xxxxxzzxz@gmail.com','$2a$10$hKkrJ0a6zhruSKpwuf.HnussGui6pFiz8TB63rf9c.zyWxSDUUKmW','ROLE_USER','x','2023-05-13'),(7,'Thu Van','0957657434','cxc@gmail.com','$2a$10$hKkrJ0a6zhruSKpwuf.HnussGui6pFiz8TB63rf9c.zyWxSDUUKmW','ROLE_USER','x','2023-05-16'),(8,'Van Dung','0974853846','zzs@gmail.com','$2a$10$hKkrJ0a6zhruSKpwuf.HnussGui6pFiz8TB63rf9c.zyWxSDUUKmW','ROLE_USER','x','2023-07-16');




INSERT INTO `invoices` VALUES (31,27,10,'2023-09-29',1),(32,28,10,'2023-09-29',1),(33,29,320,'2023-09-30',1),(34,30,10,'2023-09-30',1),(35,31,10,'2023-09-30',1),(36,32,10,'2023-10-02',1),(37,33,100,'2023-10-02',1),(38,34,100,'2023-10-02',1),(39,35,10,'2023-10-02',1),(40,36,10,'2023-10-02',1),(41,37,10,'2023-10-02',1),(42,38,10,'2023-10-02',1),(43,39,10,'2023-10-02',1),(44,40,100,'2023-10-02',1),(45,41,10,'2023-10-02',1),(46,42,10,'2023-10-04',1),(48,44,10,'2023-10-06',1),(49,45,0,'2023-10-06',1),(50,46,320,'2023-10-06',1),(51,47,100,'2023-10-06',1),(52,48,10,'2023-10-06',1),(53,49,0,'2023-10-06',1),(54,50,10,'2023-10-06',1);







INSERT INTO `package` VALUES (1,'Dùng thử',0,0,0,0,1,'0$ được dùng thử dịch vụ trong 14 ngày, mỗi ngày tối đa dùng 12 tiếng máy LINUX RAM 4G'),(2,'Cơ Bản 1',10,100,0,1,2,'Gói tháng: 10$/tháng được dùng máy Linux RAM 8GB/ Mac RAM 8GB. Gói năm: 100$/năm  được dùng máy Linux RAM 8GB/ Mac RAM 8GB'),(3,'Nâng cao',30,320,0,1,3,'Gói tháng: 30$/tháng được dùng máy Linux RAM 16GB/ Mac RAM 16GB. Gói năm: 320$/năm  được dùng máy Linux RAM 16GB/ Mac RAM 16GB'),(4,'On-demand',10,0,0,1,4,'Cố định 10$/tháng, miễn phí 7 ngày đầu, sau 7 ngày 0.5$/24 tiếng');






INSERT INTO `payment_method` VALUES (29,'pm_1Ny3MDEXx0Jio81j253dk8j4',3),(30,'pm_1Ny3PlEXx0Jio81jbRvoPZLs',2),(32,'pm_1Ny3QxEXx0Jio81jYnrwnj4O',2),(33,'pm_1Ny5VJEXx0Jio81jpd8fOFfE',2);






INSERT INTO `subscriptions` VALUES (31,2,2,'2023-09-30',10,30,'2023-06-30','RrerVxN4BD5cMJwBHELo',0),(32,2,2,'2023-10-02',10,30,'2023-06-02','CVSwmzVLYFQqWQYy7yaw',1),(33,2,2,'2023-10-02',100,365,'2023-07-02','IeaZAGF4dVnGDSsvGQlW',1),(34,2,2,'2023-10-02',100,365,'2023-08-02','L7nBtrzdy2JbXpF2ryHj',1),(35,2,2,'2023-10-02',10,30,'2023-08-02','D7cBViQ6DUar6KJOVtwv',1),(36,2,2,'2023-10-02',10,30,'2023-09-02','WLfYQxGkiKZOfGvNU4S3',1),(37,2,2,'2023-10-02',10,30,'2023-09-02','dh7YPzargSQoJcZ3Iyjy',1),(38,2,2,'2023-10-02',10,30,'2023-09-02','RuGWwXLyilr72ugi8IR4',1),(39,2,2,'2023-10-02',10,30,'2023-10-02','Mb9ETH2QeHAlBwc5WRT1',1),(40,2,2,'2023-10-02',100,365,'2023-10-02','CwqZUg4IBGidvRGrj5xo',1),(41,2,2,'2023-10-02',10,30,'2023-10-02','uNcDhCRccsJmL67kmPHn',1),(42,2,4,'2023-09-06',10,30,'2023-09-04','UZqfvSpnLBIihe5Q4Dtu',-1),(44,3,2,'2023-10-06',10,30,'2023-10-06','q72uVm3i426fCcANl0sx',1),(45,3,1,'2023-10-06',0,14,'2023-10-06','yMLlms9JcCFSj62DadS6',1),(46,2,3,'2023-10-06',320,365,'2023-09-06','gJMMI1pe2ur8xZzoMP9n',1),(47,2,2,'2023-10-06',100,365,'2023-10-06','EfzefcSR9m8LT1FbRWpm',-1),(48,2,2,'2023-10-06',10,30,'2023-10-06','WiEnHYQ1ES2QZGOg2cK8',-1),(49,2,1,'2023-10-06',0,14,'2023-10-06','IRIqrcPRggm3Pk2RrFO9',1),(50,2,2,'2023-10-06',10,30,'2023-10-06','q3QQxEqm3RkiE6oQo5pB',1);



