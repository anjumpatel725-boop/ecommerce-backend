# Upgrade Plan: ecommerce-backend (20260628133508)

- **Generated**: 2026-06-28 13:35:08
- **HEAD Branch**: N/A
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 21.0.10: C:\Program Files\Java\jdk-21.0.10\bin (required by steps 1, 3, 5)
- JDK 17: not available (baseline step will be skipped)

**Build Tools**
- Maven 3.9.16: C:\Users\Anjum\maven\apache-maven-3.9.16-bin\apache-maven-3.9.16\bin
- Maven Wrapper: not present

## Guidelines

- Upgrade the project runtime to the latest LTS Java version (Java 21) with minimal functional change.
- Preserve Spring Boot 2.7.18 unless compatibility issues force a later Spring Boot patch or minor upgrade.

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260628133508
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java 21

## Technology Stack

| Technology/Dependency    | Current | Min Compatible | Why Incompatible |
| ------------------------ | ------- | -------------- | ---------------------------------------------- |
| Java                     | 17      | 21             | User requested runtime upgrade                 |
| Spring Boot              | 2.7.18  | 2.7.18         | Current parent; keep unless runtime issues appear |
| Maven                    | 3.9.16  | 3.9.0+         | Recommended for Java 21 compatibility          |
| maven-compiler-plugin    | managed | 3.11.0         | Recommended for reliable Java 21 source/target handling |
| ModelMapper              | 3.2.0   | 3.2.0          | No upgrade needed for Java 21 compatibility    |
| Lombok                   | managed | managed        | No change required                             |

## Derived Upgrades

- Java 21 requires a build tool capable of compiling source/target 21. Use Maven 3.9.16 and explicit `maven-compiler-plugin` 3.11.0.
- Current project is Maven-based with no wrapper, so system Maven 3.9.16 is the execution tool.
- Spring Boot 2.7.18 is retained because the user requested only a runtime upgrade and not a Spring Boot major upgrade.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|------------|---------|--------|--------|--------|
| pom.xml | `<java.version>` | 17 | upgrade | 21 | User requested Java runtime upgrade |
| pom.xml | `maven-compiler-plugin` | managed by Spring Boot | add | 3.11.0 | Ensure Java 21 compile support and explicit plugin version |
| pom.xml | `maven-compiler-plugin` configuration | `source=17`, `target=17` | update | `source=21`, `target=21` | Match project runtime target |

### Source Code Changes

| File | Location | Current | Required Change | Reason |
|------|----------|---------|----------------|--------|
| No source files require changes | N/A | N/A | N/A | Java runtime upgrade is orthogonal to the existing codebase; no API migration is required at this stage |

### Configuration Changes

| File | Property/Setting | Current | Required Change | Reason |
|------|------------------|---------|----------------|--------|
| No configuration files require changes | N/A | N/A | N/A | Runtime upgrade is handled through build configuration only |

### CI/CD Changes

| File | Location | Current | Required Change |
|------|----------|---------|----------------|
| No CI/CD files detected in repository | N/A | N/A | N/A |

### Risks & Warnings

- **No git repository**: Version control is unavailable. Changes will not be committed automatically and must be preserved manually.
- **Baseline JDK not installed**: Java 17 is not available locally, so the baseline compile/test step will be skipped.
- **Spring Boot 2.7.18 on Java 21**: This combination is expected to compile, but if JDK 21 compatibility issues appear, the next mitigation is to patch Spring Boot within 2.7.x or consider a later Spring Boot release.
- **No test sources present**: The repository contains `spring-boot-starter-test` but no `src/test` tree. Final validation may effectively be compile-only unless test files are added.

## Upgrade Steps

- Step 1: Setup Environment
  - Rationale: Ensure the required runtime and build tool are available before applying code changes.
  - Changes to Make: Confirm JDK 21 and Maven 3.9.16 are accessible on the local machine.
  - Verification: `mvn -version` with Java 21 active; expected result: Maven 3.9.16 and Java 21.

- Step 2: Setup Baseline
  - Rationale: Determine current compile/test state before upgrading, if possible.
  - Changes to Make: Attempt baseline validation with current project settings; skip if Java 17 is unavailable.
  - Verification: `mvn clean test-compile -q && mvn clean test -q`; expected result: baseline recorded or skipped.

- Step 3: Upgrade Java runtime to 21 in Maven build
  - Rationale: Apply the requested runtime target and ensure the compiler plugin supports the new JDK.
  - Changes to Make: Update `<java.version>` to `21`, set `maven-compiler-plugin` version to `3.11.0`, and update source/target to `21`.
  - Verification: `mvn clean test-compile -q`; expected result: compilation succeeded.

- Step 4: CVE Validation & Fix
  - Rationale: Scan direct dependencies for known vulnerabilities and apply minimal version fixes if needed.
  - Changes to Make: Run dependency scan, fix any reported CVEs through patch/minor upgrades, verify compile.
  - Verification: `mvn clean test-compile -q` after fixes; expected result: no CVEs remain for direct dependencies.

- Step 5: Final Validation
  - Rationale: Confirm the upgrade meets the runtime target and project verification requirements.
  - Changes to Make: Resolve any remaining compile or test failures from earlier steps.
  - Verification: `mvn clean test -q`; expected result: full success, or if no tests exist, compilation success with zero test failures.
