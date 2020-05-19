1、项目目录架构：
=
    Elevator
    ├─entity 电梯实体包
    │  └─Elevator 电梯实体类
    │
    ├─manager 管理界面包
    │  ├─ManagerUI 管理界面UI类
    │
    ├─service 请求访问模块
    │  ├─ElevatorService 电梯接口类
    │  └─imp 接口实现包
    │     └─ElevatorServiceImp 电梯接口实现类
    │
    └─ElevatorApplication 主程序启动类
2、应用算法原理：
=
    实现时把电梯分为五个状态：
     1：静止态（Still）
     2:上升状态以及下一个状态为上升状态（Direction==Up && Next_Direction ==Up）
     3:下降状态以及下一个状态是下降状态（Direction==Down && Next_Direction ==Down）
     4:上升状态以及下一个状态时下降状态Direction==Up && Next_Direction ==Down）
     5:下降状态以及下一个状态是上升状态（Direction==Down && Next_Direction ==Up）
    如果目前有楼层请求Up，先检查是否有电梯时现在处于5状态而且判断是否请求的楼层数大于目前5状态的电梯的目的楼层，
    如果前面两项都满足那么就从5状态的电梯中选一个离请求楼层最近的去到达请求楼层。
    如果没有满足5状态的电梯那么从1态以及2态的电梯中选择一个离请求楼层最近的电梯去到达请求楼层。
    如果目前有一楼层请求Down，那么先判断是否有电梯满足5态以及请求楼层小于电梯目的楼层，那么从这些电梯中选择离请求楼层最近的电梯去到达请求楼层；
    如果没有满足的电梯那么从1态以及3态中选择一个离请求楼层最近的电梯去到达请求楼层。
3、测试程序：
=
    1）直接运行主程序类：ElevatorApplication.java
    2) 命令执行已经打好的jar包，在resources/jar下面，执行命令：java -jar 目录/elevator.jar
4、运行效果预览：
=
    ![image](https://github.com/lugh99/test/src/main/resources/img/elevator.png)
