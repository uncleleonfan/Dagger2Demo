# Dagger 2 极速入门 #
Dagger是一个快速的依赖注入框架，供Android和Java开发使用。以前由Spuare维护，现在转交给Google，Github链接为：https://github.com/google/dagger。


## 依赖注入 ##
Dagger为依赖注入而生。什么是依赖？什么是注入？为什么要使用依赖注入？这是我们学习Dagger之前必须了解的。
依赖就是一个类中要使用其他的类来完成某些工作，这样一个类就依赖了另外一个类。比如在MainActivity中必须使用一个User类的对象，那么MainActivity就依赖了User类。

	public class MainActivity extends AppCompatActivity {

	    User mUser;//MainActivity依赖User类

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
			mUser = new User();//初始化mUser
	        setContentView(R.layout.activity_main);
	    }
	}

一般我们直接在MainActivity中直接初始化mUser对象，但如果有天User类的构造方法中增加了一个参数，比如用户名，那么所有调用User的无参构造方法的地方全部要修改，这显然不是我们希望看到的。于是我们思考可不可以提供一个User的工厂类或者容器类专门负责User对象的创建，这样User对象的创建就不会跟MainActivity发生耦合，不管User的构造方法如何变，都不会影响到MainActivity。那么代码可能是这样：

	public class MainActivity extends AppCompatActivity {

	    User mUser;//MainActivity依赖User类

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
			mUser = UserFactory.getUser()//通过工厂类注入mUser
	        setContentView(R.layout.activity_main);
	    }
	}

那么mUser对象就不是在MainActivity创建，而是由UserFactory创建，设置给mUser对象，这就可以称之为mUser被注入了，这样MainActivity就不需要关心User对象是怎样创建出来，对User对象创建的修改就不会影响到MainActivity里面的代码，这就是依赖注入的好处的。这就好比我们打针，人体依赖药液来治疗疾病，但药液并不是人体自己生产的，而是医药公司生产然后通过注射器注入到人体，而我们人体根本就无需关心药液是如何被制造出来的。
	
Dagger的作用就是通过注解的方式，帮我们自动生成创建对象的工厂类。

## Dagger 2使用 ##
### 1. 添加依赖 ###
可通过链接https://github.com/google/dagger/releases查找最新版本

	dependencies {
	    compile 'com.google.dagger:dagger:2.11'
	    annotationProcessor 'com.google.dagger:dagger-compiler:2.11'
	}

### 2. 添加注解@Inject ###
分别在MainActivity的User成员变量和User的构造方法上添加@Inject依赖。

	public class MainActivity extends AppCompatActivity {
	
		//成员变量上添加注解
	    @Inject
	    User mUser;
	}


	public class User {
	
	    String name;
	
		//构造方法上添加注解
	    @Inject
	    public User() {
	        this.name  = "Leon";
	    }
	
	}

### 3. 创建Component类 ###
事实上完成第二步后，MainActivity中成员mUser并没有调用构造方法完成初始化，还需要一个类来完成注入，这就是Component类。
我们创建一个MainComponent接口，提供一个inject方法，其参数为将被注入的类MainActivity

	public interface MainComponent {
	
	    void inject(MainActivity activity);
	}

然后，我们在Android Studio下选择菜单build->Make Project，这时候，会在app模块下build/generated/source/apt/debug/包名/目录下生成三个文件**DaggerMainComponent**，**MainActivity\_MembersInjector**，**User\_Factory**。DaggerMainComponent即为接口MainComponent的实现类。

### 4. 注入 ###
最后，我们可以使用生成的DaggerMainComponnet完成注入，成员变量mUser将被赋值。如果是老司机，是不是会觉得这跟ButterKnife.bind(this)有异曲同工之妙呢？

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		//注入，内部会调用User的构造方法完成成员变量mUser的初始化
        DaggerMainComponent.builder().build().inject(this);
        //打印结果，mUser不为null
        Log.d(TAG, "onCreate: " + mUser.name);
    }

## Module使用 ##
假如我想在一个Activity里面注入一个TextView，如下：

	public class MainActivity extends AppCompatActivity {
	
	    @Inject
	    TextView mTextView;
	}

如果按照上述步骤，就需要找到TextView的构造方法，加上一个@Inject注解，这个显然是无法做到的。Module就是为了解决不能够通过注解构造方法来创建一个实例的问题。我们创建一个TextViewMoudle类，使用@Module注解。另外需要提供一个方法返回一个TextView实例，并且用@Provides注解。

	@Module
	public class TextViewModule {
	
	    @Provides
	    TextView provideTextView(Context context) {
	        return new TextView(context);
	    }
	}

provideTextView方法有个Context参数，需要外界传入到TextViewModule，这里给TextViewModule提供一个带有Context参数的构造方法。另外还需要提供个provideContext方法返回上下文，因为Dagger框架调用provideTextView方法获取一个TextView实例时，发现要传一个Context类型的参数，这时候他会查找被@Provides注解并且返回值为Context类型的方法获取一个Context实例传入provideTextView方法。

	@Module
	public class TextViewModule {
		//保存一个上下文成员变量
	    private Context mContext;
	
		//构造方法接收一个上下文
	    public TextViewModule(Context context) {
	        this.mContext = context;
	    }
	
	    @Provides
	    TextView provideTextView(Context context) {
	        return new TextView(context);
	    }
	
		
	    @Provides
	    Context provideContext() {
	        return mContext;
	    }
	}

接下来还需要在MainComponent接口上指定TextViewModule。然后点击菜单选项build->Make Project，这时又会在build/generated/source/apt/debug/包名/目录下生成两个新文件**TextViewModule\_ProvideContextFactory**和**TextViewModule\_ProvideTextViewFactory**。

	@Component(modules = TextViewModule.class)
	public interface MainComponent {
	
	    void inject(MainActivity activity);
	}


最后在注入时创建一个TextViewModule传入，表示告诉Dagger框架可以从TextViewModule中获取TextView实例。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //传入TextViewMoudle
        DaggerMainComponent.builder().textViewModule(new TextViewModule(this)).build().inject(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame);
		//mTextView已被注入，不为null
        mTextView.setText(mUser.name);
        frameLayout.addView(mTextView);
    }