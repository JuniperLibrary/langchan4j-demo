-- ============================
-- 1. 清理旧表
-- ============================
DROP TABLE IF EXISTS `appointment`;
DROP TABLE IF EXISTS `doctor_schedule`;

-- ============================
-- 2. 创建医生排班表
-- ============================
CREATE TABLE `doctor_schedule` (
                                   `id` BIGINT NOT NULL AUTO_INCREMENT,
                                   `doctor_name` VARCHAR(50) NOT NULL,
                                   `department` VARCHAR(50) NOT NULL,
                                   `date` VARCHAR(10) NOT NULL,     -- yyyy-MM-dd
                                   `time` ENUM('上午','下午') NOT NULL,
                                   `total_slots` INT NOT NULL,      -- 总号源数
                                   `available_slots` INT NOT NULL,  -- 剩余可用号源数
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================
-- 3. 创建预约表
-- ============================
CREATE TABLE `appointment` (
                               `id` BIGINT NOT NULL AUTO_INCREMENT,
                               `username` VARCHAR(50) NOT NULL,
                               `id_card` VARCHAR(18) NOT NULL,
                               `department` VARCHAR(50) NOT NULL,
                               `date` VARCHAR(10) NOT NULL,
                               `time` ENUM('上午','下午') NOT NULL,
                               `doctor_name` VARCHAR(50) DEFAULT NULL,
                               `status` ENUM('已预约','已取消','已完成') DEFAULT '已预约',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================
-- 4. 插入医生排班数据
-- ============================
INSERT INTO `doctor_schedule` (doctor_name, department, date, time, total_slots, available_slots) VALUES
                                                                                                      ('张三', '心内科', '2025-09-01', '上午', 5, 5),
                                                                                                      ('张三', '心内科', '2025-09-01', '下午', 5, 5),
                                                                                                      ('李四', '心内科', '2025-09-01', '上午', 3, 3),
                                                                                                      ('王五', '心内科', '2025-09-01', '下午', 4, 4),
                                                                                                      ('赵六', '骨科',  '2025-09-01', '上午', 6, 6),
                                                                                                      ('钱七', '骨科',  '2025-09-01', '下午', 6, 6);

-- ============================
-- 5. 插入一些模拟预约数据（模拟有人已经挂号）
-- ============================
INSERT INTO `appointment` (username, id_card, department, date, time, doctor_name, status) VALUES
                                                                                               ('小明', '110101199001010011', '心内科', '2025-09-01', '上午', '张三', '已预约'),
                                                                                               ('小红', '110101199202022222', '心内科', '2025-09-01', '上午', '张三', '已预约');

-- 同步扣减号源（张三上午总 5 个，现在已占 2 个，剩余 3 个）
UPDATE `doctor_schedule`
SET available_slots = available_slots - 2
WHERE doctor_name = '张三' AND department = '心内科' AND date = '2025-09-01' AND time = '上午';
