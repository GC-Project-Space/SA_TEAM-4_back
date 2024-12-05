<a href="https://club-project-one.vercel.app/" target="_blank">
</a>

<br/>
<br/>

# 0. Getting Started (시작하기)

[서버 링크](http://43.201.26.216) (배포중지)

<br/>
<br/>

# 1. Project Overview (프로젝트 개요)
- 프로젝트 이름: 가마고치 리팩토링 
- 프로젝트 설명: 학교 내에서 걸어다니며 포인트를 획득하고, 미션을 수행하여 이동을 수단이 아닌 행동으로, 만드는 어플리케이션

<br/>
<br/>

# 2. Team Members (팀원 및 팀 소개)
| 주성재 | 이도연 | 임석현 | 변영은 |
|:------:|:------:|:------:|:------:|
| <img src="https://avatars.githubusercontent.com/u/150310988?v=4" alt="주성재" width="150"> | <img src="https://github.com/user-attachments/assets/653c94e3-5837-4e40-8ee9-b0ff135b59e7" alt="이도연" width="150"> | <img src="https://avatars.githubusercontent.com/u/117873805?v=4" alt="임석현" width="150"> | <img src="https://avatars.githubusercontent.com/u/155031676?v=4!" alt="변영은" width="150"> |
| BE | DevOps, BE | BE | BE |
| [GitHub](https://github.com/hashedbrown01) | [GitHub](https://github.com/doup2001) | [GitHub](https://github.com/ssklim) | [GitHub](https://github.com/zxcvye) |

<br/>
<br/>

# 3. System Architecture (구성도)
<img width="760" alt="수정1차" src="https://github.com/user-attachments/assets/e3567cec-d812-4264-9e48-20f53553c2fc">

<br/>
<br/>

# 4. Key Features (주요 기능)
- **회원가입**:
  - 회원가입 시 DB에 유저정보가 등록됩니다.

- **로그인**:
  - JWT를 통해 로그인합니다.

- **미션 부여**:
  - DB에 존재하는 미션을 매일 초기화되도록 하여, 아직 수행하지않은 미션을 부여합니다.

- **미션 조회**:
  - 미션의 내용, 미션의 성공여부 및 좌표가 보여집니다.

- **미션 수행 및 걸음수 등록**:
  - 미션이 수행되면, 완료 표시 됩니다.
  - 프론트 측에서 넘어오는 걸음수를 저장할 수 있습니다.

- **랭킹 기능**:
  - 랭킹에서 학과별 순위를 확인할 수 있습니다.
  - 랭킹에서 개인별 순위를 확인할 수 있습니다.
    
<br/>
<br/>

# 5. Tasks & Responsibilities (작업 및 역할 분담)
|  |  |  |
|-----------------|-----------------|-----------------|
| 주성재    |  <img src="https://avatars.githubusercontent.com/u/150310988?v=4" alt="주성재" width="100"> | <ul><li>팀장</li><li>미션 조회 기능</li><li>미션 완료 기능</li></ul>     |
| 이도연   |  <img src="https://github.com/user-attachments/assets/653c94e3-5837-4e40-8ee9-b0ff135b59e7" alt="이도연" width="100">| <ul><li>젠킨슨 CI/CD</li><li>AWS 서버 배포</li><li>nginx blue-green 무중단배포</li> <li>유저 마이페이지 조회</li></ul> |
| 임석현   |  <img src="https://avatars.githubusercontent.com/u/117873805?v=4" alt="임석현" width="100">    |<ul><li>유저 회원 가입 </li><li>JWT 기능구현</li>  |
| 변영은    |  <img src="https://avatars.githubusercontent.com/u/155031676?v=4!" alt="변영은" width="100">    | <ul><li>학과별 랭킹 기능 </li><li>개인별 랭킹 기능 </li></ul>    |

<br/>
<br/>

# 6. Technology Stack (기술 스택)

## 5.3 Backend
|  |  |  |
|-----------------|-----------------|-----------------|
| SpringBoot    |  <img src="https://github.com/user-attachments/assets/1694e458-9bb0-4a0b-8fe6-8efc6e675fa1" alt="Firebase" width="100">    | 3.3.4  |
| SpringSecurity    |  <img src="https://github.com/user-attachments/assets/1694e458-9bb0-4a0b-8fe6-8efc6e675fa1" alt="Firebase" width="100">    |  |

<br/>

## 7 Cooperation
|  |  |
|-----------------|-----------------|
| Git    |  <img src="https://github.com/user-attachments/assets/483abc38-ed4d-487c-b43a-3963b33430e6" alt="git" width="100">    |
| Notion    |  <img src="https://github.com/user-attachments/assets/34141eb9-deca-416a-a83f-ff9543cc2f9a" alt="Notion" width="100">    |

<br/>

# 8. Project Structure (프로젝트 구조)
```plaintext
Dockerfile
build.gradle
├── docker-compose.yml
scripts
│   └── deploy.sh
src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── sa_project
    │   │               ├── SaProjectApplication.java
    │   │               ├── api
    │   │               │   ├── controller
    │   │               │   │   ├── home
    │   │               │   │   │   └── HomeController.java
    │   │               │   │   ├── mission
    │   │               │   │   │   ├── MissionController.java
    │   │               │   │   │   └── request
    │   │               │   │   │       ├── ClearRequest.java
    │   │               │   │   │       ├── MissionRequest.java
    │   │               │   │   │       ├── ProgressRequest.java
    │   │               │   │   │       ├── ResetRequest.java
    │   │               │   │   │       └── RewardRequest.java
    │   │               │   │   ├── ranking
    │   │               │   │   │   └── RankingController.java
    │   │               │   │   ├── response
    │   │               │   │   │   └── ErrorResponse.java
    │   │               │   │   └── user
    │   │               │   │       ├── UserController.java
    │   │               │   │       └── request
    │   │               │   │           └── UserRequest.java
    │   │               │   └── service
    │   │               │       ├── mission
    │   │               │       │   ├── MissionService.java
    │   │               │       │   └── response
    │   │               │       │       ├── ClearResponse.java
    │   │               │       │       ├── MissionProgress.java
    │   │               │       │       ├── ProgressResponse.java
    │   │               │       │       ├── ResetResponse.java
    │   │               │       │       ├── RewardResponse.java
    │   │               │       │       └── mission
    │   │               │       │           └── MissionProgressResponse.java
    │   │               │       ├── ranking
    │   │               │       │   ├── RankingService.java
    │   │               │       │   └── response
    │   │               │       │       ├── MajorProgressDTO.java
    │   │               │       │       ├── SetProgressDTO.java
    │   │               │       │       └── UserProgressDTO.java
    │   │               │       └── user
    │   │               │           ├── CustomUserDetailsService.java
    │   │               │           ├── UserService.java
    │   │               │           └── response
    │   │               │               ├── UserResponse.java
    │   │               │               └── UserResponseNoToken.java
    │   │               ├── config
    │   │               │   ├── CustomUserDetails.java
    │   │               │   ├── JwtAuthenticationFilter.java
    │   │               │   └── SecurityConfig.java
    │   │               ├── domain
    │   │               │   ├── mission
    │   │               │   │   ├── Mission.java
    │   │               │   │   ├── MissionRepository.java
    │   │               │   │   ├── MyMission.java
    │   │               │   │   └── MyMissionRepository.java
    │   │               │   ├── ranking
    │   │               │   │   ├── UserProgress.java
    │   │               │   │   └── UserProgressRepository.java
    │   │               │   └── user
    │   │               │       ├── Major.java
    │   │               │       ├── User.java
    │   │               │       └── UserRepository.java
    │   │               └── util
    │   │                   └── JwtUtil.java
    │   └── resources
    │       └── application.yml
```

<br/>
<br/>

# 9. Development Workflow (개발 워크플로우)
## 브랜치 전략 (Branch Strategy)
우리의 브랜치 전략은 Git Flow를 기반으로 하며, 다음과 같은 브랜치를 사용합니다.

- Main Branch
  - 배포 가능한 상태의 코드를 유지합니다.
  - 모든 배포는 이 브랜치에서 이루어집니다.
 
- Devlop Branch
  - 만든 기능들이 작동하는지 코드를 합병합니다.
  
- {feat} Branch
  - 모든 기능 개발은 feat 브랜치에서 이루어집니다.

<br/>
<br/>

# 10. Coding Convention

<br/>

## type 종류
```
feat : 새로운 기능 추가
fix : 버그 수정
docs : 문서 수정
style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
refactor : 코드 리펙토링
test : 테스트 코드, 리펙토링 테스트 코드 추가
chore : 빌드 업무 수정, 패키지 매니저 수정
```

<br/>

## 커밋 이모지
```
== 코드 관련
📝	코드 작성
🔥	코드 제거
🔨	코드 리팩토링
💄	UI / style 변경

== 문서&파일
📰	새 파일 생성
🔥	파일 제거
📚	문서 작성

== 버그
🐛	버그 리포트
🚑	버그를 고칠 때

== 기타
🐎	성능 향상
✨	새로운 기능 구현
💡	새로운 아이디어
🚀	배포
```

<br/>

## 커밋 예시
```
== ex1
✨Feat: "회원 가입 기능 구현"

SMS, 이메일 중복확인 API 개발

== ex2
📚chore: styled-components 라이브러리 설치

UI개발을 위한 라이브러리 styled-components 설치
```
<br/>

# 11. 수행 결과
<img width="100%" alt="서버 작동" src="<img width="835" alt="스크린샷 2024-12-05 14 57 55" src="https://github.com/user-attachments/assets/a04af810-8457-43a0-987f-430ecb4a6b06">">
