
INSERT INTO  mo_user (NAME, USERNAME,PASSWORD,MOBILE_NO,ROLE_ID,ACTIVE) VALUES
('Tony','ironman','','9075225067','Admin',1),('steve','captain','' ,'7972707692','Regular',1);

			
INSERT INTO mo_role(ROLE_ID, ROLE_DESC) VALUES 
('Admin','Super user'),('Regular','Regular User');

			

INSERT INTO mo_role_business_function(ROLE_ID, BUSINESS_FUNCTION_ID) VALUES ('ROLE_ADMIN','ROLE_BF_MANAGE_USER'),('ROLE_ADMIN','ROLE_BF_SHOW_HOME');	

	


INSERT INTO mo_business_function (BUSINESS_FUNCTION_ID, BUSINESS_FUNCTION_DESC) VALUES ('ROLE_BF_REGULAR_USER','Show List User'),('ROLE_BF_REGULAR_HOME','Show home page');


SELECT u1.* from( select u.USER_ID, u.NAME, u.USERNAME, u.PASSWORD, u.ACTIVE, r.ROLE_ID, r.ROLE_DESC, 
GROUP_CONCAT(rbf.BUSINESS_FUNCTION_ID) BUSINESS_FUNCTION_IDS 
FROM mo_user u
LEFT JOIN mo_role r ON u.ROLE_ID = r.ROLE_ID
LEFT JOIN mo_role_business_function rbf ON r.ROLE_ID = rbf.ROLE_ID
GROUP BY u.USER_ID);


SELECT u1.* FROM (SELECT 
            u.USER_ID, u.NAME, u.USERNAME, u.PASSWORD, u.ACTIVE, r.ROLE_ID, r.ROLE_DESC, 
            GROUP_CONCAT(rbf.BUSINESS_FUNCTION_ID) `BUSINESS_FUNCTION_IDS` 
            FROM mo_user u 
            LEFT JOIN mo_role r ON u.ROLE_ID=r.ROLE_ID 
           LEFT JOIN mo_role_business_function rbf ON r.ROLE_ID=rbf.ROLE_ID 
            group by u.USER_ID ) AS u1 WHERE TRUE;


UPDATE mo_user u  SET  u.NAME=:name,u.USERNAME=:username,  WHERE u.USER_ID=:userId;




SELECT r.ROLE_ID `ID`, r.ROLE_DESC `LABEL` FROM mo_role r;





SELECT u1.* 
FROM (
    SELECT 
        u.USER_ID, 
        u.NAME, 
        u.USERNAME, 
        u.PASSWORD, 
        u.ACTIVE, 
        r.ROLE_ID, 
        r.ROLE_DESC,
        GROUP_CONCAT(rbf.BUSINESS_FUNCTION_ID) AS BUSINESS_FUNCTION_IDS
    FROM 
        mo_user u
    LEFT JOIN 
        mo_role r ON u.ROLE_ID = r.ROLE_ID
    LEFT JOIN 
        mo_role_business_function rbf ON r.ROLE_ID = rbf.ROLE_ID
    GROUP BY 
        u.USER_ID
       
) AS u1 
WHERE TRUE;






INSERT INTO items (ITEM_ID, LEVEL, CODE, ITEM_DESCRIPTION, NEXT_LEVEL) VALUES
(1, 0, 'A', 'AC PowerPack', 1),
(2, 0, 'D', 'DC PowerPack', 2),
(3, 1, 'T', '3 Phase AC Motor', 4),
(4, 1, 'S', '1 Phase AC Motor', 3),
(5, 2, '12', '12 V', 5),
(6, 2, '24', '24 V', 6),
(7, 3, 'S4037', '4 Pole, 0.37 Kw', 8),
(8, 3, 'S4075', '4 Pole, 0.75 Kw', 8),
(9, 3, 'S4110', '4 Pole, 1.1 Kw', 8),
(10, 3, 'S2110', '2 Pole, 1.1 Kw', 8),
(11, 3, 'S4150', '4 Pole, 1.5 Kw', 8),
(12, 3, 'S4220', '4 Pole, 2.2 Kw', 8),
(13, 4, 'T4037', '4 Pole, 0.37 Kw', 8),
(14, 4, 'T4075', '4 Pole, 0.75 Kw', 8),
(15, 4, 'T4110', '4 Pole, 1.1 Kw', 8),
(16, 4, 'T2110', '2 Pole, 1.1 Kw', 8),
(17, 4, 'T4150', '4 Pole, 1.5 Kw', 8),
(18, 4, 'T4220', '4 Pole, 2.2 Kw', 8),
(19, 5, 'D1208', '800 Watt, 3000 RPM, 7% S2', 7),
(20, 5, 'D1212', '1200 Watt, 2400 RPM, 4.5% S2', 7),
(21, 5, 'D1216', '1600 Watt, 2500 RPM, 8% S2', 7),
(22, 6, 'D2408', '800 Watt, 3000 RPM, 7% S2', 7),
(23, 6, 'D2422', '2200 Watt, 2600 RPM, 8% S2', 7),
(24, 6, 'D2430', '3000 Watt, 2600 RPM, 9% S2', 7),
(25, 6, 'F2430', '3000 Watt, 3100 RPM, 13% S2', 7),
(26, 6, 'L2430', '3000 Watt, 3100 RPM, 9% S2', 7),
(27, 7, '', 'Relay', 8),
(28, 7, '', 'Without Relay', 8),
(29, 8, 'U1', 'U1', 9),
(30, 8, 'U2', 'U2', 9),
(31, 8, 'U3', 'U3', 17),
(32, 8, 'U4', 'U4', 17),
(33, 8, 'U5', 'U5', 10),
(34, 8, 'U6', 'U6', 11),
(35, 9, 'S', '10 - 160 bar', 17),
(36, 9, 'N', '50 - 180 bar', 17),
(37, 9, 'B', '100 - 290 bar', 17),
(38, 10, 'S', '10 - 160 bar', 12),
(39, 10, 'N', '50 - 180 bar', 12),
(40, 10, 'B', '100 - 290 bar', 12),
(41, 11, 'S', '10 - 160 bar', 13),
(42, 11, 'N', '50 - 180 bar', 13),
(43, 11, 'B', '100 - 290 bar', 13),
(44, 12, 'PS', 'Plugged Single Acting', 14),
(45, 12, 'PD', 'Plugged Double Acting', 14),
(46, 12, 'SC', 'Single Poppet Normally Closed', 14),
(47, 12, 'SO', 'Single Poppet Normally Open', 14),
(48, 12, 'SD', 'Double Poppet Normally Closed', 14),
(49, 13, 'PS', 'Plugged Single Acting', 15),
(50, 13, 'PD', 'Plugged Double Acting', 15),
(51, 13, 'SC', 'Single Poppet Normally Closed', 15),
(52, 13, 'SO', 'Single Poppet Normally Open', 15),
(53, 13, 'SD', 'Double Poppet Normally Closed', 15),
(54, 14, 'XXX', 'Without Power', 17),
(55, 14, 'D12', 'DC 12 V', 17),
(56, 14, 'D24', 'DC 24 V', 17),
(57, 14, 'A11', 'AC 110 V', 17),
(58, 14, 'A23', 'AC 230 V', 17),
(59, 14, 'A24', 'AC 24 V DC', 17),
(60, 15, 'XXX', 'Without Power', 16),
(61, 15, 'D12', 'DC 12 V', 16),
(62, 15, 'D24', 'DC 24 V', 16),
(63, 15, 'A11', 'AC 110 V', 16),
(64, 15, 'A23', 'AC 230 V', 16),
(65, 15, 'A24', 'AC 24 V DC', 16),
(66, 16, 'X', 'Without Flow', 17),
(67, 16, 'A', 'Adjustable Flow', 17),
(68, 16, 'B', '2 LPM', 17),
(69, 16, 'C', '3 LPM', 17),
(70, 16, 'D', '4 LPM', 17),
(71, 16, 'F', '6 LPM', 17),
(72, 16, 'H', '8 LPM', 17),
(73, 16, 'J', '10 LPM', 17),
(74, 16, 'L', '12 LPM', 17),
(75, 16, 'O', '15 LPM', 17),
(76, 17, '13', '1.3 cc/rev', 18),
(77, 17, '16', '1.6 cc/rev', 18),
(78, 17, '23', '2.3 cc/rev', 18),
(79, 17, '27', '2.7 cc/rev', 18),
(80, 17, '32', '3.2 cc/rev', 18),
(81, 17, '37', '3.7 cc/rev', 18),
(82, 17, '42', '4.2 cc/rev', 18),
(83, 17, '58', '5.8 cc/rev', 18),
(84, 17, '70', '7 cc/rev', 18),
(85, 17, '80', '8 cc/rev', 18),
(86, 18, 'X', 'No Tank', 21),
(87, 18, 'P', 'Plastic Tank', 19),
(88, 18, 'S', 'Steel Tank', 20),
(89, 19, '*03', '3 Lt. Square', 21),
(90, 19, '*05', '5 Lt. Square', 21),
(91, 20, '*05', '5 Lt. Round', 21),
(92, 20, '*08', '8 Lt. Round', 21),
(93, 20, '12', '12 Lt. Square Horizontal', 21),
(94, 20, '12', '12 Lt. Square Vertical', 21),
(95, 20, '15', '15 Lt. Square Horizontal', 21),
(96, 20, '15', '15 Lt. Square Vertical', 21),
(97, 20, '25', '25 Lt. Square Horizontal', 21),
(98, 20, '25', '25 Lt. Square Vertical', 21),
(99, 21, 'SV', 'SAVE', NULL);

INSERT INTO items (ITEM_ID, LEVEL, CODE, ITEM_DESCRIPTION, NEXT_LEVEL) VALUES (99, 21, 'SV', 'SAVE', NULL);

INSERT INTO order_status
(ORDER_STATUS_DESC) VALUES 
('ORDER PLACED'),
('ORDER CANCELLED'),
('ORDER COMPLETED');


        SELECT  a.ORDER_ID, p.USER_ID, p.NAME `USER_NAME`,  
         a.CODE, s.ORDER_STATUS_ID, s.ORDER_STATUS_DESC, 
        a.ORDER_DATE FROM mo_order a LEFT JOIN mo_user p ON a.USER_ID=p.USER_ID 
           LEFT JOIN mo_order_status s ON a.ORDER_STATUS_ID=s.ORDER_STATUS_ID 
            WHERE TRUE 


UPDATE mo_order  SET CODE  WHERE ORDER_ID=orderId;