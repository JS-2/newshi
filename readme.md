# README

![logo](README.assets/logo.jpg)

## **👨‍👨‍👧‍👦팀원 소개**

![logo](README.assets/Untitled.png)

                                                                     잘생긴 팀원들을 소개합니다.

[재성님의꿀보이스](README.assets/honeyvoice.m4a)

                                                                      꿀보이스 재성님의 '잘자요'

## 📑 프로젝트 소개

- **진행기간**: 2021.01.11 ~ 2021.02.19
- **웹사이트 이름**: NewsHi (뉴하)
- **목표**
    - 내가 만드는 기사 스크랩
    - 어려운 기사를 간단하게 볼 수 있는 플랫폼
    - 알짜배기 기사들만 모아 볼 수 있는 플랫폼
- 고객여정지도

    ![%5B%E1%84%83%E1%85%A2%E1%84%86%E1%85%AE%E1%86%AB%5D4%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20README%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%205ef98b6a2c644b4486b6509a31eb5c72/1.jpg](README.assets/1.jpg)

    ![%5B%E1%84%83%E1%85%A2%E1%84%86%E1%85%AE%E1%86%AB%5D4%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20README%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%205ef98b6a2c644b4486b6509a31eb5c72/2.jpg](README.assets/2.jpg)

- ER-Diagram

    >ER-Diagram
<details>
<summary>상세 내용 확인</summary>
<div markdown="1">

div 에 markdown attribute 를 1 로 
하는 이유는 div 안에서
markdown 을 사용하기 위해서 입니다.


</div>
</details>
    ![%5B%E1%84%83%E1%85%A2%E1%84%86%E1%85%AE%E1%86%AB%5D4%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%91%E1%85%A7%E1%86%BC%E1%84%80%E1%85%A1%E1%84%85%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B1%E1%84%92%E1%85%A1%E1%86%AB%20README%E1%84%8C%E1%85%A1%E1%86%A8%E1%84%89%E1%85%A5%E1%86%BC%205ef98b6a2c644b4486b6509a31eb5c72/ERDiagram.jpg](README.assets/ERDiagram.jpg)

- 와이어프레임

    ![와이어프레임](README.assets/wireframe.jpg)

## ⚙️ 개발환경

- Vue.js
- Vuetify
- Axios
- Spring Boot
- Maria DB
- Mybatis
- Gradle
- JAVA8(zulu open jdk)

## 🛠️📑 Install / Usage

1. 'newshi' directory로 이동

```bash
cd newshi
```

2. package설치

```bash
$ npm -i
or 
$ yarn -i
```

3. run server

```bash
$ npm serve
or 
$ yarn serve
```

## 🕹️주요 기능

- 뉴스피드
    - 구독중인 큐레이터들의 게시 포스트 목록 조회
    - 나의 관심 분야를 태그로 갖는 기사 목록 조회
    - 최신 포스트 조회
- 포스트/기사 조회
    - 기사 본문 조회
    - 기사 요약 조회
    - 큐레이터의 의견 및 요약 조회
    - 좋아요/싫어요 기능
    - 저장 기능
- 채널
    - 기사 스크랩 기능
    - 큐레이터별 스크랩 내 기사 좋아요 싫어요
    - 나중에 볼 기사 저장 기능
    - 구독 기능
    - 커뮤니티 게시판
- 검색
    - 태그 검색
    - 큐레이터 검색
- 마이페이지
    - 구독중인 큐레이터 목록 조회
    - 나를 구독중인 회원 목록 조회
    - 프로필 사진
    - 관심 분야 태그 목록 조회
- 회원 관리 기능
    - 회원가입
    - 로그인/로그아웃
    - 비밀번호 찾기(변경)

## 📌 기술 특장점

### **📃 Swagger Hub를 이용한 API 명세서 작성**

Swagger Hub를 이용해 API 명세서를 작성함으로써 FE/BE 협업을 쉽게 할 수 있도록 했습니다. 실제 사용되는 Parameter로 테스트할 수 있고, 어떤 방식으로 데이터를 주고받을지 확인할 수 있어서 개발 시간을 단축하고 불필요한 의사소통 비용을 줄일 수 있었습니다

---

### 🌈 **JIRA를 통한 Task 관리**

 JIRA를 통해 에픽, 스토리, 테스크등을 생성하여 스프린트를 계획하고 팀 전체가 볼  수 있도록 배포하였습니다. 이를 바탕으로 전반적인 팀 업무의 우선순위를 정하고 이에 대해 논의하며 업무를 수행하였습니다. JIRA와 Git lab 그리고 MatterMost를 서로 연동하여 실시간으로 팀원들이 수행한 업무를 확인할 수 있어서 시너지 효과를 발휘할 수 있었습니다.

---

### 📕 **Vuex를 사용한 데이터 관리**

Vuex는 Vue.js 애플리케이션에 대한 상태 관리 패턴 + 라이브러리로써, 컴포넌트 간 지속적으로 사용해야하고, 변경이 용이할 수 있는 데이터를 보관 및 비동기적으로 작업을 처리하기 위해 채택하였습니다.

---

### **📄Notion을 통한 회의록 관리**

노트, 일정, 업무, 데이터, 프로젝트 등을 효율적으로 생성하고 관리할 수 있는 All-in-one 생산성 도구이자 협업 툴입니다. 그래서 프로젝트에 대한 의견 및 회의록을 공동 작업하기 위해 채택하였습니다. 
매주 새로운 워크스페이스를 만들고, 워크스페이스 내에서 일일 스크럼, 앞으로의 일정, 기타 배운점 및 서로의 아이디어를 공유하여 팀원 간 의견을 편히 나눌 수 있었습니다.

---