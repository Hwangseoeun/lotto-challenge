## 목차
[1. 미션 목표](#-미션-목표)\
[2. 미션 STEP](#-미션-step)\
[3. 각 STEP 별 작업 내용 PR](#-각-step-별-작업-내용-pr)\
[4. Main 브랜치 프로그램 실행 방법](#-main-브랜치-프로그램-실행-방법)\
[5. 미션 기획 내용](#-미션-기획-내용)\
[6. STEP 01 추가 내용](#-추가-기획-구체화-step-1)\
&nbsp;&nbsp;&nbsp;&nbsp;[6-1. 추가 기획 구체화](#-추가-기획-구체화-step-1)\
&nbsp;&nbsp;&nbsp;&nbsp;[6-2. DB 저장할 내용](#-db-저장할-내용-step-1)\
[7. 미션을 하며 시도하는 새로운 도전들](#-미션을-하며-시도하는-새로운-도전들)


## 🎯 미션 목표
```주요 목표``` : 3주차 미션이었던 Lotto 코드가 정말로 확장성에 용이한 코드인가? 테스트 해보기
1. 콘솔 프로그램에 웹 인터페이스를 추가했을 때 기존에 작성했던 Controller가 변경되지는 않았는가? 테스트해보기
2. 기존에 사용하던 외부 인프라를 다른 것으로 대체했을 때 도메인이나 메인 로직에 큰 변경을 끼치지 않는가? 테스트해보기


## 🌱 미션 STEP
STEP 1. 3주차 미션인 Lotto 프로그램에 기획 추가하기 + JDBC 템플릿 사용 및 MySQL DB 추가

STEP 2. 기존 콘솔 프로그램에 웹 인터페이스 추가하기 + spring 추가하여 의존성 주입하기

STEP 3. 스텝 1에서 사용한 JDBC 템플릿을 JPA로 변경하기


## ⭐ 각 STEP 별 작업 내용 PR
STEP 01 : https://github.com/Hwangseoeun/lotto-challenge/pull/1

STEP 02 (stop) : https://github.com/Hwangseoeun/lotto-challenge/pull/2

STEP 02 (improve) : https://github.com/Hwangseoeun/lotto-challenge/pull/3

STEP 03 : https://github.com/Hwangseoeun/lotto-challenge/pull/4


## 🎰 Main 브랜치 프로그램 실행 방법
1. application.yml 파일의 spring.jpa.hibernate.ddl-auto를 ```create```로 변경한다.
    ```Yml
    # yml 경로 : lotto-challenge/src/main/resources/application.yml
    spring:
        jpa:
            hibernate.ddl-auto: create
   ```


2. docker를 띄우기 위한 ```docker network```를 설정해준다.
    ```Shell
    # docker-compose.yml 파일이 있는 위치의 경로로 들어가 터미널에 다음 명령어를 입력한다.
   # docker-compose.yml 경로 : lotto-challenge/docker/docker-compose.yml
    docker network create lotto-net
   ```


3. docker-compose.yml의 ```mysql 컨테이너```를 실행시킨다.
    ```Shell
   # docker-compose.yml 경로 : lotto-challenge/docker/docker-compose.yml
   docker-compose up mysql -d
   ```


4. Spring Boot 어플리케이션의 ```Docker 이미지를 빌드```한다.
    ```Shell
   # 최상위 경로에서 해당 명령어를 입력한다.
   # 경로 : lotto-challenge
   docker build -f docker/Dockerfile -t lotto-challenge-web .
   ```


5. docker-compose.yml의 ```web 컨테이너```를 실행시킨다.
    ```Shell
   # docker-compose.yml 경로 : lotto-challenge/docker/docker-compose.yml
   docker-compose up web -d
   ```


6. application.yml 파일의 spring.jpa.hibernate.ddl-auto를 ```none```으로 변경한다.
    ```Yml
    # yml 경로 : lotto-challenge/src/main/resources/application.yml
    spring:
        jpa:
            hibernate.ddl-auto: none
   ```


## 🚀 미션 기획 내용
로또 구매 및 그동안의 로또 통계를 조회할 수 있는 사용자 프로그램을 구현한다.
- 사용자는 새로운 로또를 발급할지, 그동안의 로또 수익률을 조회할지 선택한다.
- 사용자는 새로운 로또를 발급하기 위해 이메일과 구매 금액을 입력한다.
- 발행한 로또 내역들을 출력한다.
- 로또 당첨에 대한 결과를 발행 즉시 출력한다.
- 새로운 로또를 발급하는 로직은 3주차 미션의 Lotto 요구사항과 동일하다.
- 로또 당첨 번호와 보너스 번호는 사용자가 직접 입력하는 것이 아닌 특정 값으로 정해져있다.(3주차 미션 Lotto와 다른 부분)
- 그동안의 로또 통계는 당시 로또 구매 금액과 수익률을 함께 출력한다.

1. 새로운 로또 발급

<img width="645" height="685" alt="Image" src="https://github.com/user-attachments/assets/bd44ab62-0a06-4c06-8729-53ab7f8c9b61" />

2. 로또 수익률 조회

<img width="656" height="449" alt="Image" src="https://github.com/user-attachments/assets/fd7d6e88-06ea-4cf5-ae8b-85b9e7b2aaf7" />


## 🌗 추가 기획 구체화 (STEP 1)
```default``` : 프리코스 3주차 미션인 Lotto 프로그램 기반

- 사용자로부터 새로운 로또를 발급할지, 그동안의 로또 수익률을 조회할지 여부를 입력받는 기능
    - 조건 :
        1. 새로운 로또 발행하기
        2. 그동안의 로또 구매 금액 및 로또 수익률 조회하기
        3. 종료
    - 에러 : ```IllegalArgumentException``` 발생
        1. "[ERROR] 공백은 입력할 수 없어요."
        2. "[ERROR] 시작 옵션은 숫자로만 입력해야 해요."
        3. "[ERROR] 유효하지 않은 선택지에요."


- 사용자로부터 이메일을 입력받는 기능
    - 조건 :
        1. 유효한 이메일 형식인지 검증
        2. 공백이 포함되어 있는지 검증
    - 에러 : ```IllegalArgumentException``` 발생
        1. "[ERROR] 올바르지 않은 이메일 형식이에요."
        2. "[ERROR] 이메일에 공백은 입력할 수 없어요."


- 사용자가 입력한 이메일을 저장하는 기능


- 구매한 로또 금액과 수익률을 저장하는 기능


- 사용자가 입력한 이메일을 기반으로 그동안의 수익률을 조회하는 기능


## 🌓 DB 저장할 내용 (STEP 1)
1. 사용자가 입력한 이메일
2. 사용자가 로또 발급 시 구매한 로또의 금액과 수익률

<img width="1084" height="443" alt="Image" src="https://github.com/user-attachments/assets/16ef35db-3a1c-45ad-99c9-570ec2f57e91" />


## 📢 미션을 하며 시도하는 새로운 도전들
1. **JDBC 템플릿 사용해보기**\
-> 그동안 프로젝트를 하면 항상 JPA만 사용해왔기에, 이번에는 직접 SQL을 다루는 JDBC 템플릿을 활용해 데이터 접근 방식을 경험하기 위한 도전


2. **기존에 작성했던 순수 자바 프로그램에 spring 적용해보기**\
-> 스프링 프로젝트 초기 세팅 때 사용하던 Spring Initializr를 사용하는 것이 아닌, 기존 자바 프로젝트에 직접 Spring 환경을 구성해보며 프레임워크 통합 과정을 이해하기 위한 도전


3. **콘솔 프로그램과 웹 프로그램이 공존하는 형태로 구현해보기**\
-> 지금까지는 콘솔 기반 또는 웹 기반으로 각각 따로 개발해왔기에, 두 환경이 함께 동작하는 구조를 직접 설계하고 구현해보기 위한 도전


4. **사용 중인 외부 기술 교체해보기**\
-> 그동안 JPA에만 익숙해 외부 기술 교체를 고려하지 않았으나, 동일한 기능을 다른 기술로 구현해보며 확장성을 고려하는 능력을 키우기 위한 도전



