## 🌱직짱건강을 소개합니다 ! 🏥

<p align="center">
    <img src="https://github.com/SWYP-3rd-period-1-team/Swyp1team-backend/assets/58305106/8b37e47a-943b-4cd3-b628-58224d1fcf1a" alt="pick-git-logo" width="320" height="320">
</p>

<div align="center">

  💻 나에게 건강을 맞겨줘 ! 종합 건강관리 플랫폼, <br>
  🖋 [직짱건강]()

</div>
<br/>


## 🧑🏻‍💻 기술 스택
### BE
<img src="https://img.shields.io/badge/Java 17-007396?style=flat&logo=java&logoColor=white"/>

<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=spring&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/Spring MVC-6DB33F?style=flat&logo=spring&logoColor=white"/>  <img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=flat&logo=spring&logoColor=white"/> <img src="https://img.shields.io/badge/JPA-orange?style=flat&logo=JPA&logoColor=white"/> <img src="https://img.shields.io/badge/Hibernate-orange?style=flat&logo=Hibernate&logoColor=white"/> <img src="https://img.shields.io/badge/Querydsl-orange?style=flat&logo=querydsl&logoColor=white"/> <img src="https://img.shields.io/badge/Junit5-blue?style=flat&logo=Junit5&logoColor=white"/>

<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white"/> 

<img src="https://img.shields.io/badge/EC2-FF9900?style=flat&logo=amazonec2&logoColor=white"/> <img src="https://img.shields.io/badge/RDS-527FFF?style=flat&logo=amazonrds&logoColor=white"/> <img src="https://img.shields.io/badge/ELB-FF9900?style=flat&logo=amazon elb&logoColor=white"/> <img src="https://img.shields.io/badge/Api Gateway-FF9900?style=flat&logo=amazonapigateway&logoColor=white"/> 

<img src="https://img.shields.io/badge/GithubActions-2088FF?style=flat&logo=githubactions&logoColor=white"/> <img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white"/> <img src="https://img.shields.io/badge/redis-DC382D?style=flat&logo=redis&logoColor=white"/>

## 💻 개발자
- Back-end : [최동훈](https://github.com/ulsandonghun), [최지웅](https://github.com/wooni423)



## ⚙️ 직짱건강 프로젝트는 이렇게 배포돼요!


<img src="https://github.com/SWYP-3rd-period-1-team/zigzzang-backend/assets/58305106/280aeb89-089c-4a8d-9b85-a44d1f977e2c" alt="pick-git-logo" width="80%" style="height: auto;">

###   [feature branch workflow](https://gmlwjd9405.github.io/2017/10/27/how-to-collaborate-on-GitHub-1.html)에 따라 개발을 진행합니다

1. git clone 명령으로 중앙 원격 저장소를 복제해 자신의 로컬 저장소를 만듭니다

   `git clone [중앙 remote repository URL]`

2. 새로운 기능을 추가하고 싶다면 feature/xxx 형태의 브랜치를 dev에서 분기합니다

   `git checkout -b feature/login`

3. 새로 만든 브랜치에 대한 새로운 기능에 대한 내용을 커밋합니다

   `git add .`

   `git commit -m “[ 커밋 컨벤션에 따라 작성한다 ]”`

   자세한 커밋 컨벤션은 아래를 참고해주세요!
4. 커밋을 완료했다면, 자신이 작업한 내용을 포함한 브랜치를 중앙 원격 저장소에 올립니다

   `git push -origin feature/login branch`
5. PR 요청 후 프로젝트 관리자는 문제가 없다면 dev브랜치에 병합합니다.

6. 만약 실제 배포과정시, 모든 테스트가 완료된 dev 브랜치를 main 브랜치로 최종검수후 merge 합니다.

*주의*
Default 브랜치는 dev 입니다 ! 


###  저희는 다음과 같은 commit convention에 따라 개발을 진행합니다

#### 커밋메시지 형식
    [type] : subject
    blank line
    body
    blank line
    footer

📌 Type 제목 부분
1. 속성별 Type
* feat: 새로운 기능, 특징 추가
* add : 단순 코드 추가
* fix: 수정, 버그 수정
* docs: 문서에 관련된 내용, 문서 수정
* style: 코드 포맷, 세미콜론 누락, 코드 변경이 없을 경우
* refactor: 리팩토링
* test: 테스트 코드 수정, 누락된 테스트를 추가할 때, 리팩토링 테스트 추가
* chore: 빌드 업무 수정, 패키지 매니저 수정

2. 범위를 나타내는 scope

   생략가능

3. 제목을 간단하게 설명하는 subject

* 제목은 최대 50글자가 넘지 않도록 한다.
* 마침표 및 특수기호는 사용하지 않는다. 끝에 점(.) 없음
* 영어로 작성시 첫 글자를 대문자로 쓰지 않는다.
* 영문으로 표기하는 경우 동사(원형)를 가장 앞에 둔다.
* 제목은 개조식 구문으로 작성한다. (간결하고 요점적인 서술)

📌 body 본문부분

📌 footer 꼬릿말부분


📌 gitmoji 활용

예시 `git commit -m "[style]: subject"`

[자세한 내용은 링크를 참조](https://velog.io/@ninto_2/%EC%BB%A4%EB%B0%8B-%EC%BB%A8%EB%B2%A4%EC%85%98)
