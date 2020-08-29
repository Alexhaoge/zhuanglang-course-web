# zhuanglang-course-web
 A course resources web for junior high school students in Zhuanglang, Gansu, China.  
 Use Vue.js as front end and Spring Boot as back end.
 Demo site is [here](http://http://114.116.233.111:8080/).
 
 这是一个为甘肃庄浪县思源实验学校搭建的教学资源平台，用于收录甘肃初中的信息技术、音乐、体育、美术课程的教学资源。也是我2020年7月参加“南开大学服务学习：计算机教育与科技扶贫”课程的实践成果。  
 [网站](http://http://114.116.233.111:8080/)已开始试运行。

## Run and Deploy
### Initialization
1. Clone this repositroy.
2. Install MySQL and login as administrator.
3. Create database, user, and tables with the script [zhuanglang.sql](https://github.com/Alexhaoge/zhuanglang-course-web/blob/master/zhuanglang.sql).
4. Set `web.upload.path` in `zhuanglang/src/main/resources/application.properties` for diretory to store lesson resources like pdf or mp4.
### Run
Run these commands in project workspace. The website is at `http://localhost:8443/`
```
gradle npmBuild
gradle bootRun
```
We can also run front end and backend seprately by running `npm run dev` in `web` folder and `gradle bootrun` in `zhuanglang` folder.
### Deploy
We can deploy it in two ways.
#### wrap frontend and backend together in `war`
run `gradle build` and we can find the war file in `zhuanglang/build/libs`.
#### deploy seprately
We can also put static files of front end into nginx and run backend with tomcat.  
1. run `npm run build` in `web` folder and get front end in `web/dist`
2. copy `staic` and `index.html` to some where(say /var/www/html/zhuanglang) on the server and configure port 8080 by configuration files in `/etc/nginx/sites-enabled` or `conf.d`
3. run `gradle build` in `zhuanglang` and copy to some where on the server and run `java -jar zhuanglang-0.0.1-SNAPSHOT.war`  
4. Actually war is not needed since user will not directly access backend, so we can wrap backend into a `jar`.
    Remove these code in `zhuanglang/build.gradle`:
    ```
    build.dependsOn project(':web').getTasksByName('npmBuild', false)
    processResources {
        from(project(':web').file('dist').path) {
            into 'static'
        }
        from(project(':web').file('dist/index.html').path) {
            into 'templates'
        }
    }
    apply plugin: 'war'
    ```
    And then add these code:
    ```
    jar {
        baseName = 'zhuanglang'
        version = '0.0.1'
        manifest {
            attributes "Manifest-Version": 1.0,
                'Main-Class': 'xyz.alexhaoge.DemoApplication'
        }
    }
    ```
#### NOTE
Deploy on seprate port may bring **cross-orgin** access problems, the solution is to change the configuration of `$axios` in `main.js` and `site-enabled` in **Nginx**. Please take a look at [the production branch](https://github.com/Alexhaoge/zhuanglang-course-web/tree/production) and its [release](https://github.com/Alexhaoge/zhuanglang-course-web/releases/tag/v0.0.1) for reference.
## Acknowledge
The design of the whole website take reference from *Evan-Nightly's* [White-Jotter Project](https://github.com/Antabot/White-Jotter). Thanks a lot for his [tutorials](https://blog.csdn.net/Neuf_Soleil/article/details/88925013).
