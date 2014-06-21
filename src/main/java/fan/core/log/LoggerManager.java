package fan.core.log;

import java.lang.reflect.Method;
/**
 * <p> ##################################################### </p>
 * <p> @描述： 日志管理器。该类会检测项目是否使用了 Log4j，</p>
 * <p>        若有，则调项目配置好的 Log4j 来做相关的信息记录工作。</p>
 * <p>        若没有，则它只是相当于控制台的一个简单的打印语句的调用。</p>
 * <p> @作者： fancy </p>
 * <p> @邮箱： fancores@163.com </p>
 * <p> @日期： 2014-05-11 </p>
 * <br> ##################################################### </p>
 */
public class LoggerManager {
	
	private Object target;
	
	private LoggerManager(String name, Log4jEnum status){
		switch (status) {
			case INEXISTENCE: break;
			default: target = lookupLog4jLogger(name);
		}
	}
	
	/** <p> 得到一个可以记录日志的实例 </p> */
	static Logger getLogger(String name, Log4jEnum status){
		return new Logger(new LoggerManager(name, status));
	}
	
	/** <p> 得到一个可以记录日志的实例 </p> */
	static Logger getLogger(Class<?> clazz, Log4jEnum status){
		return new Logger(new LoggerManager(clazz.getSimpleName(), status));
	}
	
	/** <p> 记录日志 </p> */
	void log(String name, Object arg){
		log(name, arg, null);
	}
	
	/** <p> 记录日志 </p> */
	void log(String name, Object arg, Throwable throwable){
		if(target != null){
			try {
				callLog4j(target.getClass(), name, arg, throwable);
			} catch (Exception e) {
				try {
					/* 类内若找不到，回溯到父类找 */
					callLog4j(target.getClass().getSuperclass(), name, arg, throwable);
				} catch (Exception ex) { /* 若父类还找不到，不做任何处理 */ }
			}
		}else {
			if(name.equals("info") || name.equals("debug") || name.equals("trace")){
				System.out.println(arg);
			}
			if(name.equals("warn") || name.equals("error") || name.equals("fatal")){
				System.err.println(arg);
			}
			if(throwable != null){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) { }
				throwable.printStackTrace();
			}
		}
	}
	
	/** <p> log4j 方法调用 </p> */
	private void callLog4j(Class<?> targetClass, String name, Object arg, Throwable throwable) throws Exception {
		if(throwable == null){
			targetClass.getDeclaredMethod(name, Object.class).invoke(target, arg);
		}else{
			targetClass.getDeclaredMethod(name, Object.class, Throwable.class).invoke(target, arg + "\n", throwable);
		}
	}
	
	/** <p> 尝试获取 org.apache.log4j.Logger 的 Class 实例 </p> */
	private Class<?> getLog4jLoggerClass() {
		try {
			return Class.forName("org.apache.log4j.Logger");
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	/** <p> 尝试获取 log4j 的 Logger 实例 </p> */
	private Object lookupLog4jLogger(String name) {
		Class<?> log4jLoggerClass = getLog4jLoggerClass();
		if(log4jLoggerClass != null){
			try {
				Method method = log4jLoggerClass.getDeclaredMethod("getLogger", String.class);
				return method.invoke(null, name);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	
	static enum Log4jEnum { EXIST, INEXISTENCE, UNKNOWN; }
}