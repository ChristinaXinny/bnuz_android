数据放在根下的sites



先终端开启apache服务器

```bash
sudo apachectl start
```

或者

```bash
sudo apachectl -k restart
```

回车会提示输入密码，也就是你电脑的密码

http://127.0.0.1/测试一下，成功:返回it works！



直接运行项目



如果不行可以查看一下这个

这个是当前的网络的ip

![image-20211208222716990](https://tva1.sinaimg.cn/large/008i3skNgy1gx6smdj6h1j30ik0g1aav.jpg)

![image-20211208222628044](https://tva1.sinaimg.cn/large/008i3skNgy1gx6slkn8u8j312s0giq6s.jpg)

随机分配的可能会改变

以及看看在setes文件中的htdocs. NewsInfo

![image-20211208223447474](https://tva1.sinaimg.cn/large/008i3skNgy1gx6su6vsf4j30gz0caabl.jpg)







