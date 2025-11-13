# 仓库指南

## 项目结构和模块组织

- 基于 Maven 的 Spring Boot 项目，目标平台为 Java 17；核心源代码位于 `src/main/java` 目录下。

- 模式演示位于 `design_patterns.chapter48` 和 `my_demo` 等包中，重构案例研究位于 `refactoring` 包中。

- 共享资源（例如 Spring 配置，如 `src/main/resources/beans.xml`）位于 `src/main/resources` 目录下；说明性文档位于 `doc/` 文件夹下。

- 测试包与主包位于 `src/test/java` 目录下，保持 1:1 的文件夹结构，以便快速导航。

## 构建、测试和开发命令

- `mvn clean verify` 执行全新构建，执行所有单元测试，并验证打包。

- `mvn spring-boot:run` 启动当前示例应用程序，以便进行交互式模式探索。

- `mvn test` 提供快速的反馈循环；仅在生成无需执行的工件时才将其与 `-DskipTests` 结合使用。

## 编码风格和命名约定

- 使用 4 个空格缩进，成员名称使用 `lowerCamelCase`，类名称使用 `UpperCamelCase`；包名保持小写，并按章节分组（例如，`design_patterns.chapter60.strategy`）。

- 优先使用能够体现模式角色的表达性类名（例如，`ProxyClient`、`StrategyContext`）；使新示例与现有章节编号保持一致。

- Lombok 可用但可选——请谨慎使用注解，并记录任何影响协作者的生成方法。

- Maven 没有集成任何格式化插件；请依赖 IntelliJ 的默认 Java 配置，并在格式化后运行 `mvn -DskipTests compile` 以确认代码可以顺利编译。

## 测试指南

- 测试依赖于 Spring Boot Starter Test（JUnit 5）。测试类命名为 `<Pattern>Test`（例如，`ObserverDemoTest`），并将其放置在 `src/test/java` 目录下对应的包名中。

- 尽量使用以行为为中心的断言来演示模式交互；必要时，将 fixtures 放在 `support` 子包内。

- 在提交 PR 之前运行 `mvn test`，并在 PR 描述中记录任何有意跳过的场景。

## 提交和拉取请求指南

- 历史记录尽量使用简洁的现在时中文动词（例如，`更新`、`补充`）；修改特定模块时，保留主语前缀，例如 `chapter60:`。

- 如果行为变更较为复杂，请在提交正文中详细说明变更理由，并在有 issue ID 的情况下引用 issue ID。

- 拉取请求应总结受影响的章节，列出验证命令（`mvn clean verify`），并且仅当图表或 UI 资源更新时才附上屏幕截图。

## other
- 对话以及分析过程均使用中文
- 当我说 【分析xxxx】或者【总结xxxx】的时候，请生成xxxx.md文件报告，这里的xxxx 根据主题命名。