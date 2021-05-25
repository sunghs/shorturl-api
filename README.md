# shorturl-api
단축 URL 을 제공하는 API 입니다.

## Environment
- Java11 (amazon-corretto)
- Gradle 6.8.3
- Spring Boot 2.5.0 (Framework 5.3.7)
- IDE : IntelliJ IDEA 2020
- DB : MySQL 5.7
- OS : MacOS & Windows 10


## 설치 및 환경 구성
### DB는 docker 의 mysql official image로 개발환경을 구성하였습니다.

```
docker run -d -p 10000:3306 -e MYSQL_ROOT_PASSWORD=password --name mysql mysql:5.7
```

테이블 명을 소문자로 사용하므로 내부의 docker.cnf 의 수정이 필요합니다.

```sh
$ apt update && apt-get install vim

$ vi /etc/mysql/conf.d/docker.cnf

lower_case_table_names=1 추가
```

### 소스 설치 및 빌드
embedded tomcat 을 사용하여 서비스를 구동 할 수 있습니다.

#### clone
```sh
$ git clone https://github.com/sunghs/shorturl-api
```

#### build
```sh
$ cd shorturl-api

$ ./gradlew bootjar
```

#### execute
```sh
$ java -jar ./build/libs/shorturl-api-1.0.0-SNAPSHOT.jar
```


## URL 입력 및 결과 출력으로 swagger docs를 제공합니다.
Application 구동 후 Context의 Root Uri 진입 시 redirect 됩니다.  
(예) http://localhost:8080/

## 생성 된 단축 URL을 브라우저에 요청 시 원본 URL로 redirect 됩니다.
