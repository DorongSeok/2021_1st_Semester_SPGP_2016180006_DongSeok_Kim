Pokeman
============
스마트폰 게임 프로그래밍 Term Project
2016180006 김동석
------------

## + 게임 컨셉
> * High Concept   
> 스팀게임인 팀버맨(Timberman)의 모작으로 기존의 게임에서 나무꾼이 나무를 패는 것이 아닌 피카츄가 갸라도스의 몸통을 공격하는 형태로 바뀌었다.  
>    
> * 핵심 메카닉   
> 플레이어는 피카츄가 갸라도스의 지느러미에 걸리지 않도록 좌우로 이동해가며 갸라도스의 몸통을 공격하며 화면 상단의 게이지가 전부 소모되거나 피카츄가 갸라도스의 지느러미에 부딪치면 게임이 끝나게 된다.   
>   
> <img src="./image/scene_1.png" width="297px" height="528px" title="pikaScene" alt="pikaScene"></img><br/>
- - -
## + 개발 범위
> * 맵   
> 호수, 땅, 하늘이 분리되어 각각 움직인다.   
>    
> * 캐릭터 컨트롤러   
> 터치를 사용하여 플레이어가 좌우로 이동하며 공격   
>    
> * 게임 주요기능
> 1. 플레이어와 장애물 간의 충돌 처리
> 2. 탭 횟수만큼 스코어 증가(1회당 1점)
> 3. 스코어에 따라 새로운 캐릭터 잠금 해제(100, 150, 200점마다 잠금 해제)
> 4. 캐릭터가 공격할 때 몸통이 분리되어 날라가는 경로 무작위로 설정   
>    
> * 게임 난이도   
> 시간이 지날수록 게이지가 빨리 줄어들고 장애물(지느러미)가 자주 나온다.   
>    
> * 사운드   
> BackGround, Attack, Death, Click 사운드(총 4개)   
>    
> * 캐릭터  
> 스코어에 따라 잠금 해제되는 캐릭터 3종   
>    
> * 애니메이션   
> Breath, Attack 캐릭터 애니메이션(총 2개)   
- - -
## + 예상 게임 실행 흐름
> * 전체 흐름   
> <img src="./image/순서도.jpg" width="370px" height="832px" title="순서도" alt="순서도"></img><br/>
>    
> * 로비 씬   
> 게임 플레이, 게임 종료 버튼 클릭(Click) 가능   
> <img src="./image/LobbyScene.JPG" width="297px" height="528px" title="LobbyScene" alt="LobbyScene"></img><br/>
>     
> * 게임 준비 씬   
> 플레이, 캐릭터 선택 버튼 클릭(Click) 가능   
> <img src="./image/GameScene.JPG" width="297px" height="528px" title="ReadyScene" alt="ReadyScene"></img><br/>
>    
> * 게임 플레이 씬   
> 화면의 좌우를 터치하여 플레이어 좌우로 이동하며 공격, 장애물에 충돌하거나 게이지가 모두 소모되면 게임종료  
> <img src="./image/GamePlayScene.JPG" width="297px" height="528px" title="GamePlayScene" alt="GamePlayScene"></img><br/> 
>    
> * 캐릭터 선택 씬   
> 스코어에 따라 잠금 해제되는 캐릭터 선택가능    
> <img src="./image/Shop.JPG" width="297px" height="528px" title="Shop" alt="Shop"></img><br/>
>    
> * 스코어 씬   
> 게임 스코어 출력, 다시하기 버튼 클릭 시 게임 재시작   
> <img src="./image/ScoreScene.JPG" width="297px" height="528px" title="ScoreScene" alt="ScoreScene"></img><br/>
- - -
## + 개발 일정
> * 1주   
> 필요 리소스 제작 및 수집(캐릭터, 배경, 폰트 등), Timberman 게임 플레이   
>    
> * 2주   
> 캐릭터 생성, 클릭 시 좌우 이동   
>    
> * 3주   
> 맵 중앙에 공격 대상 생성, 공격 시 기둥 사라짐   
>    
> * 4주   
> 기둥이 날라가는 모션 제작, 스코어 제작   
>    
> * 5주   
> 배경 및 시간에 따라 줄어드는 게이지 생성   
>    
> * 6주   
> 게임 난이도 설정, 캐릭터 잠금 해제 기능 추가   
>    
> * 7주   
> 로비 씬 추가, 캐릭터 선택 씬 추가   
>    
> * 8주   
> 사운드 추가   
>    
> * 9주   
> 최종 점검, 오류 수정, 마무리 작업
- - -
