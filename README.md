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
