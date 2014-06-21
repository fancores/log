package fan.core.log;

import fan.core.log.LoggerManager.Log4jEnum;
/**
 * <p> ##################################################### </p>
 * <p> @描述： 如果检测到项目有使用 Log4, 那么该类效果与 org.apache.log4j.Logger 一样。 </p>
 * <p>        如果检测到项目没有使用 Log4j, 那么调用该类下的方法只是在控制台输出信息。
 * <p> @作者：fancy </p>
 * <p> @邮箱：fancores@163.com </p>
 * <p> @日期：2014-05-11 </p>
 * <br> ##################################################### </p>
 */
public class Logger {
	
	private LoggerManager logger;
	
	Logger(LoggerManager logger){
		this.logger = logger;
	}
	
	/** 
	 * <p> 得到一个可以记录日志的实例 </p>
	 * <p> 若项目使用了 Log4j 则效果与 Log4j.Logger 效果相同 </p>
	 */
	public static Logger getLogger(String name){
		return LoggerManager.getLogger(name, Log4jEnum.UNKNOWN);
	}
	
	/** 
	 * <p> 得到一个可以记录日志的实例 </p>
	 * <p> 若项目使用了 Log4j 则效果与 Log4j.Logger 效果相同 </p>
	 */
	public static Logger getLogger(Class<?> clazz){
		return LoggerManager.getLogger(clazz, Log4jEnum.UNKNOWN);
	}
	
	/** 
	 * <p> 得到一个可以记录日志的实例 </p>
	 * <p> 只在控制台输出信息, 不记录到日志文件 </p>
	 */
	public static Logger getConsoleLogger(String name){
		return LoggerManager.getLogger(name, Log4jEnum.INEXISTENCE);
	}
	
	/** 
	 * <p> 得到一个可以记录日志的实例 </p>
	 * <p> 只在控制台输出信息, 不记录到日志文件 </p>
	 */
	public static Logger getConsoleLogger(Class<?> clazz){
		return LoggerManager.getLogger(clazz, Log4jEnum.INEXISTENCE);
	}

	/** <p> 日志级别 </p> */
	public void trace(Object message) {
		logger.log("trace", message);
	}

	/** <p> 日志级别 </p> */
	public void trace(Object message, Throwable throwable) {
		logger.log("trace", message, throwable);
	}

	/** <p> 日志级别 </p> */
	public void debug(Object message) {
		logger.log("debug", message);
	}

	/** <p> 日志级别 </p> */
	public void debug(Object message, Throwable throwable) {
		logger.log("debug", message, throwable);
	}

	/** <p> 日志级别 </p> */
	public void debug(String message, Object... placeholderValues) {
		logger.log("debug", parsePlaceholder(message, placeholderValues));
	}

	/** <p> 日志级别 </p> */
	public void debug(Throwable e, String message, Object... placeholderValues) {
		logger.log("debug", parsePlaceholder(message, placeholderValues), e);
	}

	/** <p> 日志级别 </p> */
	public void info(Object message) {
		logger.log("info", message);
	}

	/** <p> 日志级别 </p> */
	public void info(Object message, Throwable throwable) {
		logger.log("info", message, throwable);
	}

	/** <p> 日志级别 </p> */
	public void info(String message, Object... placeholderValues) {
		logger.log("info", parsePlaceholder(message, placeholderValues));
	}

	/** <p> 日志级别 </p> */
	public void info(Throwable e, String message, Object... placeholderValues) {
		logger.log("info", parsePlaceholder(message, placeholderValues), e);
	}

	/** <p> 日志级别 </p> */
	public void warn(Object message) {
		logger.log("warn", message);
	}

	/** <p> 日志级别 </p> */
	public void warn(Object message, Throwable throwable) {
		logger.log("warn", message, throwable);
	}

	/** <p> 日志级别 </p> */
	public void warn(String message, Object... placeholderValues) {
		logger.log("warn", parsePlaceholder(message, placeholderValues));
	}

	/** <p> 日志级别 </p> */
	public void warn(Throwable e, String message, Object... placeholderValues) {
		logger.log("warn", parsePlaceholder(message, placeholderValues), e);
	}

	/** <p> 日志级别 </p> */
	public void error(Object message) {
		logger.log("error", message);
	}

	/** <p> 日志级别 </p> */
	public void error(Object message, Throwable throwable) {
		logger.log("error", message, throwable);
	}

	/** <p> 日志级别 </p> */
	public void error(String message, Object... placeholderValues) {
		logger.log("error", parsePlaceholder(message, placeholderValues));
	}

	/** <p> 日志级别 </p> */
	public void error(Throwable e, String message, Object... placeholderValues) {
		logger.log("error", parsePlaceholder(message, placeholderValues), e);
	}

	/** <p> 日志级别 </p> */
	public void fatal(Object message) {
		logger.log("fatal", message);
	}

	/** <p> 日志级别 </p> */
	public void fatal(Object message, Throwable throwable) {
		logger.log("fatal", message, throwable);
	}

	/** <p> 解析占位符 </p> */
	private String parsePlaceholder(String source, Object... values){
		int length = values.length;
		for(int i = 0; i < length; i++){
			source = source.replaceFirst("\\?", values[i].toString());
		}
		return source;
	}
	
}