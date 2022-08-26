# SimpleToDoList
MVVM 패턴 학습과 Room 라이브러리 활용

## 주요 라이브러리
1. Room : 앱 내부 데이터베이스를 사용하기 위한 라이브러리
    - SQLite도 있는데 왜 Room을 사용했습니까? : 초기 설정은 번거로우나, 설정이 끝나면 사용시 매우 편리함
1. Coroutine : 데이터베이스 작업시 비동기 처리를 위해
    -  메모리 누수 감소, 스레드에 비해 가벼움
1. ViewPager2 : Fragment 화면 전환

<br>

## 주요 기능
### 1. 할 일 목록 생성<br>
![newToDo](https://user-images.githubusercontent.com/97011241/184499921-480a75d4-981b-40c4-8833-123714d125d9.gif)
- 우측 하단의 Floating Button을 눌러서 새로운 할 일 목록 생성 가능

<br>

### 2. 할 일 목록 내용 수정<br>
![editTodo](https://user-images.githubusercontent.com/97011241/184499952-2645a000-aab2-4f01-bad3-2dc4a5d5ba4f.gif)
- 목록 내부의 연필 모양의 수정 버튼을 통해 할 일 목록의 내용을 수정 가능

<br>

### 3. 달력을 통한 할 일 목록 확인 및 수행 여부 체크<br>
![calendar,Check](https://user-images.githubusercontent.com/97011241/184499996-1c2f4722-bc03-4515-99fa-47c66aa86513.gif)
- 화면을 슬라이드하거나, 상단의 캘린더 버튼을 눌러 화면 전환 가능
- 특정 날짜를 선택하면 그 날짜에 할 일 목록이 있으면 해당 목록을 보여줌
- 체크박스를 눌러서 할 일을 수행 했는지 사용자 본인이 체크할 수 있음

<br>

### 4. 특정 날짜에 할 일 목록 추가<br>
![specificDate](https://user-images.githubusercontent.com/97011241/184500095-d48f39f5-2ff8-4ccf-be9d-b3cf570b2d7b.gif)
- 달력에서 특정 날짜를 선택한 후 해당 날짜에 할 일 목록을 기입할 수 있음

<br>

### 5. 할 일 목록 삭제<br>
![deleteTodo](https://user-images.githubusercontent.com/97011241/184500158-94b7be7b-5a67-46ea-8982-8d712440fff9.gif)
- 목록의 쓰레기통 버튼을눌러서 할 일 목록을 삭제할 수 있음

### 6. 완료한 할 일 목록만 표시<br>
![check](https://user-images.githubusercontent.com/97011241/186813059-bd808e65-7ee4-45cf-8d3e-a1e54d9f8bb3.gif)
- 사용자가 완료했다고 표시한(체크박스) 목록들만을 출력함


<br>

## 패턴
- MVVM이 무엇인가요?
  - Model, View, ViewModel을 분리해 모델간의 의존성을 줄이는 패턴
  - Moel
    - 이벤트를 발생시켜 데이터 요청
    - 라이브 데이터를 감지해 저장된된 값을 View에 출력
  - View Model
    - 요청에 맞게 데이터를 불러오는 Model의 메소드를 호출
    - Model로 받은 값을 라이브 데이터에 저장
  - Model
    - ViewModel에서 요청하는 값을 반환
- MVVM 왜사용했나요?
  - 이때까지 내가 만들어온 앱은 전부 Activity에서 작성해왔다.
  - 이렇게 작성하다 보니 추후에 기능을 추가하거나 삭제할 때 어려움이 있었다.
  - 이걸 해결하기위한 방법을 찾던 중 발견한 것이 디자인 패턴이였다.
  - MVP, MVC등의 다른 패턴도 존재하지만 MVVM 패턴에서 확인할 수 있는 분리되고, 체계적인 느낌이 좋아서 채택함
    - MVC : View와 Controller가 Activity에서 처리됨
    - MVP : Presenter가 View와 의존성이 강함
  - 화면이 회전되어 View가 재생성되는 등의 상황에서 ViewModel을 통해 데이터를 유지할 수 있음
- 어디에 적용되었나요?
  - 내부 데이터베이스를 Model(Repository), 해당 데이터베이스에 접근할 수 있는 메소드를 ViewModel에 각각 작성
  - Fragment에서 ViewModel의 메소드를 호출하고, 이를 Observe를 통해 변화를 관측함
  - Observe중 값이 변화하면 이를 recycler adapter에 전달하여 recycler view를 갱신함
