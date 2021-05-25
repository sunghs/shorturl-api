# shorturl-api
단축 URL 을 제공하는 API 입니다.<br/>

## 1. Environment
- Java11 (amazon-corretto)
- Gradle 6.8.3
- Spring Boot 2.5.0 (Framework 5.3.7)
- IDE : IntelliJ IDEA 2020
- DB : MySQL 5.7
- OS : MacOS & Windows 10
<br/>

## 2. 설치 및 환경 구성
### 2-1. DB는 docker 의 mysql official image로 개발환경을 구성하였습니다.

```
docker run -d -p 10000:3306 -e MYSQL_ROOT_PASSWORD=password --name mysql mysql:5.7
```

테이블 명을 소문자로 사용하므로 내부의 docker.cnf 의 수정이 필요합니다.

```sh
$ apt update && apt-get install vim

$ vi /etc/mysql/conf.d/docker.cnf

lower_case_table_names=1 추가
```
<br/>

### 2-2. 소스 설치 및 빌드
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

<br/>

## 3. URL 입력 및 결과 출력으로 swagger docs를 제공합니다.
Application 구동 후 Context의 Root Uri 진입 시 redirect 됩니다.  
(예) http://localhost:8080/

<br/>

## 4. 기능 설명

### 4-1. `/url/short (POST)`
원본 URL을 단축 URL로 요청합니다.

### 4-2. `/url/original (POST)`
단축 URL을 원본 URL로 변경하여 정보를 가져옵니다.

### 4-3. 단축 URL을 브라우저에 입력 시 목적 URL로 redirect 됩니다.
예) https://www.google.com?a=b&c=d 을 http://localhost:8080/ABCD 라는 단축 URL로 변경 후,  
http://localhost:8080/ABCD 입력 시 https://www.google.com?a=b&c=d 로 이동합니다.
