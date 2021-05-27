/*
platform_nacos、platform_seata、platform_sw、platform_zipkin是第三方组件的库， 分别为：nacos、seata、SkyWalking、zipkin 服务端需要的库

platform_none、platform_activiti、platform_column、platform_defaults、platform_base_0000、platform_extend_0000 是platform-cloud项目需要的库
 */
-- none 模式
CREATE DATABASE IF NOT EXISTS `platform_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_seata` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_zipkin` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS `platform_none` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_activiti` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- column 模式
CREATE DATABASE IF NOT EXISTS `platform_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_seata` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_zipkin` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS `platform_column` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_activiti` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- schema 模式、datasource模式 （暂不支持工作流）
CREATE DATABASE IF NOT EXISTS `platform_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_seata` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_zipkin` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS `platform_defaults` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_base_0000` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `platform_extend_0000` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


