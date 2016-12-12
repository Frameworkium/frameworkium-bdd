Frameworkium-BDD [![Build Status][status-svg]][status]
======================================================

This is a BDD version of [Frameworkium][frameworkium], it utilises 
[frameworkium-core][core] and is nearing first release.

## Getting Started

1. Clone this project `git clone https://github.com/Frameworkium/frameworkium-bdd.git
2. Create your own package for your project under `src/test/java`
3. Copy the `BrowserSetup.java` file from `com/google` to the root of your package
4. Create `glue` and `pages` packages like the current example code
5. Change `<glue>com.google.glue</glue>` in `pom.xml` to your new glue package
6. Create feature files in `src/test/resources/features`
7. Run tests as per [Frameworkium][frameworkium] with the addition of `-Dtags=mytag`

## TODO:
- [ ] expand documentation
- [x] improve capture test title
- [ ] open browsers only when required
- [ ] beta test

[status-svg]: https://travis-ci.org/Frameworkium/frameworkium-bdd.svg?branch=master
[status]: https://travis-ci.org/Frameworkium/frameworkium-bdd
[frameworkium]: https://github.com/Frameworkium/frameworkium
[core]: https://github.com/Frameworkium/frameworkium-core
