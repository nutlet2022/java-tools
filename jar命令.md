# Java-jar 命令

## 1、简介

> [jar (oracle.com)](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/jar.html) ：jar命令解析文档
>
> [Lesson: Packaging Programs in JAR Files (The Java™ Tutorials > Deployment) (oracle.com)](https://docs.oracle.com/javase/tutorial/deployment/jar/index.html)：学习引导

### 1.1 主要命令

操作 Jar 文件的主要命令，JAR：Java Archive，主要分为了以下几种：

1. 创建 JAR

   `jar c[efmMnv0] [entrypoint] [jarfile] [manifest] [-C dir] file ... [-Joption ...] [@arg-file ...]`

   > jar 创建option [入口点] [JAR 文件] [配置清单] 文件列表 ... [-Joption ...] [@arg-file ...]

2. 更新 JAR

   `jar u[efmMnv0] [entrypoint] [jarfile] [manifest] [-C dir] file ... [-Joption ...] [@arg-file ...]`

3. 解压 JAR

   `jar x[vf] [jarfile] file ... [-Joption ...] [@arg-file ...]`

4. 查看 JAR 文件内容

   `jar t[vf] [jarfile] file ... [-Joption ...] [@arg-file ...]`

5. 给 JAR 添加索引

   `jar i jarfile [-Joption ...] [@arg-file ...]`

### 1.2 详解

jar 命令的主要作用是压缩Java程序，结构化，方便下载

> The `jar` command is a general-purpose archiving and compression tool, based on ZIP and the ZLIB compression format. However, the `jar` command was designed mainly to package Java applets or applications into a single archive. When the components of an applet or application (files, images and sounds) are combined into a single archive, they can be downloaded by a Java agent (such as a browser) in a single HTTP transaction, rather than requiring a new connection for each piece. This dramatically improves download times. The `jar` command also compresses files, which further improves download time. The `jar` command also allows individual entries in a file to be signed by the applet author so that their origin can be authenticated. A JAR file can be used as a class path entry, whether or not it is compressed.

jar命令类似tar命令

> The syntax for the `jar` command resembles the syntax for the `tar` command. It has several operation modes, defined by one of the mandatory *operation arguments*. Other arguments are either *options* that modify the behavior of the operation, or *operands* required to perform the operation.

主要操作参数详解：

- c: create, Create a new JAR archive.
- i: index, Generate index information for a JAR archive.
- t: content, List the contents of a JAR archive.
- u: update, Update a JAR archive
- x: extrac, Extract files from a JAR archive.

主要操作/选项说明：

- e: entrypoint, Sets the class specified by the *entrypoint* operand to be the entry point for a standalone Java application bundled into an executable JAR file.
- f:  file, Sets the file specified by the *jarfile* operand to be the name of the JAR file. It maybe the formatting.
- m: manifest, Includes names and values of attributes from the file specified by the `manifest` operand in the manifest file of the `jar` command
- M: modify/move,  Does not create a manifest file entry (for `c` and `u`), or delete a manifest file entry when one exists (for `u`)
- n: normalizes, When creating (`c`) a JAR file, this option normalizes the archive so that the content is not affected by the packing and unpacking operations of the [`pack200`(1)](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/pack200.html#CHDIIIFE) command. Without this normalization, the signature of a signed JAR can become invalid.
- v: verbose, Generates verbose output to standard output. See [Examples](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/jar.html#CHDJAEBE).
- 0: zero, (Zero) Creates (`c`) or updates (`u`) the JAR file without using ZIP compression.
- **-C** *dir*, should read the document which is in the header
- **-J** *option*

主要操作数：

- *file*: file or directory, When creating (`c`) or updating (`u`) a JAR file, the *file* operand defines the path and name of the file or directory that should be added to the archive.
- *entrypoint*: entry ponint, The *entrypoint* operand must be specified if the `e` option is present.
- *jarfile*: jar file, Defines the name of the file to be created (`c`), updated (`u`), extracted (`x`), or viewed (`t`)
- *manifest*: When creating (`c`) or updating (`u`) a JAR file, the *manifest* operand defines the preexisting manifest files with names and values of attributes to be included in `MANIFEST.MF` in the JAR file.The *manifest* operand must be specified if the `f` option is present.
- *@arg-file*: should read the document

## 2、常用参数及命令

### 2.1 常用参数

```bash
用法: jar {ctxui}[vfmn0PMe] [jar-file] [manifest-file] [entry-point] [-C dir] files ...
选项:
    -c  创建新档案
    -t  列出档案目录
    -x  从档案中提取指定的 (或所有) 文件
    -u  更新现有档案
    -v  在标准输出中生成详细输出
    -f  指定档案文件名
    -m  包含指定清单文件中的清单信息
    -n  创建新档案后执行 Pack200 规范化
    -e  为捆绑到可执行 jar 文件的独立应用程序
        指定应用程序入口点
    -0  仅存储; 不使用任何 ZIP 压缩
    -P  保留文件名中的前导 '/' (绝对路径) 和 ".." (父目录) 组件
    -M  不创建条目的清单文件
    -i  为指定的 jar 文件生成索引信息
    -C  更改为指定的目录并包含以下文件
如果任何文件为目录, 则对其进行递归处理。
清单文件名, 档案文件名和入口点名称的指定顺序
与 'm', 'f' 和 'e' 标记的指定顺序相同。

示例 1: 将两个类文件归档到一个名为 classes.jar 的档案中:
       jar cvf classes.jar Foo.class Bar.class
示例 2: 使用现有的清单文件 'mymanifest' 并
           将 foo/ 目录中的所有文件归档到 'classes.jar' 中:
       jar cvfm classes.jar mymanifest -C foo/ .
```

### 2.2 常用命令

```bash
# 打包
jar cvef <main所在类的路径：path/Main 或 主类]> <jar包文件名> <打包文件列表，包含文件夹、文件，支持*.filetype>
如：
jar cvef dependonother/StandardPrint  dependonother.jar dependonother
jar cvef dependonother.StandardPrint  dependonother.jar dependonother

jar cvemf <main所在类的路径：path/Main 或 主类]> <MANIFEST清单> <jar包文名> <打包文件列表，包含文件夹、文件，支持*.filetype>
如：
jar cvemf dependonother/StandardPrint MANIFEST dependonother.jar dependonother

# 解压
jar x[vf] [jarfile]
如：
jar xvf dependonother.jar

# 查看
jar tf <jar包文件名>
如：
jar tf dependonother.jar
```

## 3、实践

### 3.1 单个 java 文件，无package声明，无三方或者二方依赖

#### 3.1.1 源文件

文件 `StandardPrint.java`，路径 `D:\Projects\JavaProjects\java-tools\singlefile\StandardPrint.java`

```java
public class StandardPrint {
    public static void main(String[] args) {
        System.out.println("standard print");
    }
 }
```

#### 3.1.2 编译运行

打包时需要注意 `e f m`三个参数的顺序情况，后续的文件参数需要一一对应

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\singlefile\
javac -cp  D:\Projects\JavaProjects\java-tools -s D:\Projects\JavaProjects\java-tools\singlefile StandardPrint.java

# 打包
cd D:\Projects\JavaProjects\java-tools\singlefile
jar cvef StandardPrint singlefile.jar StandardPrint.class

# 运行
cd D:\Projects\JavaProjects\java-tools\singlefile\
# 与 java StandardPrint 
java -jar singlefile.jar
```

### 3.2 单个 java 文件，有package声明，无三方或者二方依赖

#### 3.2.1 源文件

文件 `StandardPrint.java`，路径 `D:\Projects\JavaProjects\java-tools\singlefilepackage\StandardPrint.java`

```java
package singlefilepackage;

public class StandardPrint {
    public static void main(String[] args) {
        System.out.println("standard print");
    }
 }
```

#### 3.2.2 编译运行

打包时指定 classpath sourcepath destinationpath

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\singlefilepackage\
javac  -cp D:\Projects\JavaProjects\java-tools -sourcepath D:\Projects\JavaProjects\java-tools\singlefilepackage -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 打包
cd D:\Projects\JavaProjects\java-tools\destination
jar cvef singlefilepackage.StandardPrint singlefilepackage.jar singlefilepackage

# 运行
cd D:\Projects\JavaProjects\java-tools\destination
java -jar singlefilepackage.jar
```

### 3.3 多个 java 文件，有package声明，无三方或者二方依赖，无跨包引用

#### 3.3.1 源文件

文件 `StandardImport.java`，路径 `D:\Projects\JavaProjects\java-tools\multiplefiles\StandardImport.java`

```java
package multiplefiles;

public class StandardImport {
    public void StandardImportPrint() {
        System.out.println("standard import print");
    }
 }
```

文件 `StandardPrint.java`，路径 `D:\Projects\JavaProjects\java-tools\multiplefiles\StandardPrint.java`

```java
package multiplefiles;

public class StandardPrint {
    public static void main(String[] args) {
        StandardImport standardImport = new StandardImport();
        standardImport.StandardImportPrint();
        System.out.println("standard print");
    }
 }
```

#### 3.2.2 编译运行

编译打包的基础命令同上

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\multiplefiles\
javac  -cp D:\Projects\JavaProjects\java-tools  -sourcepath D:\Projects\JavaProjects\java-tools -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 打包
cd D:\Projects\JavaProjects\java-tools\destination
jar cvef multiplefiles.StandardPrint multiplefiles.jar multiplefiles

# 运行
cd D:\Projects\JavaProjects\java-tools\destination
java -jar multiplefiles.jar
```

> 编译java源文件时，未被引用的类应该先编译，依赖其他类的类需要引入依赖类的编译后类路径，使用 -cp 指定或将编译后的字节码文件放至classpath

### 3.4 多个 java 文件，有package声明，无三方或者二方依赖，跨包引用

#### 3.4.1 源文件

文件 `StandardImport.java`，路径 `D:\Projects\JavaProjects\java-tools\multiplefilesother\other\StandardImport.java`

```java
package multiplefilesother.other;

public class StandardImport {
    public void StandardImportPrint() {
        System.out.println("standard import print");
    }
 }
```

文件 `StandardPrint.java`，路径 `D:\Projects\JavaProjects\java-tools\multiplefilesother\StandardPrint.java`，依赖跨包的`StandardImport.java`

```java
package multiplefilesother;

public class StandardPrint {
    public static void main(String[] args) {
        StandardImport standardImport = new StandardImport();
        standardImport.StandardImportPrint();
        System.out.println("standard print");
    }
 }
```

#### 3.4.2 编译运行

编译方式：`java -cp <classpath列表，当前项目根路径> -s <源文件路径> -d <编译后字节码存放路径> Main.java`，被依赖的类应已编译，存放于 `classpath` 或手动指定已编译文件存放路径

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\multiplefilesother\
javac  -cp D:\Projects\JavaProjects\java-tools  -sourcepath D:\Projects\JavaProjects\java-tools -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 打包
cd D:\Projects\JavaProjects\java-tools\destination
jar cvef multiplefilesother.StandardPrint multiplefilesother.jar multiplefilesother

# 运行
cd D:\Projects\JavaProjects\java-tools\destination
java -jar multiplefilesother.jar
```

### 3.5 多个/单个 java 文件，有package声明，有三方或者二方依赖

#### 3.5.1 源文件

文件 `StandardImport.java`，路径 `D:\Projects\JavaProjects\java-tools\dependonother\StandardPrint.java`，依赖 `log4j2`

```java
package dependonother;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.Logger;


public class StandardPrint {
    private static final Logger LOGGER = LogManager.getLogger(StandardPrint.class);
    public static void main(String[] args) {
        LOGGER.info("log4j2 print");
        System.out.println("standard print");
    }
 }
```

依赖版本如下，jar 包存放于 `D:\Projects\JavaProjects\java-tools\classpath`

```xml
<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.13.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.13.2</version>
</dependency>

```

#### 3.5.2 编译运行

编译方式：`java -cp <classpath列表，当前项目根路径> -s <源文件路径> -d <编译后字节码存放路径> Main.java`，被依赖的类应已编译，存放于 `classpath` 或手动指定已编译文件存放路径

> MANIFEST 是文本文件，其中设置了classpath参数，指定依赖jar包位置
>
> ```properties
> Class-Path: ../classpath/log4j-api-2.13.2.jar ../classpath/log4j-core-2.13.2.jar
> ```

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\dependonother\
javac  -cp D:\Projects\JavaProjects\java-tools\classpath\*  -sourcepath D:\Projects\JavaProjects\java-tools -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 打包
cd D:\Projects\JavaProjects\java-tools\destination
jar cvemf dependonother.StandardPrint MANIFEST dependonother.jar dependonother 

# 运行
cd D:\Projects\JavaProjects\java-tools\destination
java -jar dependonother.jar
```

> 此处未配置 `log4j2.xml` 文件，控制台未输出设置的日志

## 4、其他

### 4.1 classpath 与 -sourcepath 源文件目录引起的歧义

有文件：`D:\Projects\JavaProjects\java-tools\singlefile\StandardPrint.java`

```java
package multiplefiles;

public class StandardImport {
    public void StandardImportPrint() {
        System.out.println("standard import print");
    }
 }
```

使用以下命令打包:

```bash
cd D:\Projects\JavaProjects\java-tools\singlefile\
javac -cp  D:\Projects\JavaProjects\java-tools StandardPrint.java
```

在目录 `D:\Projects\JavaProjects\java-tools\singlefile` 中生成了 `StandardPrint.class`文件，运行时报错：

```bash
# 运行
cd D:\Projects\JavaProjects\java-tools\singlefile\
java StandardPrint

# 报错
Error: A JNI error has occurred, please check your installation and try againException in thread "main" java.lang.ClassFormatError: Incompatible magic value 1347093252 in class file StandardPrint

        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
        at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
        at java.net.URLClassLoader.defineClass(URLClassLoader.java:468)
        at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
        at java.security.AccessController.doPrivileged(Native Method)
        at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
```

初步判断是 `classpath`参数设置问题，后使用以下命令打包后执行编译后文件无错误

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\singlefile\
javac -cp  D:\Projects\JavaProjects\java-tools -s D:\Projects\JavaProjects\java-tools\singlefile StandardPrint.java

# 编译二
cd D:\Projects\JavaProjects\java-tools\singlefile\
javac -cp  D:\Projects\JavaProjects\java-tools\singlefile StandardPrint.java

```

### 4.2 jar 文件中有依赖第三方包情况

#### 4.2.1 问题描述

文件 `StandardImport.java`，路径 `D:\Projects\JavaProjects\java-tools\dependonother\StandardPrint.java`，依赖 `log4j2`

```java
package dependonother;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.Logger;


public class StandardPrint {
    private static final Logger LOGGER = LogManager.getLogger(StandardPrint.class);
    public static void main(String[] args) {
        LOGGER.info("log4j2 print");
        System.out.println("standard print");
    }
 }
```

使用以下命令编译打包执行

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\dependonother\
javac  -cp D:\Projects\JavaProjects\java-tools\classpath\*  -sourcepath D:\Projects\JavaProjects\java-tools -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 打包
cd D:\Projects\JavaProjects\java-tools\destination
jar cvef dependonother.StandardPrint dependonother.jar dependonother

# 运行
cd D:\Projects\JavaProjects\java-tools\destination
java -jar dependonother.jar
```

报错，没有找到指定依赖类的问题，判断是 classpath 参数未指定

```java
Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/logging/log4j/LogManager
        at dependonother.StandardPrint.<clinit>(StandardPrint.java:8)1
Caused by: java.lang.ClassNotFoundException: org.apache.logging.log4j.LogManager
        at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
        ... 1 more
```

#### 4.2.2 指定 classpath

运行时尝试指定 classpath，仍然存在找不到依赖类的问题，猜测 classpath 参数未被识别，或已经识别但是路径不正确；

```bash
# 运行
cd D:\Projects\JavaProjects\java-tools\destination
java -jar dependonother.jar

# 报错：仍然没有找到主类
Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/logging/log4j/LogManager
        at dependonother.StandardPrint.<clinit>(StandardPrint.java:8)
Caused by: java ......
```

故编写清单文件`MANIFEST`，并在清单中添加`Class-Path`项，使用绝对路径表示

```properties
# MANIFEST 清单配置 ClassPath，依赖的三方jar包存放位置
Class-Path: D:\Projects\JavaProjects\java-tools\classpath\log4j-api-2.13.2.jar D:\Projects\JavaProjects\java-tools\classpath\log4j-core-2.13.2.jar
```

编译打包后运行，其中 classpath 为依赖jar包存放位置

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\dependonother\
javac  -cp D:\Projects\JavaProjects\java-tools\classpath\*  -sourcepath D:\Projects\JavaProjects\java-tools -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 打包
cd D:\Projects\JavaProjects\java-tools\destination
jar cvemf dependonother.StandardPrint MANIFEST dependonother.jar dependonother 

# 运行
cd D:\Projects\JavaProjects\java-tools\destination
java -jar dependonother.jar
```

报错找不到主类

```bash
错误: 找不到或无法加载主类 dependonother.StandardPrint
```

继续猜测是classpath设置问题，在清单文件`MANIFEST`中使用`/`替换`\`，仍存在找不到主类问题，继续搜索相关问题解决方式，在阅读官方打包依赖第三方jar文件指导：[Adding Classes to the JAR File's Classpath (The Java™ Tutorials > Deployment > Packaging Programs in JAR Files) (oracle.com)](https://docs.oracle.com/javase/tutorial/deployment/jar/downman.html) 以及搜索找不到主类的相关问题 [java - Could not find or load main class with a Jar File - Stack Overflow](https://stackoverflow.com/questions/13030675/could-not-find-or-load-main-class-with-a-jar-file) 后，发现以下评论，使用绝对路径配置classpath会导致找不到主类问题出现：

> I'd like to remark a point implicit in the answer: it's important to "cd" to the path which would have been used as the classpath was TestClass.class ran without a jar file. For instance, "cd /MyEclipseWorkspace/MyProject/bin" **If, instead, jar uses the absolute path of the class files it may fail with "could not find or load main class" error.** For instance, this is wrong: jar cfm test.jar manifest.txt /absolutepath/classes/TestClass.class 

在`MANIFEST`修改ClassPath，**使用相对路径表示，并在修改后再次编译打包运行后，成功输出，问题解决**

```properties
Class-Path: ../classpath/log4j-api-2.13.2.jar ../classpath/log4j-core-2.13.2.jar
```

> 猜测 MANIFEST 中使用相对路径指定classpath或者不指定classpath，会把当前路径的jar包标识为classpath，jar包中的函数也得以运行，以上问题应该与 java 中的类加载机制有关

