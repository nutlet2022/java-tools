# Java-javac命令

## 1、简介

> [javac (oracle.com)](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html#BHCGAJDC)

命令格式：`javac [option] [sourcefiles] [classes] [@argfiles]`

options: 指令选项

sourcefiles: java 源文件

classes：一个或多个类进行注解处理，

@argfiles: 列出选项和源文件的一个或多个文件，这些文件中不允许使用 `-J` 选项

## 2、常用选项

```bash
  -g                         生成所有调试信息
  -g:none                    不生成任何调试信息
  -g:{lines,vars,source}     只生成某些调试信息
  -nowarn                    不生成任何警告
  -verbose                   输出有关编译器正在执行的操作的消息
  -deprecation               输出使用已过时的 API 的源位置
  -classpath <路径>            指定查找用户类文件和注释处理程序的位置
  -cp <路径>                   指定查找用户类文件和注释处理程序的位置
  -sourcepath <路径>           指定查找输入源文件的位置
  -bootclasspath <路径>        覆盖引导类文件的位置
  -extdirs <目录>              覆盖所安装扩展的位置
  -endorseddirs <目录>         覆盖签名的标准路径的位置
  -proc:{none,only}          控制是否执行注释处理和/或编译。
  -processor <class1>[,<class2>,<class3>...] 要运行的注释处理程序的名称; 绕过默认的搜索进程
  -processorpath <路径>        指定查找注释处理程序的位置
  -parameters                生成元数据以用于方法参数的反射
  -d <目录>                    指定放置生成的类文件的位置
  -s <目录>                    指定放置生成的源文件的位置
  -h <目录>                    指定放置生成的本机标头文件的位置
  -implicit:{none,class}     指定是否为隐式引用文件生成类文件
  -encoding <编码>             指定源文件使用的字符编码
  -source <发行版>              提供与指定发行版的源兼容性
  -target <发行版>              生成特定 VM 版本的类文件
  -profile <配置文件>            请确保使用的 API 在指定的配置文件中可用
  -version                   版本信息
  -help                      输出标准选项的提要
  -A关键字[=值]                  传递给注释处理程序的选项
  -X                         输出非标准选项的提要
  -J<标记>                     直接将 <标记> 传递给运行时系统
  -Werror                    出现警告时终止编译
  @<文件名>                     从文件读取选项和文件名
```

## 3、classpath extdirs

> 关联类加载机制 `AppClassLoader` `BootStrapClassLoader` `ExtClassLoader`

## 4、javac 编译

### 4.1 单个 java 文件，无package声明，无三方或者二方依赖

#### 4.1.1 源文件

文件 `StandardPrint.java`，路径 `D:\Projects\JavaProjects\java-tools\singlefile\StandardPrint.java`

```java
public class StandardPrint {
    public static void main(String[] args) {
        System.out.println("standard print");
    }
 }
```

#### 4.1.2 编译运行

方式一：不指定编译后路径，**编译后字节码文件与源文件处于同一路径**

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\singlefile\
javac StandardPrint.java

# 运行
cd D:\Projects\JavaProjects\java-tools\singlefile\
java StandardPrint
```

> 注：java 文件编译后是`.class` 后缀字节码文件 ，运行编译后程序需要指定类名

方式二：指定编译后字节码存放路径，存放路径需要提前创建；`-d` 可指定相对路径和绝对路径

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\singlefile\
javac -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 运行
cd D:\Projects\JavaProjects\java-tools\destination\
java StandardPrint
```

方式三：指定编译源文件存放路径，源文件已存在，此 -s 参数不起作用

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\singlefile\
javac -sourcepath D:\Projects\JavaProjects\java-tools\singlefile StandardPrint.java
# 或
cd D:\Projects\JavaProjects\java-tools\singlefile\
javac -s D:\Projects\JavaProjects\java-tools\singlefile StandardPrint.java

# 运行
cd D:\Projects\JavaProjects\java-tools\singlefile\
java StandardPrint
```

### 4.2 单个 java 文件，有package声明，无三方或者二方依赖

#### 4.2.1 源文件

文件 `StandardPrint.java`，路径 `D:\Projects\JavaProjects\java-tools\singlefilepackage\StandardPrint.java`

```java
package singlefilepackage;

public class StandardPrint {
    public static void main(String[] args) {
        System.out.println("standard print");
    }
 }
```

#### 4.2.2 编译运行

方式一：不指定编译后路径，**编译后字节码文件与源文件处于同一路径**

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\singlefilepackage\
javac StandardPrint.java

# 运行
cd D:\Projects\JavaProjects\java-tools\
java singlefilepackage.StandardPrint
```

> 注：java 文件编译后是`.class` 后缀字节码文件 ，运行编译后程序需要指定类名，包括 package 与 类名，带有包名的类执行时，java 查找规则从当前路径查找，如执行 `singlefile.StandardPint`  会查找 `./singlefile/StandardPrint.class`

方式二：指定编译后字节码存放路径，存放路径需要提前创建；`-d` 可指定相对路径和绝对路径

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\singlefilepackage\
javac -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 运行
cd D:\Projects\JavaProjects\java-tools\destination\
java singlefilepackage.StandardPrint
```

方式三：指定编译源文件存放路径，源文件已存在，此 -s 参数不起作用

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\singlefilepackage\
javac -sourcepath D:\Projects\JavaProjects\java-tools\singlefilepackage StandardPrint.java
# 或
cd D:\Projects\JavaProjects\java-tools\singlefilepackage\
javac -s D:\Projects\JavaProjects\java-tools\singlefilepackage StandardPrint.java

# 运行
cd D:\Projects\JavaProjects\java-tools\
java singlefilepackage.StandardPrint
```

### 4.3 多个 java 文件，有package声明，无三方或者二方依赖，无跨包引用

#### 4.3.1 源文件

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

#### 4.2.2 编译运行

此处编译运行指定了编译后字节码存放位置，后续编译运行会采用以下方式或思路：编译方式：`java -cp <classpath列表，当前项目根路径>-s <源文件路径> -d <编译后字节码存放路径> Main.java`，被依赖的类应已编译，存放于 `classpath` 或手动指定已编译文件存放路径

```bash
# 编译一：不建议
cd D:\Projects\JavaProjects\java-tools\multiplefiles\
javac -d D:\Projects\JavaProjects\java-tools\destination\ StandardImport.java StandardPrint.java

# 编译二：建议
cd D:\Projects\JavaProjects\java-tools\multiplefiles\
javac  -cp D:\Projects\JavaProjects\java-tools -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 运行
cd D:\Projects\JavaProjects\java-tools\destination\
java multiplefiles.StandardPrint
```

> 编译java源文件时，未被引用的类应该先编译，依赖其他类的类需要引入依赖类的编译后类路径，使用 -cp 指定或将编译后的字节码文件放至classpath

### 4.4 多个 java 文件，有package声明，无三方或者二方依赖，跨包引用

#### 4.4.1 源文件

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

#### 4.4.2 编译运行

编译方式：`java -cp <classpath列表，当前项目根路径>-s <源文件路径> -d <编译后字节码存放路径> Main.java`，被依赖的类应已编译，存放于 `classpath` 或手动指定已编译文件存放路径

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\multiplefilesother\
javac -cp D:\Projects\JavaProjects\java-tools -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 运行
cd D:\Projects\JavaProjects\java-tools\destination\
java multiplefiles.StandardPrint
```

### 4.5 多个/单个 java 文件，有package声明，有三方或者二方依赖

#### 4.5.1 源文件

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

#### 4.5.2 编译运行

编译方式：`java -cp <classpath列表，当前项目根路径>-s <源文件路径> -d <编译后字节码存放路径> Main.java`，被依赖的类应已编译，存放于 `classpath` 或手动指定已编译文件存放路径

```bash
# 编译
cd D:\Projects\JavaProjects\java-tools\dependonother
javac -cp 'D:\Projects\JavaProjects\java-tools;D:\Projects\JavaProjects\java-tools\classpath\*'  -d D:\Projects\JavaProjects\java-tools\destination StandardPrint.java

# 运行
cd D:\Projects\JavaProjects\java-tools\destination\
java -cp '.;D:\Projects\JavaProjects\java-tools\classpath\*' dependonother.StandardPrint
```

> 此处未配置 `log4j2.xml` 文件，控制台未输出设置的日志

## 其他

参考资料：[javac (oracle.com)](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html)

主要知识点：

1. 依赖其他类时，这个类需要提前被编译，因此需要将类编译后添加至classpath
2. 依赖二/三方包时，同样需要将jar包放至classpath或者手动指定存放路径

问题遗留：

1. jar包打包后没有找到主类，jar 打包的方式，这个此时不应该关注，后续应该学习jar打包命令
2. java 执行编译有的程序主类时，如何替换配置文件，后续应该学习 java 命令

