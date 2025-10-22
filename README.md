# 🏢 태평로빌딩 사내 통합관리 시스템

### 📌 개요
이 프로젝트는 인사, 근태, 교육, 급여를 통합적으로 관리하기 위한 **Java 콘솔 기반 시스템**입니다.  
각 관리 기능은 독립된 모듈로 구성되어 있으며, 모든 데이터는 텍스트 파일(`.txt`)로 저장됩니다.  

---

### 📂 기능 구성

| 번호 | 메뉴명 | 설명 |
|------|--------|------|
| 1 | 인사 관리 | 직원 등록, 조회, 검색, 퇴사 등록 |
| 2 | 근태 관리 | 출근/퇴근 기록, 근태 조회 |
| 3 | 교육 관리 | 교육 등록, 수정, 조회 |
| 4 | 급여 관리 | 급여 등록, 조회, 수정, 퇴사 처리 |
| 5 | 종료 | 프로그램 종료 |

---

### ⚙️ 실행 방법

1. 프로젝트를 clone 또는 다운로드합니다.
 git clone https://github.com/quemasda90/taepyungro.git
2.Eclipse 또는 IntelliJ에서 main/MainApp.java 실행합니다.
3.콘솔 창에 나타나는 메뉴를 통해 기능 선택을 해주세요.

### 🗂️ 패키지 구조

src/
 ├── main/
 │   └── MainApp.java               # 전체 메인 메뉴
 │
 ├── employee/
 │   └── EmployeeManager.java       # 인사 관리 (등록, 검색, 퇴사)
 │
 ├── attendance/
 │   └── AttendanceManager.java     # 근태 관리 (출근/퇴근 기록)
 │
 ├── training/
 │   └── TrainingManager.java       # 교육 관리 (등록, 수정, 조회)
 │
 └── payroll/
     └── PayrollManager.java        # 급여 관리 (등록, 수정, 퇴사처리)

### 💾 데어터 저장 위치
src/
 └── data/
     └── employee.txt               # 직원 정보
     └── attendance.txt             # 출퇴근 기록
     └── training.txt               # 교육 정보
     └── payroll.txt                # 급여 정보
     
모든 데이터는 src/data/ 폴더에 .txt 형태로 저장됩니다.

🧱 주요 기능 설명
🧍 인사 관리

직원 등록, 목록 보기, 검색, 퇴사 등록

입력값이 잘못되면 “잘못 입력하셨습니다.” 메시지 출력

🕒 근태 관리

writeRecord(sc, "출근") 형태로 출근/퇴근 기록

LocalDateTime을 이용해 날짜 및 시간 자동 저장

🎓 교육 관리

사내 교육 등록, 수정, 조회 가능

💰 급여 관리

addPayroll : 급여 등록

listPayroll : 급여 조회

updatePayroll : 급여 수정

deletePayroll : 퇴사 처리

숫자 이외 입력 시 “잘못 입력하셨습니다. 숫자만 입력해주세요.” 안내 후 재
### 🧑‍💻 개발 환경
● Java 17 이상
● Eclipse / IntelliJ IDEA 사용 가능

### 📝 작성자
● 개발자: 강동윤
● 최근 수정일: 2025-10-22
