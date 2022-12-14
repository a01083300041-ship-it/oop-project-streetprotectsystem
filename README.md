# StreetProtectSystem (길거리 지킴이)

객체지향프로그래밍 팀 프로젝트 — 시민이 길거리 무단투기와 수해위험지역(댐)을 신고하고, 관리자가 신고 내역을 관리하는 데스크톱 애플리케이션입니다.

## 1. 프로젝트 배경 및 개요

**기간**: 2022.09 – 2022.12 (약 4개월)
**팀 구성**: 4명
**담당 역할**: 팀 전체가 기능을 분담하지 않고 공동 개발 (개별 담당 파트가 명확히 나뉘어 있지 않음)

프로그램 실행 시 표시되는 메인 타이틀은 "길거리 지킴이"이며, 내부 창 타이틀은 "길거리 무단투기 개선 시스템"입니다.

## 2. 주요 기능

- **지도/주소 기능** — 입력한 주소를 Naver Map(Geocoding) API로 좌표로 변환해 지도에 표시 (`NaverMap.java`, `AddressVO.java`)
- **신고 등록** — 쓰레기 무단투기(`InsertTrash.java`) 및 수해위험지역/댐(`InsertDam.java`) 신고 등록
- **관리자 기능** — 관리자 로그인 후 신고 내역을 조회·수정·삭제 (`Manager.java`, `Modify.java` — 약 370줄로 가장 큰 클래스)
- **화면 전환 UI** — 메인 화면, 다음 화면 전환, 지도 보기 버튼 등 (`StreetProtectSystem.java`, `Next.java`, `Project01_E.java`)

## 3. 클래스 구성

| 클래스 | 역할 |
|---|---|
| `StreetProtectSystem` | 메인 진입점, 메인 화면 UI |
| `Project01_E` | 건의사항/지도 화면 |
| `NaverMap` | Naver Geocoding API 연동, 주소→좌표 변환 |
| `AddressVO` | 주소 데이터 VO |
| `Trash` / `InsertTrash` | 쓰레기 무단투기 신고 조회/등록 |
| `Dam` / `InsertDam` | 수해위험지역(댐) 신고 조회/등록 |
| `SuTrash` / `SuDam` | 쓰레기/댐 신고 상세 조회 |
| `Manager` | 관리자 로그인/메뉴 |
| `Modify` | 관리자용 신고 내역 수정·삭제 (약 370줄) |
| `Next` | 화면 전환 처리 |

## 4. 기술 스택

| 영역 | 기술 |
|---|---|
| 언어 | Java |
| UI | Java Swing (JFrame, JPanel) |
| 데이터 저장 | JSON (json-simple) |
| 외부 API | Naver Cloud Platform (Geocoding API) |
| 빌드/IDE | Apache NetBeans IDE, Ant |

## 5. 실행 전 준비

Naver Cloud Platform API 키가 필요합니다. [Naver Cloud Platform 콘솔](https://www.ncloud.com/)에서 Maps > Geocoding API를 신청해 클라이언트 ID/Secret을 발급받은 뒤, `src/streetprotectsystem/NaverMap.java` 안의 `YOUR_NAVER_CLIENT_ID` / `YOUR_NAVER_CLIENT_SECRET`를 교체하세요.

## 6. 실행 방법

NetBeans에서 프로젝트를 열고 `StreetProtectSystem.java`를 실행하거나:

```bash
ant run
```
