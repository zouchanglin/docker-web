Manage docker for Spring Boot!

这是一个可视化的Docker管理系统，主要包含了镜像管理、容器管理、宿主机监控的一些功能

# 一、镜像管理
## 1、查看镜像列表
![mark](https://img.zouchanglin.cn///20200509/DTfH9aBeec5n.png)
在这里可以把镜像推送到阿里云镜像仓库，非常方便。

## 2、搜索/拉取镜像
![mark](https://img.zouchanglin.cn///20200509/PbXA6xjBLRjv.png)
点击download即可获取到该镜像的latest版本。

## 3、通过DockerFile构建镜像
![mark](https://img.zouchanglin.cn///20200509/GgHpJvIcGENV.png)
上面的图中是一个把本工程给打包成镜像的操作示意，即把Dockerfile与Jar包打成Zip格式的压缩包，然后在网页上选择上传此压缩包即可：
![mark](https://img.zouchanglin.cn///20200509/zlt64u7xNNaF.png)

# 二、容器管理
## 1、创建容器
由于平时的容器数据卷不需要配置多组，包括端口和环境变量也是一样的，无需配置多组，所以在这里我就只设置了一组配置
![mark](https://img.zouchanglin.cn///20200509/kQCfHyajXwvS.png)

## 2、分状态查看/操作容器
![mark](https://img.zouchanglin.cn///20200509/BBgBndXlm9kn.png)
不同状态的容器对应着不同的操作，比如对于已退出的容器，就有启动和删除的操作：
![mark](https://img.zouchanglin.cn///20200509/xQ0pdnkg7CYu.png)

# 三、宿主机监控
这里因为最后是需要把项目直接一键部署到Docker，所以不做任何硬件资源限制的容器监控的环境就是宿主机的环境
## 1、基本信息
![mark](https://img.zouchanglin.cn///20200509/ea8xHXbGLkhH.png)
## 2、磁盘监控
![mark](https://img.zouchanglin.cn///20200509/Hac08395OM0J.png)
## 3、CPU监控
![mark](https://img.zouchanglin.cn///20200509/s9r4pOMQD43K.png)
## 4、内存监控
![mark](https://img.zouchanglin.cn///20200509/wy0RSxTKRgf7.png)

 # 四、TODO List
 * 需要一个应用商店（常用软件一键式部署）
 * 自动配置IP地址
 * 自动配置仓库地址