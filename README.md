# 🌸 COSMOS(코스모스-코딩 스터디, 모두 함께 스터디)

비대면 화상 IT 스터디 플랫폼



## 🤼‍♂️ 팀원 소개
![image-20220218015429409](./exec/산출물/팀원소개.png)

##  🕹Git 컨벤션 개요

Conflict를 방지하고, 효과적이고 명확한 협업을 진행하고자

1️⃣ **Git-Flow** 브랜치 전략을 도입하여 계층별 브랜치를 관리했습니다

2️⃣ [AngularJS Commit Conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153)를 참고하여 **Commit 컨벤션**을 정의했습니다

3️⃣ 작업의 시작 전 JIRA 티켓을 생성하고, 모든 커밋과 티켓을 연결했습니다

위와 같은 세 가지 협업 규칙을 세우고 프로젝트를 진행했습니다.



## 🏞 Git Flow

```
master   
└ develop  
  ├ front - feature/front/기능...  
  └ back - feature/back/기능...
  
```

- master : 운영 서버로 배포하기 위한 브랜치
- develop : 다음 출시 기능을 개발하는 브랜치
- front : 프론트엔드 개발하는 브랜치
- back : 백엔드를 개발하는 브랜치
- feature : 세부 기능을 개발하는 브랜치



## 😎 Commit Convention

> [type] commit message

- Type

  - **Fix** : 잘못된 동작을 고칠 때

    > fix function/error/typo in style.css

  - option

    - funtion : 고친 함수 명 (e.g. fix login function in index.html)
    - error : 수정한 에러 (e.g. fix [구체적 에러명] error in login.js)
    - typo : 오타 (e.g. fix typo in style.css)

  - **add** : 새로운 것을 추가할 때

    > add mytest.test for test (새로운 파일 추가 시)

    > add blue color to style.css (기존 파일에 내용 추가 시)

  - **move** : 코드나 파일을 이동할 때

    > move A to B (e.g. A를 B로 이동할 때)

  - **rename** : 이름 변경이 있을 때

    > rename A to B (e.g. A를 B로 이름을 변경할 때)

  - **update** : 정상적으로 동작하는 파일을 보완하는 경우

    > update test.js to use HTTPS (test.js에 기존의 프로토콜에서 HTTPS 프로토콜 사용으로 변경)

  - **remove** : 삭제가 있을 때

    > remove test.js (파일 삭제 시)

    > remove black color from style.css (파일 내 부분 삭제 시)

- commit message: 변경 사항에 대해 명확하게 기술





## 🖥 프로젝트 개요

<img src ="https://img.shields.io/badge/service-Web-red"></img><img src ="https://img.shields.io/badge/frontend-Vue-green"></img><img src ="https://img.shields.io/badge/backend-SpringBoot-skyblue"></img><img src ="https://img.shields.io/badge/database-MySQL-silver"></img> <img src ="https://img.shields.io/badge/server-EC2, Docker-gold"></img><img src ="https://img.shields.io/badge/webRTC-openVidu-pink"></img>



- **진행 기간** : 2022.01.10 ~ 2022.02.18

- **목표**
  
  - webRTC 기술을 사용해 비대면 화상 IT 스터디 플랫폼을 만듭니다.
  - 스터디 모집과 스터디 활동을 모두 지원하는 스터디 플랫폼을 목표로 합니다.
  - 서비스를 이용하는 사람들로 하여금 공부에 대한 자극을 주는 서비스를 제공합니다.
  
  

- **🗺와이어프레임**(figma)

  ![필수기능_figma](./exec/산출물/필수기능_figma.png)

  ![image-20220218015011885](./exec/산출물/필수기능2_figma.png)



- ERD

  ![ERD](./exec/산출물/ERD.png)





## 🐱‍💻 프로젝트 소개

### 기획배경

- 높아진 IT에대한 관심

- 독서실의 가격 상승
- 코로나19 심화로 카페, 동아리방 등 공부하기가 걱정되는 환경
- 요리조리 왔다갔다 여러가지 플랫폼 사용으로 번거로운 스터디 활동



## 📌주요 기능

### 🔑공개 스터디

> study with me 와 같은 오픈된 캠스터디

- 권한(권한이 있는 사람은 강퇴할 수 있음)

  - 권한 부여 기능

  ![권한](./exec/산출물/권한부여.gif)

  

  - 강퇴 기능

  ![강퇴시연](./exec/산출물/강퇴시연.gif)

  

- 스톱 워치

- 마이크,카메라 & 채팅



### 🔒비공개 스터디

> 같은 목표를 가진 소수의 사람들로 구성된 스터디

- 타이머(모든 사람이 같은 타이머를 보고 )

  ![타이머종료](./exec/산출물/타이머종료.gif)

  

- 화면공유

  ![타이머종료후 화면공유](./exec/산출물/타이머종료후 화면공유.gif)

  

- 상벌점 기능

  ![비공개점수주기](./exec/산출물/비공개점수주기.gif)



## 🛠 프로젝트 빌드

- Frontend/Backend

  [프론트엔드/백엔드](./exec/산출물/프로젝트_빌드_및_배포_문서)



## ⚙ 시스템 환경 및 구성

- OS: Windows10
- Backend Framework: Spring Boot 2.4.5
- Frontend Framework: Vue 2
- DB: mysql  Ver 8.0.27 for Win64 on x86_64 (MySQL Community Server - GPL)
- WAS: Gradle
- JVM: openJDK (1.8.0_192)
- Node.js: 16.13.1
- WebRTC: openVidu 2.20.0
- Docker: 20.10.12
- WEB: Nginx (1.18.0)



## 🎞 최종산출물

[시연영상](./exec/공통PJT_부울경_1반_E103_시연영상.mp4)

