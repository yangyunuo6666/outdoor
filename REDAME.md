[toc]

***

# outdoor
+ nvm 安装14.20.0Node失败：
  - 从node管理下载包，解压后，放在nvm安装目录下。
  - (官网)[https://nodejs.org/download/release/]
  - 缺失sass：
    - npm install node-sass@^4.14.1 --registry=https://registry.npmmirror.com --sass_binary_site=https://npmmirror.com/mirrors/node-sass/
    - npm install node-sass@^3.4.4 --registry=https://registry.npmmirror.com --sass_binary_site=https://npmmirror.com/mirrors/node-sass/
  - 安装 canvas@2.9.3 证书失效：临时设置不验证证书安装：npm config set strict-ssl false
    - npm install canvas@2.9.3 --registry=https://registry.npmmirror.com
  - npm audit fix//自动修复漏洞 
+ cnpm 安装失败：
  - 安装：`npm install -g cnpm --registry=https://registry.npmmirror.com`
  - 文件占用：直接重启
  - 版本过高安装匹配14node的：`npm install -g cnpm@7.1.0 --registry=https://registry.npmmirror.com`
+ cnpm安装包注意事项：
  - 项目所在磁盘格式：NTFS，否则会报创建符号链接失败错误，因其是淘宝源。
+ npm
  - 常用选项（选择淘宝镜像）：--registry=https://registry.npmmirror.com

