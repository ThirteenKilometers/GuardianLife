#Java 反射机制
----
###1、什么是Java反射机制
Java反射机制是在运行状态中，对任意一个类，都能够知道这个类的所有属性和方法；对任意一个对象，都能够调用它的任意一个方法；这种动态获取的以及动态调用对象的方法的功能称之为Java的反射机制

###2、Java 的反射机制踢动了那些功能
*  在运行时判定任意一个对象所属的的类  
*  在运行时构造任意一个类的对象
*  在运行时判定任意一个类所具有的成员变量和方法
*  在运行时调用任意个对象的方法
*  生成动态代理

###3、Java 反射机制类
```java
	java.lang.Class;//类
	java.lang.reflect.Constructor;//构造方法
	java.lang.reflect.Field;//类的成员变量
	java.lang.reflect.Method;//类的方法
	java.lang.reflect.Modefer;//访问权限
```

###4、Java反射机制的实现
####1）、class对象的获取
```java
	//第一种方式 通过对象getClassf方法
	Preson preson =new Preson();
	Class<?> class1=preson.getClass();
	
	//第二种方式 通过类的class属性
	class1=Preson.class;
	
	//第三种方式 通过Class类的静态方法---forName()来实现
	try{
		class1=Class.forName("com.lzhs.Preson");
	}catch(ClassNotFoundException ex){
		ex。printStackTrace();
	}
```
####2)、获取class对象的摘要信息
```java
	boolean isPrimitivie = class1.isPrimitive();/判断是都是基本类型
	boolean isArray = class1.isArray();//判断是否是集合类
	boolean isAnnotation =class1.isAnnotation)();//判断是否是注解类
	boolean isInterface = class1.isInterface();//判断是否是接口类
	boolean isEnum = class1.isEunm();//判断是否是枚举类
	boolean isAnoymousClass = class1.isAnoymousClass();//判断是否是匿名内部类
	boolean isAnnotationPresent= class1.isAnnotationPresent(Deprecated.class);//判断是否被某个注解类修改
	
	String className = class1.getName();//获取class名字，包含包路径
	Packge aPackge = class1.getPackge();//获取class的包的信息
	String simpName = class1.getSimpName();//获取class类名
	int modifies = class1.getModifies();//获取class访问权限
	
	Class<?>[] declareClasses = class1.getDeclareClasses();//内部类
	Class<?> declaringClass = class1.getDeclaringClass();//外部类
```
####3)、获取class对象的属性，方法，构造函数等
```Java 
	Field[] allFields = class1.getDeclareFields();//获取class对象的所有属性
	Field[]	publicFields=class1.getFields();//获取class对象的Public


```