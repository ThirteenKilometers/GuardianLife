#酒商网 Android 项目插件化开发总结（刘桦晨）

-----
##一、插件化概念  
插件化开发时将整个app拆分成很多模块，这些模块包括一个宿主和多个插件，每个模块都是一个apk（组件化的每个模块是个lib），最终打包的时候将宿主apk和插件apk分开或者联合打包。 ![android 插件化思维图](https://github.com/ThirteenKilometers/GuardianLife/blob/master/Images/D4AF6DAC-BE93-42AB-AA8A-97A9030C5D95.png?raw=true)



##二、为什么要使用插件化开发？以及插件化可以解决什么问题？

* 在一个大的项目里面，为了明确的分工，往往不同的团队负责不同的插件APP，这样**分工更加明确**。  
* 各个模块封装成不同的插件APK，不同模块可以单独编译，**提高了开发效率**。  
* 解决了上述的方法数超过限制的问题。  
* 可以通过上线新的插件来解决线上的BUG，达到**“热修复”**的效果。
* 将用户可选择使用的功能模块做成独立的插件动态下发，**减小了宿主APK的体积。**  
* **解耦主项目**，每个业务作为独立的插件进行开发，可以大大减轻合并不同分支带来的工作量。  
*  **提升编译效率(目前项目开发中的主要痛点)**从编译整个项目到只需要编译独立的插件，尤其对于大中型项目来说可以节约很多时间。

## 三、使用插件化可能会带来的问题
*  **首先只适用于国内市场**，google play 是不允许这种行为的。
*  目前开放出来的插件化开发框架比较多，他们各自都有自己的优势和和不足，**良莠不齐**	选择是一个问题，下面我列举一些框架
*  对于我们这种规模的项目来说，重构项目分拆业务模块的话。**重构成本是很大的（时间、以及人力）**  
*  插件化也会带来不方便的地方，比如开发习惯上的不同，需要满足一些插件框架约束条件；


##四、主流框架
1. [DynamicAPK--->携程，目前已经停止维护咯](https://github.com/CtripMobile/DynamicAPK)  
2. [DroidPlugin--->360的手机助手, 使用预占位的方式注册四大组件, 实现热更新](https://github.com/Qihoo360/DroidPlugin/blob/master/readme_cn.md)  
3. [AndroidDynamicLoader-->通过Fragment 以及 schema的方式实现的，使用限制太多](https://github.com/mmin18/AndroidDynamicLoader)
4. [OpenAltas后改名-->ACDD](https://github.com/bunnyblue/ACDD/blob/master/README-Zh.md)  
5. [360开源的DroidPlugin](https://github.com/DroidPluginTeam/DroidPlugin)
6. [VirtualAPK 是滴滴开源的一套插件化框架](https://github.com/didi/VirtualAPK)
7. [RePlugin 由360手机卫士的RePlugin Team研发](https://github.com/Qihoo360/RePlugin/blob/dev/README_CN.md)
8. [移动热修复（Mobile Hotfix）产品基于阿里巴巴首创hotpatch技术，提供细粒度热修复能力，无需等待，实时修复应用线上问题。](https://help.aliyun.com/product/51340.html?spm=5176.131995.673114.doc_11.7d9795297DIiBG)  

以上是网络上我能找到的一些主流框架，其中有些框架已经很久没有维护或者宣布不再维护  

----------


##五、要进行插件化开发，建议使用**VirtualAPK**、**RePlugin**、**DroidPlugin**、**Mobile Hotfix**这三个开源框架


###**1、VirtualAPK**  
[详细文档点我](https://github.com/didi/VirtualAPK/wiki#%E4%BC%98%E7%A7%80%E7%9A%84%E5%85%BC%E5%AE%B9%E6%80%A7)  
  VirtualAPK是滴滴出行自研的一款优秀的插件化框架，主要有如下几个特性。  
  1. 功能完备   
  2. 优秀的兼容性  
  3. 入侵性极低  
  4. VirtualAPK和主流开源框架的对比  
  ![](https://github.com/ThirteenKilometers/GuardianLife/blob/master/Images/5FB278F9-7D67-4F7B-866C-7CE1471C3839.png?raw=true)
  
####为什么选择VirtualAPK？

  已经有那么多优秀的开源的插件化框架，滴滴为什么要重新造一个轮子呢？

1. 大部分开源框架所支持的功能还不够全面 除了DroidPlugin，大部分都只支持Activity。

2. 兼容性问题严重，大部分开源方案不够健壮 由于国内Rom尝试深度定制Android系统，这导致插件框架的兼容性问题特别多，而目前已有的开源方案中，除了DroidPlugin，其他方案对兼容性问题的适配程度是不足的。

3. 已有的开源方案不适合滴滴的业务场景 虽然说DroidPlugin从功能的完整性和兼容性上来看，是一款非常完善的插件框架，然而它的使用场景和滴滴的业务不符。

DroidPlugin侧重于加载第三方独立插件，比如微信，并且插件不能访问宿主的代码和资源。而在滴滴打车中，其他业务模块均需要宿主提供的订单、定位、账号等数据，因此插件不可能和宿主没有交互。

其实在大部分产品中，一个业务模块实际上并不能轻而易举地独立出来，它们往往都会和宿主有交互，在这种情况下，DroidPlugin就有点力不从心了。

基于上述几点，我们只能重新造一个轮子，它不但功能全面、兼容性好，还必须能够适用于有耦合的业务插件，这就是VirtualAPK存在的意义。

在加载耦合插件方面，VirtualAPK是开源方案的首选。
通俗易懂地说

    如果你是要加载微信、支付宝等第三方APP，那么推荐选择DroidPlugin；
    如果你是要加载一个内部业务模块，并且这个业务模块很难从主工程中解耦，
    那么VirtualAPK是最好的选择。

抽象地说

    如果你要加载一个插件，并且这个插件无需和宿主有任何耦合，
    也无需和宿主进行通信，并且你也不想对这个插件重新打包，
    那么推荐选择DroidPlugin；
    除此之外，在同类的开源中，推荐大家选择VirtualAPK。

### **RePlugin**  
 [详细文档点我](https://github.com/Qihoo360/RePlugin/blob/dev/README_CN.md)  
 RePlugin —— 历经三年多考验，数亿设备使用的，稳定占坑类插件化方案  
 RePlugin是一套完整的、稳定的、适合全面使用的，占坑类插件化方案，由360手机卫士的RePlugin Team研发，也是业内首个提出”全面插件化“（全面特性、全面兼容、全面使用）的方案。
 
 
###**2、DroidPlugin**  
  [详细文档点我](https://github.com/DroidPluginTeam/DroidPlugin/blob/master/readme_cn.md)  
  DroidPlugin侧重于加载第三方独立插件，比如微信，并且插件不能访问宿主的代码和资源。
 
###**3、Mobile Hotfix（阿里百川）**
  [详细文档点我](https://help.aliyun.com/product/51340.html?spm=5176.131995.673114.doc_11.24f81cbcMNb1b8)  
  移动热修复（Mobile Hotfix）是阿里云提供的全平台App热修复服务方案。产品基于阿里巴巴首创hotpatch技术,提供最细粒度热修复能力，让您无需等待实时修复应用线上问题。  
  **缺点**  
  1. 侵入性高  
  2. 收费












