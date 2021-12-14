# mac上配置Apache服务器

参考文章：

https://blog.csdn.net/wanxue0804/article/details/79434058



### 一、启动Apache:

终端：

```bash
//开启apache:  sudo apachectl start

//重启apache:  sudo apachectl restart

//关闭apache:  sudo apachectl stop
```

![image-20211208163230302](https://tva1.sinaimg.cn/large/008i3skNgy1gx6id98sehj31840i0n13.jpg)

回车会提示输入密码，也就是你电脑的密码

http://127.0.0.1/测试一下，成功:

![image-20211208163142124](https://tva1.sinaimg.cn/large/008i3skNgy1gx6ice478vj30rk0aejro.jpg)



### 二、配置

#### 1、首先在自己的电脑昵称的文件夹下 建一个sites文件夹

里面随便放上一些后台的假数据。

我的是10.13.2版本的，记住文件夹要放在User文件夹下，否则好像会失败 新版的位置是Macintosh HD > 用户 > 你的电脑名

![image-20211208163419512](https://tva1.sinaimg.cn/large/008i3skNgy1gx6if4jjizj30jm0o6q4n.jpg)





### 2.找到配置文件，给原来文件备份

接下来都是在终端下操作 输入下面指令（$后面的代码可以直接复制）

// 切换工作目录

```bash
$cd /etc/apache2
```

//  备份文件，以防不测，只需要执行一次就可以了

```bash
$sudo cp httpd.conf httpd.conf.bak
```



![image-20211208163704677](https://tva1.sinaimg.cn/large/008i3skNgy1gx6ikd4vtcj31500d0q5d.jpg)



### 3.开始修改配置文件

// 用vim编辑httpd.conf

```bash
$sudo vim httpd.conf
```

<!--我是用“sudo open httpd.conf”-->

// 查找DocumentRoot  

```bash
/DocumentRoot
```

按下 i 进入编辑模式

可以看到有两个路径 把他们都改成你刚才建的那个Sites 文件夹的路径

![image-20211208163909114](https://tva1.sinaimg.cn/large/008i3skNgy1gx6ik5aat8j30u00zqjxw.jpg)

![image-20211208165048568](https://tva1.sinaimg.cn/large/008i3skNgy1gx6iwbep1kj314k0qcgp0.jpg)



附上vim

【yy】 复制光标所在的那一行
【nyy】 复制光标所在的向下n行

【p,P】 p为将已经复制的数据在光标下一行粘贴；P为将已经复制的数据在光标上一行粘贴

【/word】 在文件中查找内容为word的字符串（向下查找）

向前滚动一屏：Ctrl+F

向后滚动一屏：Ctrl+B

【u】 撤消上一个操作



再查找下 php

```bash
/php
```

定位到这一行后把这行最前面的#删除

![image-20211208165454163](https://tva1.sinaimg.cn/large/008i3skNgy1gx6j0jyg0bj314k0qcdku.jpg)





（**如果是10.10以上的系统的话还有一步：**查找Options 输入

```bash
/Options 
```

也可以目测自己找到图中的位置，在Options和Follow之间增加一个单词）

![image-20211208165826435](https://tva1.sinaimg.cn/large/008i3skNgy1gx6j48ryt1j311i0u0dkn.jpg)

改好之后先按下esc键退出编辑模式，再输入:wq 保存并退出 如果打错了不想保存就是 :q!



### 4.收尾工作与确认成功

//切换到工作目录

```bash
$cd /etc
```

//拷贝配置文件

```bash
$sudo cp php.ini.default php.ini
```

// 重新启动apache服务器 之后下面说这句话是正常的

```bash
$sudo apachectl -k restart
```

之后下面说这句话是正常的不用担心



![image-20211208165959556](https://tva1.sinaimg.cn/large/008i3skNgy1gx6j5uf4vkj313c0lo772.jpg)



![image-20211208170125320](https://tva1.sinaimg.cn/large/008i3skNgy1gx6j7bm1g1j314k088taj.jpg)

再确认下到底成功了没有，就到浏览器里输入localhost如果能来到下面界面就对了

![image-20211208170237979](https://tva1.sinaimg.cn/large/008i3skNgy1gx6j8l2rgdj311w0u0q60.jpg)

### 5.注意事项

注意前面的备份。不要多次备份。

注意再vim编辑下全部使用英文符号和字母

每次关机开机之后再想用服务器就要重新敲下开启的指令







