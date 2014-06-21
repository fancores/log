
##########################################################################################
#	
#	封装了与日志相关的常用的操作。
#	
#	如果检测到项目有使用 Log4, 那么其效果与 org.apache.log4j.Logger 一样。
#	
#	如果检测到项目没有使用 Log4j, 那么其下的方法只是在控制台输出信息。
#	
#	可以通过 Logger.getConsoleLogger() 来关闭对 Log4j 的检查, 而只使用控制台输出。
#	
#	开发环境：eclipse（indigo） + maven
#	
#	eclipse 导入项目：
#	
#	Import ——> Maven ——> Existing Maven Projects
#	
#	Ant 任务：
#	
#	m-package ( 打包 )
#	
#	m-clean   ( 清除 )
#	
#	m-idea    ( 生成 idea 项目 )
#	
#	m-eclipse ( 生成 eclipse 项目 )
#	
#	01. 导入项目结构混乱
#	
#		选中项目 ——> 右键 ——> Maven ——> Update Project Configuration
#	
#	demo 项目地址：https://github.com/fancores/log-demo
#	
##########################################################################################
