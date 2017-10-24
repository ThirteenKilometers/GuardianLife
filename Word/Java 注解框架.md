#Java 注解框架
----

###一、元注解
所谓的元注解就是注解就是注解的注解。Java提供了4个元注解  

*  **1、@Target：**用于描述注解的使用范围，如果自定义注解不存在**@Target**，则表示该注解可以使用在任何程序元素之上。接收参数**ElementType**，其值如下：
  
```java

		ElementType.TYPE  //接口、类、枚举、注解

		ElementType.FIELD //成员变量、对象、属性、字段、枚举的常量

		ElementType.METHOD //方法
		
		ElementType.PARAMETER //方法中参数
		
		ElementType.CONSTRUCTOR //构造方法

		ElementType.LOCAL_VARIABLE //局部变量
		
		ElementType.ANNOTATION_TYPE //注解
		
		ElementType.PACKAGE // 包

		ElementType.TYPE_PARAMETER //表示该注解能写在类型变量的声明语句中。Java8 新增
		
		ElementType.TYPE_USE //表示该标注能写在使用类型的任何语句总。Java8新增
		
```

*  **2、 @Retention：**表示注解类型保留的时长，它接收的参数**RetentionPolicy**参数，其值如下：

```java

		/**注解仅存于源码中，在编译阶段丢失。这些注解在编译结束之后就不在有任何意义，所以它们不会写入字节码,@Override、@SuppressWarnings都属于这类注释**/
		RetentionPolicy.SOURCE
		/**默认的保留策略，注解会在Class字节码文件中存在，但是运行时无法获得。**/
		RetentionPolicy.CLASS
		/**该方式始终不会丢弃。运行期也保留盖住是，因此可以使用反射机制读取该注解的信息。**/
		RetentionPolicy.RUNTIME

```

*  **3、@Documeted:**表示注解可以出现在JavaDoc中,一个简单的Annotations标记注解，表示是否将注解信息添加在java文档中。

*  **4、@Inherited:**表示注解可以被子类继承，定义该注释和子类的关系。  
   @Inherited  元注解是一个标记注解，@Inherited阐述了某个被标记的类型是被集成的。如果一个使用了@Inherited修饰的Annotation类型被用于一个class，则这个Annotation将被用于该class的子类。
   
   
###二、常见标准的Annotation

*  **1、Override：** java.lang.Override是一个标记类型注解，它被用作标注方法。
   


###Annotation Processor Tool 
**Annotation Processor Tool** 是用于编译期扫面和处理注解的工具，目前被集成在**javac**中，在编译的时候，**javac**通常会找到你定义的注解处理器，并执行注解处理。

不过遗憾的是**Android Studio** 默认是不支持注解处理器的，我们需要引入一个额外的**Gradle**插件，**android-apt**这个插件的功能是：允许配置只在编译时作为注解处理器的依赖，而不添加到最后的**APK**或**Library**；设置源路径，使注解处理器生成的代码能被**Android Studio**正确的引用。

###AbstractProcessor
**AbstractProcessor**是**javac**扫描和处理的关键类，所有自定义的**Processor**都是继承自**AbstractProcessor**，一个基本的**Processor**结构如下所示

```java

public class SimpleProcessor extends AbstractProcessor{

	/**
	 * 每个注解处理器都必须有一个无参构造方法。
	 * init方法是在Processor创建时被javac调用并执行初始化操作。
	 * @param processingEnv 提供一系列的注解处理工具
	 **/
	 @Override
	 public synchronized void init(ProcessingEnvironment env){}
	 
	 /**
	  * 注解处理需要执行一次或者多次。每次执行时，处理器方法被调用，并且传入了当前要处理的注解类型。
	  * 可以在这个方法中扫描和处理注解，并生成Java代码
	  * @param annotations 当前要处理的注解类型
	  * @param roundEnv 这个对象提供当前或者上一次注解处理中被注解标注的源文件元素。（获得所有被标注的元素）
	  **/
	 @Override
	 public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env){}
	 
	 
	 /**
	  *注解处理器要处理的注解类型，值为完全限定名（就是带所在包名和路径的类的全名）
	  **/
	 @Override
	 public Set<String> getSupportedAnnotionTypes(){}
	 
	 /**
	  * 指定支持的Java版本，通常返回 SourceVersion.latestSupported
	  **/
	 @Override
	 public SourceVersion getSupportedSourceVersion(){}

}

```
有一点需要注意，**Android Library** 中去除了**javax**包中的部分功能，所以在新建的**Module**的时候不能选在**Android Library**，需要使用**Java Library**。



















