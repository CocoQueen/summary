Android 与 webview 的js交互

一、webview调用java方法：

	1、允许webview加载js，通过 webview.getsetting().setjavascriptenabled(true);
  
	2、编写js类接口类
  
	3、给webview添加js接口,通过webview.addjavascripinterface(obj,name);注：obj：是js接口类，name是交互时的一个flag
  
二、android 调用js方法

	1、webview.loadurl(javascript:jsString);
  
	2、jsString是要调用的js代码的字符串

三、chorme调试

	1、打开允许调试的开关，通过webview.setWebContentsDebuggingEnabled(true);api 19及以上可用
  
	2、使用Chorme浏览器进行调试，chorme://inspect/#devices


四、js交互中常见的一些错误:

	1、在js接口的回调方法中throw exception，android端不会崩溃，但是网页会出现崩溃
  
	2、web端不进行对象存在的判断，网页端会抛出一个异常
  
	3、传递参数类型不一致（尤其是数组和对象）
  
	4、字符串类型参数为空时传递undefined


五、webview的使用场景：

  客户端的意见反馈和用户协议、新闻客户端的正文页和广告页等。
  
  
  
  
 Material主题

	新的控件

	视图阴影

	动画效果

	与drawable的兼容性


Material Design(材料设计)
		Material Design可以理解为一门视觉设计语言，以构建跨平台和设备尺寸的统一体验，遵循基本的移动设计定则，支持触摸、语音、鼠标、键盘等输入方式为目标。通过设计方式来达到实体表面和边缘的真实感，借鉴传统的印刷设计——排版、网络、空间、比例、配色、图像使用——这些基础的平面设计规范。构建除视觉层级、视觉意义以及视觉聚焦。Material Design设计语言根据用户行为凸显核心功能，通过有意义的动画效果维持整个系统的连续性体验进而指引用户操作。
（Google最初设计这门全新的设计语言，目的是为了打造一个google独有的设计风格。无论是手机、平板、台式机和其他平台形成统一的设计理念。）

	各种设计方式：
			拟物化（系统自带图标设计）
			扁平化（ios 8）
			阴影和层次化（Material Design）

	材料设计要做的工作：
			保证向后兼容性
			使用材料设计主题
			让视图产生阴影
			使用List和Card
			定制动画



使用材料主题：

	@android/style/Theme.Material                  	黑色背景
	
	@android/style/Theme.Material.Light			亮色背景
	
	@android/style/Theme.Material.Light.DarkActionBar




recyclerview：

	listview控件的升级版（recyclerview不仅支持垂直的还支持水平的列表）  
	LinearLayoutManager[listview和画廊效果]
	GridLayoutManager[网格]
	StaggeredGridLayoutManager（recyclerview提供了多个布局管理器，控制列表的展示状态）
	定制Item动画、指定Item之间的分隔条（分隔线、动画、以及一些监听事件需要自己定义）


  
