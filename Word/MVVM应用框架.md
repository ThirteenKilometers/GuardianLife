#MVVM应用框架
____________________________
>  **View:**对应Activity 和XML ，负责View的绘制以及与用户的交互。  
>  **Model:**实体模型  
>  **ViewModel:**负责完成View与Model见的交互，负责业务逻辑。

MVVM的目标和思想与MVP类似，利用数据绑定（Data Binding）、依赖属性（Dependency Property）、命令（Command）、路由事件（Routed Event）等新特性，打造一个更加灵活高效的架构。


###数据驱动
在常规的开发模式中，数据变化需要更新UI的时候，需要先获取UI控件的应用，然后再更新UI。获取用户的输入和操作也需要通过UI控件的引用。在MVVM 中，这些改变也能自动反馈到数据层，数据成为主导因素。这样VM在业务逻辑处理中只要关心数据，不需要直接和UI打交道，在业务处理过程中简单方便很多。

###低耦合度
MVVM模式中，数据是独立于UI的。  
数据和业务逻辑处于一个独立的ViewModel中，ViewModel只需要关注数据和业务逻辑，不需要和UI或者控件打交道，UI想怎么处理数据都由UI自己决定，ViewModel不涉及任何和UI相关的事，也不持有UI控件的引用，即便是控件改变了（比如TextView换成了EditText），ViewModel也几乎不需要更改任何代码。它非常完美的解耦了View层和ViewModel，解决了上面我们所说的MVP的痛点。

###更新UI
在MVVM中，数据发生变化后，我们在工作线程直接修改（在数据是线程安全的情况下）ViewModel的数据即可，不用再考虑要切到主线程更新UI了，这些事情相关框架都帮我们做了。

###团队协作
MVVM的分工是非常明显的，由于View和ViewModel之间是松散耦合的：一个是处理业务和数据、一个是专门的UI处理。所以，完全由两个人分工来组，一个做UI（XML 和 Activity ）一个写ViewModel效率更高。

###可复用性
一个ViewModel可以复用到多个View中，。同样的一份数据，可以提供给不同的UI去做展示。对于版本迭代中频繁的UI改动，更新或新增一套View即可。如果想在UI上做A/B Testing ，那MVVM是你不二的选择

###单元测试
ViewModel层做的事数数据处理和业务逻辑，View层中关注的是UI，两者完全没有依赖。不管UI的单元测试还是业务逻辑的单元测试，都是低耦合的，在MVVM中数据是直接绑定到UI控件的（部分数据是可以直接反映处UI上的内容），那么我们就可直接通过修改绑定的数据源来间接做一些android UI上的测试

###如何分工
构建MVVM框架首先要具体了解各个模块的分工。View、ViewModel、Model他们的各自的职责塑造  

* View  
> View层做的就是UI相关的工作，我们只在XML、Activity和Fragment写View成的代码，View层不做和业务相关的事，也就是我们在Activity中不写业务逻辑和业务数据相关的代码，更新UI通过数据绑定实现，尽量在ViewModel里面做（更新绑定的数据源即可），Activity要做的事就是初始化一些控件（比如控件的颜色，添加RecyclerView的分割线），View成可以提供更新UI的接口（但是我们更倾向所有的UI原色都是通过数据来驱动更改UI），View层可以处理事件，但是我们更希望UI时间通过Command来绑定）。**简单低来说：View层不做任何业务逻辑、不涉及操作数据、不处理数据，UI和数据严格分开。**  

* ViewModel  
> ViewModel层做的事情刚好和View层相反，ViewModel只做和业务逻辑和业务数据相关的事，不做任何和UI相关的事情，ViewModel层不会持有任何控件的引用，更不会在ViewModel中通过UI控件的引用去做更新UI的事情，ViewModel就是专注于业务的逻辑处理，做的事情也都是对数据的操作（这些数据绑定在相应的控件上会自动去更改UI）。同时DataBinding框架已经支持双向绑定，让我们可以通过双向绑定获取View层反馈给ViewModel层的数据，并对这些数据上进行操作，关于对UI控件事件的处理，我们也希望能把这些事件处理绑定到控件上，并把这些事件的处理统一化，为此我们通过BindingAdapter对一些常用的时间做了封装，把一个个时间封装成一个Command，对于每个事件我们用一个ReplyCommand去处理就行，ReplyCommand会把你可能需要的数据带给你，这使得我们在ViewModel层处理事件的时候字需要关心处理数据就行了。**ViewModel不做和UI相关的事**  

* Model
>  Model层最大的特点是被赋予了数据获取的职责，与我们平常Model层之定义实体对象的行为截然不同。实例中，数据的获取、存储、数据状态变化都是Model层的任务、Model层包括实体模型（Bean）、Retrofit的Service，获取网络数据接口,本地提供数据获取接口供ViewModel调用，经数据转换和操作并最终映射到View层某个UI元素的属性上

###如何协作
关于协作  
![](http://upload-images.jianshu.io/upload_images/966283-78b410b9af8b18fa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
上图反映了MVVM框架中各个模块的联系和数据流的走向，我们从每个模块一一拆分来看。那么我们重点就是线面的三个协作  

*  **ViewModel与View的协作**
*  **ViewModel与Model的协作**
*  **ViewModel与ViewModel的协作**


1.  ViewModel与View的协作  
![](http://upload-images.jianshu.io/upload_images/966283-d2985f45c0c1e618.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/500)  
ViewModel和View是通过绑定的方式连接在一起的，绑定分成两种：一种是数据绑定，一种是命令绑定。数据的绑定Databinding已经提供好了，简单地定义了一些ObservableField就能把数据和框架和控件绑定在一起了（如TextView的Text属性）  

我们可以看出ViewModel的模块中我们可以看出ViewModel类下面一般包含下面五个部分  
*  **Context（上下文）**  
*  **Model（数据源 JavaBean）**  
*  **Data Field（数据绑定）**  
*  **Command（命令绑定）**  
*  **Child ViewModel(子ViewModel)**    


1.  Context

ViewModel不处理和UI相关的事也不操作控件，也不更新UI，那为什么要有Context呢






