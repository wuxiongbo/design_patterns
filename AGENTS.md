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

## MCP 工具与优先级
- 优先使用 IntelliJ MCP 工具完成项目内操作，尤其是 `get_run_configurations`、`execute_run_configuration`、`get_file_problems`、`get_project_dependencies`、`get_project_modules`、`find_files_by_glob`、`find_files_by_name_keyword`、`get_all_open_file_paths`、`list_directory_tree`、`open_file_in_editor`、`reformat_file`、`get_file_text_by_path`、`replace_text_in_file`、`search_in_files_by_regex`、`search_in_files_by_text`、`get_symbol_info`、`rename_refactoring`、`execute_terminal_command`、`get_repositories`、`create_new_file`、`runNotebookCell`。
- 当需要运行配置时，先调用 `get_run_configurations` 获取名称，再用 `execute_run_configuration` 执行。

## 编码执行流程（通用）
- 【强制】任何代码改动（新增、重构、清理、导包、重命名）必须遵循“先分析、后修改、再验证”的流程，禁止盲改全量文件。
- 【强制】结构化修改必须优先使用 IDE/IntelliJ 语义级能力；禁止仅靠正则或纯文本脚本直接批量改代码（仅在语义级工具不可用时可例外）。
- 【强制】若使用脚本批处理，必须先产出“候选改动清单”并抽样核对，通过后再分批落盘，禁止一次性全量覆盖。
- 【强制】导包清理必须按“语义级检查优先”执行，禁止删除未完成引用校验的导包。
- 【强制】每次批量改动后必须立即编译校验；若 `mvn` 不可用，必须使用 IntelliJ MCP 的 `build_project`。
- 【强制】对报错文件必须逐个执行 `get_file_problems(errorsOnly=true)` 并修复，错误未清零前禁止进入下一轮改动。
- 【强制】同一轮任务内，已修复文件禁止再次被粗粒度批处理覆盖；若确需二次处理，必须先复核差异并记录原因。
- 【强制】交付前必须完成一次最终增量编译，并确认“无新增编译错误、无明显回归”。
- 【强制】除编译校验外，涉及专项治理任务时（如 `final` 化、导包清理、可见性统一），必须执行对应“规则复扫”并确认无遗漏，再允许交付。
- 【强制】“编译通过”不是专项治理任务的唯一完成条件；必须同时满足“规则命中清零或降至约定范围并说明例外”。
- 【交付门禁】只有在“最后一次代码改动之后”的编译校验成功，才允许输出“已完成/已修复/可交付”。
- 【交付门禁】若最终编译失败，只能反馈“未完成+失败原因+下一步修复动作”，禁止给出完成态结论。
- 【交付门禁】最终回复必须附带校验结论（成功/失败）以及所用校验命令或工具（如 `mvn -DskipTests compile` 或 `build_project`）。
- 【交付门禁】每次交付必须附“最后一次校验记录”，至少包含：校验工具/命令、执行时点（最后一次改动后）、校验结论。
- 【交付门禁】专项治理任务（如 `final` 化、导包清理、可见性统一）每次交付必须附“规则复扫结果”（命中数=0 或 例外清单+原因）。
- 【交付门禁】若用户指出任一编译错误，流程自动回退到“未完成态”；在再次校验成功前，禁止使用“已完成/已修复/可交付”措辞。
