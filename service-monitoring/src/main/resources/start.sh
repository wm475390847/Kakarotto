#!/bin/bash
echo "开始启动监控>>>>>>>"

proc_number=$(ps -ef |grep -w server-monitoring*|wc -l)

if [ ${proc_number} -le 1 ];then
	echo "进程不存在，启动中"
	nohup java -jar server-monitoring.jar > /dev/null  2>&1 &
	echo "已启动"
else 
	echo "进程已存在"
fi
