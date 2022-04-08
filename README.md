# Oak [![Build Status](https://travis-ci.org/smba/oak.svg?branch=master)](https://travis-ci.org/smba/oak)
**UPDATE: I no longer have the resources to maintain this project. -- This project was the result of a research internship in 2016. If you have questions, I compiled a paper manuscript I will make available upon request or as a preprint later. (April 2022)**

**Oak** is an output-oriented symbolic interpreter for the [PHP](http://php.net/) programming language. PHP scripts generate client page output dynamically, often depending on a number of program inputs including user interaction, database queries or file system access. This tool is inspired by the symbolic execution engine by [Symex](https://github.com/git1997/VarAnalysis) and additionally provides support for basic object-oriented programming and array support.

## Getting Started
### Download & Build
To build and run Oak, you will need
* Java 8+,
* Scala 2.11.8+,
* sbt 0.11+: [sbt](http://www.scala-sbt.org/index.html) for building the interpreter and resolving dependencies.

To obtain the tool, simply clone this repository ```git clone https://github.com/smba/oak.git``` and build the project by running sbt in the root directory of this repository ```sbt compile```.

The repository contains four projects:
* `oak`: Interpreter infrastructure including tests
* `quercus`: [Quercus](http://quercus.caucho.com/) is a pure-Java implementation of the PHP language, we basically reuse the parser and AST fragments
* `kernel` and `hessian` are dependencies for quercus

### Run Oak
To run Oak for a given PHP script file see ```edu.cmu.cs.oak.analysis.RunOakForFile.scala``` and specify input script file and output path for the approximated output, then compile and execute the file. The predefined example script file `schoolmate/index.php` is the main entry point for the [SchoolMate](https://sourceforge.net/projects/schoolmate/) application. 

The approximated output can either be exported as HTML/JS output spiced with preprocessor-like annotations (#ifdef, #else, #endif, ...) or as a VarDOM/XML model (DOM-like output extended with select nodes to express variants and corresponding conditions). 

## Editing Oak
To edit the interpreter [IntelliJ IDEA](https://www.jetbrains.com/idea/) or the [Scala IDE for Eclipse](http://scala-ide.org/) are recommended. You can import sbt projects directly into IntelliJ; for eclipse we use the plugin [sbteclipse](https://github.com/typesafehub/sbteclipse) to build eclipse projects. For Eclipse simply use ```sbt eclipse``` to build eclipse projects.

### Configuration (Eclipse only)
Additionally, if you might want to configure different variants of the interpreter, use the [Antenna](http://antenna.sourceforge.net/wtkpreprocess.php#eclipse_plugin) preprocessor. The eclipse plugin [FeatureIDE](http://wwwiti.cs.uni-magdeburg.de/iti_db/research/featureide/) might become handy when modeling additional features.
