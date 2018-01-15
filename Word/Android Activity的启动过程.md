# Android Activity的启动过程

## 前言

**在了解Activity的启动的过程的时候，我们需要先了解下面几个东西是什么，这将更加有助于你深入了解android 活动的启动机制**  
老司机绕道 

* **Activity**  
这个类大家再熟悉不过了，android最重要的组件之一，实际上它就是一个Java对象，它会被创建，同时也会被垃圾回收就只销毁，只不过它受AMS(ActivityManagerService)管理，所以它才有生命周期。

* **ActivityResult**  
Activity 管理服务端中，Activity的记录和缓存，换句话说：就是客户端启动一个Activity，AMS(ActivityManagerService)会对它进行缓存，而缓存的Activity的类型就是ActivityResult。  

* **AMS - ActivityManagerService**  
android系统服务，Android管理的服务端，用于管理Activity的各种行为，控制Activity的生命周期，派发消息事件，低内存管理等等。实现了IBinder接口，可以用于进程间的通信。  

* **ApplicationThread**  
该类实现了IBinder接口，Activity整个框架中客户端和服务端AMS（ActivityManagerService）通信的接口。同时也是ActivityThread的内部类。这样就有效的把AMS(ActivityManagerService）和ActivityThread绑在了一起。

*  **ActivityThread**  
ApplicationThread所绑定的客户端就是ActivityThread，ActivityThread这个类在Activity客户端中的作用举足轻重。
  
>	1. 它是应用程序的入口，大家都知道，Java程序的入口为main()方法，同样，当AMS（ActivityManagerService）拉起了一个新的进程，同时启动一个主线程的时候，主线程就从ActivityThread.main方法开始执行，他回初始化一些对象，然后自己进入消息等待队列，也就是Looper.looper();一旦进入loop()方法，线程就进入了死循环，再也不会退出；一直等待别人给它发消息，然后执行这个消息。这也是EDT(事件驱动模型)的原理。
>	2. 它是Activity客户端的管理类，由它来决定什么时候调用onCreate()，什么时候调用onResume()方法，当Activity发起一个请求时，比如startActivity()或者finish()的时候，它就会来除了这个请求，然后调用其它的人来做具体的事。  
 
* **Launcher**  
Android系统启动后，加载的第一个程序，是其他应用程序的入口。  
也可以简单的理解为我们看到的android桌面  

* **Instrumentation**  
这个类在Activity启动的时候以及广播注册的时候都会调用，除了跟android的测试有关之外，还是Activity管理中实际做事的人，比如说startActivity()，在某种情况下，就是调用这个类，然后调用AMS(ActivityManagerService),但是有的时候也是通过ApplicationThread去访问AMS的。  

* **PackageManagerService** 
是Android系统中最常用的服务之一。它负责系统中Package的管理，应用程序的安装、卸载、信息查询等。这只是一个很笼统的说法。  
[PackageManagerService详解点我](https://www.cnblogs.com/chenlong-50954265/p/5729553.html)



## 正文

> Activity作为android 四大组件之一,也是最基本的组件，负责用户的交互的所有功能。Activity的启动过程也并非是一件神秘的事情，接下来就简单的从源码的角度分析一下Activity的启动过程。  
根Activity一般就是指我们项目中的MainActivity，代表了一个android应用程序，一般也是在一个新的进程中启动起来。在android系统中所有的Activity组件都保存在堆栈中，我们启动一个新的Activity组件就位于上一个Activity的上面，那么我们从桌面（Launcher）打开一个APP的 是一个怎么样的过程呢，如下所示：  

    1. Launcher向ActivityManagerService发送一个启动MainActivity的请求；  

    2. ActivityManagerService首先将MainActivity的相关信息保存下来，然后向Launcher发送一个实质进入暂停的状态的请求；  
    
    3. Launcher收到暂停状态之后，就会向ActivityManagerService发送一个已经进入暂停的状态的请求，便于ActivityManagerService继续执行启动MainActivity的操作； 

    4. ActivityManagerService检查用于运行MainActivity的进程，如果不存在，则会启动一个新的进程

    5. ActivityManagerService将第（2）步保存下来的MainActivity相关信息发送个新的进程，便于该进程启动MainActivity组件
    
1. **Launcher.startActivitySafely**  

   ```java
   boolean startActivitySafely(Intent intent, Object tag) {    
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
        try {    
            startActivity(intent);    
            return true;    
        } catch (ActivityNotFoundException e) {}    
    }    
   
   ```

   > 当我们在Launcher上点击应用图标时，startActivitySafely方法会被调用。需要启动的Activity信息保存在Intent中，包括action、category等等。那么Launcher是如何获得Intent里面的这些信息呢？首先，系统在启动时会启动一个叫做PackageManagerService的管理服务，并且通过它来安装系统中的应用程序，这个过程中，PackageManagerService会对应用程序的配置文件androidManifest.xml进行解析，从而得到程序里面的组件信息（包括Activity、Service、Broadcast等），然后PackageManagerService去查询所有的action为```android.intent.action.MAIN```并且category为“android.intent.category.LAUNCHER”的Activity，然后为每一个应用程序创建一个快捷图标，并把程序信息与之关联。上述代码中，Activity的启动标志位位置为```Intent.FLAG_ACTIVITY_NEW_TASK```,便于它可以在一个新的任务战中启动。
 
2. **Activity.startActivity**  

	```java

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        if (options != null) {
            startActivityForResult(intent, -1, options);
        } else {
            // Note we want to go through this call for compatibility with
            // applications that may have overridden the method.
            startActivityForResult(intent, -1);
        }
    }

	```
	> 调用startActivityForResult,第二个参数requestCode为-1，则表示Activity关闭时不需要将结果传回来。

3. **Activity.startActivityForResult**  

	```java

	public void startActivityForResult(@RequiresPermission Intent intent, int requestCode,
            @Nullable Bundle options) {
        if (mParent == null) {
            options = transferSpringboardActivityOptions(options);
            Instrumentation.ActivityResult ar =
                mInstrumentation.execStartActivity(
                    this, mMainThread.getApplicationThread(), mToken, this,
                    intent, requestCode, options);
            if (ar != null) {
                mMainThread.sendActivityResult(
                    mToken, mEmbeddedID, requestCode, ar.getResultCode(),
                    ar.getResultData());
            }
            if (requestCode >= 0) {
                // If this start is requesting a result, we can avoid making
                // the activity visible until the result is received.  Setting
                // this code during onCreate(Bundle savedInstanceState) or onResume() will keep the
                // activity hidden during this time, to avoid flickering.
                // This can only be done when a result is requested because
                // that guarantees we will get information back when the
                // activity is finished, no matter what happens to it.
                mStartedActivity = true;
            }

            cancelInputsAndStartExitTransition(options);
            // TODO Consider clearing/flushing other event sources and events for child windows.
        } else {
            if (options != null) {
                mParent.startActivityFromChild(this, intent, requestCode, options);
            } else {
                // Note we want to go through this method for compatibility with
                // existing applications that may have overridden it.
                mParent.startActivityFromChild(this, intent, requestCode);
            }
        }
    }
    
	```
	
	> 实际上是调用的```mInstrumentation.execStartActivity```来启动Activity，mInstrumentation类型为Instrumentation, Instrumentation用于监控程序和系统之间的交互操作。mInstrumentation代为执行Activity的启动操作，便于它可以监控这个交互过程。mMainThread的类型为ActivityThread，用于描述一个应用程序进程，系统每启动一个程序都会在它里面加载一个ActivityThread的实例，并且将实例保存在Activity的成员变量mMainThread中，而mMainThread.getApplicationThread()则用于获取内部为一个ApplicationThread的本地Binder对象。mToken的类型为IBinder，它是一个Binder的代理对象，指向了ActivityManagerService中一个类型为ActivityResult的本地对象。每一个已经启动的Activity在ActivityManagerService中都有一个对应的ActivityRecord对象，用于维护Activity的运行状态以及信息。

4. Instrumentation.execStartActivity   

	```java

 public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        IApplicationThread whoThread = (IApplicationThread) contextThread;
        Uri referrer = target != null ? target.onProvideReferrer() : null;
        if (referrer != null) {
            intent.putExtra(Intent.EXTRA_REFERRER, referrer);
        }
        if (mActivityMonitors != null) {
            synchronized (mSync) {
                final int N = mActivityMonitors.size();
                for (int i=0; i<N; i++) {
                    final ActivityMonitor am = mActivityMonitors.get(i);
                    if (am.match(who, null, intent)) {
                        am.mHits++;
                        if (am.isBlocking()) {
                            return requestCode >= 0 ? am.getResult() : null;
                        }
                        break;
                    }
                }
            }
        }
        try {
            intent.migrateExtraStreamToClipData();
            intent.prepareToLeaveProcess(who);
            int result = ActivityManagerNative.getDefault()
                .startActivity(whoThread, who.getBasePackageName(), intent,
                        intent.resolveTypeIfNeeded(who.getContentResolver()),
                        token, target != null ? target.mEmbeddedID : null,
                        requestCode, 0, null, options);
            checkStartActivityResult(result, intent);
        } catch (RemoteException e) {
            throw new RuntimeException("Failure from system", e);
        }
        return null;
    }


	```  

	> ActivityManagerNative.getDefault()获取一个ActivityManagerService的代理对象，然后调用它的startActivity方法来通知ActivityManagerService去启动Activity。中间还有一系列的过程，最后是调用ActivityThread中的私有内部类ApplicationThread的scheduleLauncherActivity来进行Activity的启动。    

5. **AcivityThread.ApplicationThread.scheduleLaunchActivity**  

	```java   

  @Override
        public final void scheduleLaunchActivity(Intent intent, IBinder token, int ident,
                ActivityInfo info, Configuration curConfig, Configuration overrideConfig,
                CompatibilityInfo compatInfo, String referrer, IVoiceInteractor voiceInteractor,
                int procState, Bundle state, PersistableBundle persistentState,
                List<ResultInfo> pendingResults, List<ReferrerIntent> pendingNewIntents,
                boolean notResumed, boolean isForward, ProfilerInfo profilerInfo) {

            updateProcessState(procState, false);

            ActivityClientRecord r = new ActivityClientRecord();

            r.token = token;
            r.ident = ident;
            r.intent = intent;
            r.referrer = referrer;
            r.voiceInteractor = voiceInteractor;
            r.activityInfo = info;
            r.compatInfo = compatInfo;
            r.state = state;
            r.persistentState = persistentState;

            r.pendingResults = pendingResults;
            r.pendingIntents = pendingNewIntents;

            r.startsNotResumed = notResumed;
            r.isForward = isForward;

            r.profilerInfo = profilerInfo;

            r.overrideConfig = overrideConfig;
            updatePendingConfiguration(curConfig);

            sendMessage(H.LAUNCH_ACTIVITY, r);
        }

	```  
	> 构造了一个ActivityClientRecord，然后调用sendMessage发送一个消息。在引用程序对应的进程中。每一个Activity组件都是使用一个ActivityClientRecord对象来描述的，他们保存在ActivityThread类的成员变量mActivities中。
	
	
6. ActivityThread.H.handleMessage  

	```java 
	
	public void handleMessage(Message msg) {
            if (DEBUG_MESSAGES) Slog.v(TAG, ">>> handling: " + codeToString(msg.what));
            switch (msg.what) {
                case LAUNCH_ACTIVITY: {
                    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityStart");
                    final ActivityClientRecord r = (ActivityClientRecord) msg.obj;

                    r.packageInfo = getPackageInfoNoCheck(
                            r.activityInfo.applicationInfo, r.compatInfo);
                    handleLaunchActivity(r, null, "LAUNCH_ACTIVITY");
                    Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
                } break;
                case RELAUNCH_ACTIVITY: {
                    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityRestart");
                    ActivityClientRecord r = (ActivityClientRecord)msg.obj;
                    handleRelaunchActivity(r);
                    Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
                } break;
                case PAUSE_ACTIVITY: {
                    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityPause");
                    SomeArgs args = (SomeArgs) msg.obj;
                    handlePauseActivity((IBinder) args.arg1, false,
                            (args.argi1 & USER_LEAVING) != 0, args.argi2,
                            (args.argi1 & DONT_REPORT) != 0, args.argi3);
                    maybeSnapshot();
                    Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
                } break;
 			// ………… 此处有代码省略
  }
  
  ```
  
  > 首先将msg里面的obj转化成一个ActivityClientRecord对象，然后调用来获取一个LoaderAPK对象并保存在ActivityClientRecord对象的成员变量packageInfo中。loader对象用于藐视一个已经加载的APK文件。最后调用handleLauncherActivity来启动Activity组件。
  
  
7. ActivityThread.handleLaunchActivity    

	```java 
	
	    private void handleLaunchActivity(ActivityClientRecord r, Intent customIntent, String reason) {
        // If we are getting ready to gc after going to the background, well
        // we are back active so skip it.
        unscheduleGcIdler();
        mSomeActivitiesChanged = true;

        if (r.profilerInfo != null) {
            mProfiler.setProfiler(r.profilerInfo);
            mProfiler.startProfiling();
        }

        // Make sure we are running with the most recent config.
        handleConfigurationChanged(null, null);

        if (localLOGV) Slog.v(
            TAG, "Handling launch of " + r);

        // Initialize before creating the activity
        WindowManagerGlobal.initialize();

        Activity a = performLaunchActivity(r, customIntent);

        if (a != null) {
            r.createdConfig = new Configuration(mConfiguration);
            reportSizeConfigurations(r);
            Bundle oldState = r.state;
            handleResumeActivity(r.token, false, r.isForward,
                    !r.activity.mFinished && !r.startsNotResumed, r.lastProcessedSeq, reason);

            if (!r.activity.mFinished && r.startsNotResumed) {
                // The activity manager actually wants this one to start out paused, because it
                // needs to be visible but isn't in the foreground. We accomplish this by going
                // through the normal startup (because activities expect to go through onResume()
                // the first time they run, before their window is displayed), and then pausing it.
                // However, in this case we do -not- need to do the full pause cycle (of freezing
                // and such) because the activity manager assumes it can just retain the current
                // state it has.
                performPauseActivityIfNeeded(r, reason);

                // We need to keep around the original state, in case we need to be created again.
                // But we only do this for pre-Honeycomb apps, which always save their state when
                // pausing, so we can not have them save their state when restarting from a paused
                // state. For HC and later, we want to (and can) let the state be saved as the
                // normal part of stopping the activity.
                if (r.isPreHoneycomb()) {
                    r.state = oldState;
                }
            }
        } else {
            // If there was an error, for any reason, tell the activity manager to stop us.
            try {
                ActivityManagerNative.getDefault()
                    .finishActivity(r.token, Activity.RESULT_CANCELED, null,
                            Activity.DONT_FINISH_TASK_WITH_ACTIVITY);
            } catch (RemoteException ex) {
                throw ex.rethrowFromSystemServer();
            }
        }
    }
 
 ```
 
 >  ```Activity a = performLaunchActivity(r ,customIntent);```真正完成Activity的吊起，Activity被实例化，onCreate被调用。performLaunchActivity函数加载用户自定义的Activity的派生类，并执行其onCreate函数，它将返回此Activity对象。·``handleResumeActivity(r.token, false, r.isForward,!r.activity.mFinished && !r.startsNotResumed);```同理，再调用Activity实例的Resume(用户界面可见)
 
8. ActivityThread. performLaunchActivity   

	```java
	
	    private Activity performLaunchActivity(ActivityClientRecord r, Intent customIntent) {
        // System.out.println("##### [" + System.currentTimeMillis() + "] ActivityThread.performLaunchActivity(" + r + ")");

        ActivityInfo aInfo = r.activityInfo;
        if (r.packageInfo == null) {
            r.packageInfo = getPackageInfo(aInfo.applicationInfo, r.compatInfo,
                    Context.CONTEXT_INCLUDE_CODE);
        }

        ComponentName component = r.intent.getComponent();
        if (component == null) {
            component = r.intent.resolveActivity(
                mInitialApplication.getPackageManager());
            r.intent.setComponent(component);
        }

        if (r.activityInfo.targetActivity != null) {
            component = new ComponentName(r.activityInfo.packageName,
                    r.activityInfo.targetActivity);
        }

        Activity activity = null;
        try {
            java.lang.ClassLoader cl = r.packageInfo.getClassLoader();
            activity = mInstrumentation.newActivity(
                    cl, component.getClassName(), r.intent);
            StrictMode.incrementExpectedActivityCount(activity.getClass());
            r.intent.setExtrasClassLoader(cl);
            r.intent.prepareToEnterProcess();
            if (r.state != null) {
                r.state.setClassLoader(cl);
            }
        } catch (Exception e) {
            if (!mInstrumentation.onException(activity, e)) {
                throw new RuntimeException(
                    "Unable to instantiate activity " + component
                    + ": " + e.toString(), e);
            }
        }

        try {
            Application app = r.packageInfo.makeApplication(false, mInstrumentation);

            if (localLOGV) Slog.v(TAG, "Performing launch of " + r);
            if (localLOGV) Slog.v(
                    TAG, r + ": app=" + app
                    + ", appName=" + app.getPackageName()
                    + ", pkg=" + r.packageInfo.getPackageName()
                    + ", comp=" + r.intent.getComponent().toShortString()
                    + ", dir=" + r.packageInfo.getAppDir());

            if (activity != null) {
                Context appContext = createBaseContextForActivity(r, activity);
                CharSequence title = r.activityInfo.loadLabel(appContext.getPackageManager());
                Configuration config = new Configuration(mCompatConfiguration);
                if (r.overrideConfig != null) {
                    config.updateFrom(r.overrideConfig);
                }
                if (DEBUG_CONFIGURATION) Slog.v(TAG, "Launching activity "
                        + r.activityInfo.name + " with config " + config);
                Window window = null;
                if (r.mPendingRemoveWindow != null && r.mPreserveWindow) {
                    window = r.mPendingRemoveWindow;
                    r.mPendingRemoveWindow = null;
                    r.mPendingRemoveWindowManager = null;
                }
                activity.attach(appContext, this, getInstrumentation(), r.token,
                        r.ident, app, r.intent, r.activityInfo, title, r.parent,
                        r.embeddedID, r.lastNonConfigurationInstances, config,
                        r.referrer, r.voiceInteractor, window);

                if (customIntent != null) {
                    activity.mIntent = customIntent;
                }
                r.lastNonConfigurationInstances = null;
                activity.mStartedActivity = false;
                int theme = r.activityInfo.getThemeResource();
                if (theme != 0) {
                    activity.setTheme(theme);
                }

                activity.mCalled = false;
                if (r.isPersistable()) {
                    mInstrumentation.callActivityOnCreate(activity, r.state, r.persistentState);
                } else {
                    mInstrumentation.callActivityOnCreate(activity, r.state);
                }
                if (!activity.mCalled) {
                    throw new SuperNotCalledException(
                        "Activity " + r.intent.getComponent().toShortString() +
                        " did not call through to super.onCreate()");
                }
                r.activity = activity;
                r.stopped = true;
                if (!r.activity.mFinished) {
                    activity.performStart();
                    r.stopped = false;
                }
                if (!r.activity.mFinished) {
                    if (r.isPersistable()) {
                        if (r.state != null || r.persistentState != null) {
                            mInstrumentation.callActivityOnRestoreInstanceState(activity, r.state,
                                    r.persistentState);
                        }
                    } else if (r.state != null) {
                        mInstrumentation.callActivityOnRestoreInstanceState(activity, r.state);
                    }
                }
                if (!r.activity.mFinished) {
                    activity.mCalled = false;
                    if (r.isPersistable()) {
                        mInstrumentation.callActivityOnPostCreate(activity, r.state,
                                r.persistentState);
                    } else {
                        mInstrumentation.callActivityOnPostCreate(activity, r.state);
                    }
                    if (!activity.mCalled) {
                        throw new SuperNotCalledException(
                            "Activity " + r.intent.getComponent().toShortString() +
                            " did not call through to super.onPostCreate()");
                    }
                }
            }
            r.paused = true;

            mActivities.put(r.token, r);

        } catch (SuperNotCalledException e) {
            throw e;

        } catch (Exception e) {
            if (!mInstrumentation.onException(activity, e)) {
                throw new RuntimeException(
                    "Unable to start activity " + component
                    + ": " + e.toString(), e);
            }
        }

        return activity;
    }
    
	```
	
	>  ```java.lang.ClassLoader cl = r.packageInfo.getClassLoader(); ```
	```activity = mInstrumentation.newActivity(cl, component.getClassName(), r.intent) ```将Activity类文件加载到内存中,创建Activity实例```mInstrumentation.callActivityOnCreate(activity, r.state);```至此，Activity启动过程就结束了，其生命周期由ApplicationThread来管理; 
	ActivityRecord里面的token，是一个Binder的代理对象，和ActivityClientRecord对象一样，都是用来藐视所启动的Activity组件，只不过前者是在Activityma中使用，后者是在引用程序进程中使用。
	
到这里，Activity启动过程就完了。  
子Activity的启动过程和应用程序的启动过程是非常类似的，过程如下：  

	1. 已启动的Activity向ActivityManagerService发送一个启动ChildActivity的请求；  
	2. ActivityManagerService首先将ChildActivity的信息保存下来，再向Activity发送一个中止的请求；
	3. Activity收到请求进入中止状态，告诉ActivityManagerService，便于ActivityManagerService继续执行启动ChildActivity的操作。
	4. ActivityManagerService检查ChildActivity所运行的进程是否存在，存在就发送ChildActivity信息给它，以便进行启动，

	
	
## 总结  
经过了应用进程和系统进程一系列操作，完成Activity的启动，并且也找到了Activity的生命周期方法到底是怎么调用的，只是很多方法太长，其中的一些逻辑还不是很理解，后面有时间再仔细看看

	
	
  
                
	
	


	
	





## 感谢以下文章提供的帮助
1. [浅析Android Activity的启动过程](http://blog.csdn.net/yyh352091626/article/details/51086117#)
2. [Android之ActivityManagerService浅谈](http://blog.csdn.net/LHQJ1992/article/details/50983618)


 
