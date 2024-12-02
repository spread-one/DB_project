# DB_project

project-root/
├── backend/                         # 백엔드 코드
│   ├── src/main/kotlin/com/yourproject
│   │   ├── config/                  # 설정 파일
│   │   ├── controller/              # 컨트롤러 계층
│   │   ├── dto/                     # 요청/응답 객체
│   │   ├── entity/                  # 데이터베이스 엔터티 클래스
│   │   ├── repository/              # 데이터 접근 계층
│   │   ├── service/                 # 비즈니스 로직 계층
│   │   ├── exception/               # 예외 처리
│   │   └── YourProjectApplication.kt
│   ├── build.gradle.kts             # Gradle 빌드 스크립트
│   ├── application.yml              # Spring Boot 설정 파일
│   └── Dockerfile                   # 백엔드용 Dockerfile
│
├── frontend/                        # 프론트엔드 코드
│   ├── public/                      # 정적 파일 (HTML, favicon 등)
│   │   ├── index.html
│   │   └── favicon.ico
│   ├── src/                         # 프론트엔드 애플리케이션 소스 코드
│   │   ├── components/              # 재사용 가능한 UI 컴포넌트
│   │   ├── pages/                   # 주요 페이지 컴포넌트
│   │   ├── services/                # API 요청 및 비즈니스 로직
│   │   ├── styles/                  # CSS/SCSS 스타일 파일
│   │   ├── App.tsx                  # 메인 React 컴포넌트 (React 기준)
│   │   └── index.tsx                # 진입점
│   ├── package.json                 # 프론트엔드 의존성 관리 파일
│   ├── tsconfig.json                # TypeScript 설정 파일
│   └── Dockerfile                   # 프론트엔드용 Dockerfile
│
├── database/                        # 데이터베이스 관련 파일
│   ├── migrations/                  # DB 마이그레이션 파일
│   │   ├── 001_create_spaces.sql
│   │   └── 002_create_reservations.sql
│   ├── seeders/                     # 초기 데이터 삽입 스크립트
│   │   └── seed_data.sql
│   └── schema.sql                   # 데이터베이스 스키마 정의 파일
│
├── docs/                            # 프로젝트 관련 문서
│   ├── API_SPEC.md                  # API 명세
│   ├── DB_SCHEMA.md                 # DB 스키마 설명
│   └── README.md                    # 프로젝트 README 파일
│
├── docker-compose.yml               # Docker Compose 파일
├── .env                             # 환경변수 파일
└── README.md                        # 프로젝트 개요 문서
