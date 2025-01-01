# Outline

---

> ***"Take the best that exists and make it better. When it does not exist, design it."***
> 
> 
> **–** Sir Henry Royce
> 

***Obsidian*** 은 고가의 자동차 브랜드 지점 정보와 자동차를 보여주는 앱 입니다. 

- Bugatti Seoul, Ferrari Gangnam 같은 럭셔리 자동차 브랜드 지점을 모아놓은 앱입니다.
- 판매중인 자동차를 한눈에 확인할 수 있으며, 자동차 사진을 클릭 시 자동차의 세부 정보까지 확인 가능합니다.
- 기존에는 직접 자동차에 꽂아서 문을 여는 방식의 수동 자동차 키를 사용하였고, 사람들을 그것을 사용할때 딱히 아무런 불편함도 느끼지 못했습니다. 다만 스마트키가 나온 이유로 사람들은 직접 문에 꽂아서 사용해야 하는 전통적인 자동차 키에 큰 불편함을 느끼게 되었고, 오늘날 거의 모든 자동차 키가 스마트 키로 대체되게 되었습니다.
- 저희 Obsidian 은 위 사례처럼 럭셔리 자동차는 접근성이 좋지 않아야 한다는 관념을 부수고, 다양한 자동차를 한눈에 비교하여 기존 자동차 시장에 얼마나 불편한지 역 체감을 시켜줄 수 있도록 디자인된 앱입니다.

<aside>
♠️

Obsidian이란?

Obsidian이라는 이름은 고급스러움과 세련미를 상징하는 검은색 흑요석에서 영감을 받아, 명품 자동차의 품격과 희소성을 강조합니다.

</aside>

# Team

---

[김소정](https://www.notion.so/05f7a77d40ef42b0aa2125eb14255002?pvs=21)

- https://github.com/sojeongyy

[라윤수 (나윤수)](https://www.notion.so/3085964a6d2a446d9db3062b489ac1d7?pvs=21)

- https://github.com/nongshim-shinramyeon

# Tech Stack

---

**Front-end** : Kotlin

**IDE** : Android Studio

# Details

---

### 전체 화면 녹화

![obsidian_화면녹화.gif](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/967df05e-3a13-4859-8a61-0989faa66346/obsidian_%ED%99%94%EB%A9%B4%EB%85%B9%ED%99%94.gif)

### 스플래시

![스플래시.gif](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/47f3d2f7-7c6c-4ae6-bb79-1e4560710ae8/%EC%8A%A4%ED%94%8C%EB%9E%98%EC%8B%9C.gif)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/bede26b0-83f4-494b-814c-1925c74051e2/image.png)

- 앱을 처음 실행하면 앱의 로고가 포함된 스플래시 화면이 약 3초간 표시됩니다.

### 메인 화면

![메인화면.gif](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/c4f61646-5971-4146-98fe-131b178bc9ce/%EB%A9%94%EC%9D%B8%ED%99%94%EB%A9%B4.gif)

하단 바에 **Home, Cars, Phone** 세 개의 탭이 있습니다.

- **Home Tab**
    
    Home Tab에서는 검색 창이 있고, 구입하고 싶은 차의 브랜드를 입력하여 검색하는 기능을 제공합니다.
    
- **Cars Tab**
    
    Cars Tab에서는 현재 판매되고 있는 자동차 사진을 보여줍니다. 
    
    자동차 사진을 누르면 자동차 정보 창으로 연결되어, 자동차의 세부 정보를 확인하실 수 있습니다.
    
- **Phone Tab**
    
    Phone Tab에서는 자동차 브랜드 지점의 정보를 스크롤 하여 한눈에 확인하실 수 있습니다. 
    
    자동차 지점들의 전화번호를 포함하고 있으며, 클릭하면 브랜드의 조금 더 구체적인 정보를 확인하실 수 있습니다.
    

### Tab1 Home: 자동차 브랜드 명 검색

![Tab1.gif](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/4325781b-41b4-4d28-ac22-440480dd6adc/Tab1.gif)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/84a969e8-d8ac-4571-a601-226b973f2ada/image.png)

- 화면에서 보시는 것처럼 검색창에 브랜드를 입력하시면 (ex: Porsche, Lamborghini) , 해당 브랜드의 자동차를 확인하실 수 있습니다. 스크롤 해서 차들을 둘러보실 수 있습니다.
- 각 자동차 이미지를 선택하면 더욱 구체적인 정보를 확인할 수 있습니다.
- 자동차 데이터는 res/raw/cars.json 파일에서 관리하였습니다.
    - 해당 파일에 있는 자동차 중 brand를 필터링하여 검색결과를 띄웁니다.
    

### Tab2 Cars: 자동차 갤러리

![Tab2.gif](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/e9c207e9-f060-4f39-b929-2720ebeca4c7/Tab2.gif)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/6765b28b-94ed-4456-9bef-17f359942c36/image.png)

- 스크롤 해서 앱에 등록된 자동차 갤러리를 확인할 수 있습니다.
- 각 자동차 이미지를 클릭하면 자동차의 구체적인 정보 (출시 연도, 옵션, 출력 등) 를 확인하실 수 있습니다.

### Tab3 Phone: 고가 브랜드 지점 전화번호

![Tab3.gif](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/7707d6a2-5109-4691-ab65-0f29080b03db/Tab3.gif)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/227f0a38-bf7d-462c-9a42-04d12fe5402b/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/acf1f6f5-7504-4a8d-aa3a-55df3db6b8f2/image.png)

- 브랜드 지점을 스크롤 해서 확인할 수 있는 탭입니다.
- 각 브랜드 지점을 클릭 시 브랜드 지점의 구체적인 정보 (사이트, 주소, 지점 이메일, 대표자 성명 등) 를 확인할 수 있습니다.
- 또한 지점 창에 들어가면, 하단에서 해당 브랜드의 차들을 확인할 수 있습니다.
- 전화번호 버튼 클릭 시에는 바로 전화 앱으로 이동됩니다.
    - `Intent.ACTION_DIAL` 을 활용하였습니다.
- 자동차 브랜드 데이터는 res/raw/carbrands.json 파일에서 관리하였습니다.

## 느낀 점

---

- 라윤수
    - 개발 경험 없음/ 안드로이드 스튜디오/ 쉽지 않았음
    - 건강 이슈 겹치게 되어
    - 많이 배울 수 있던 것 같아 좋았음

- 김소정
    - 코틀린은 한번도 써보지 않았기에 처음 시작이 매우 어려웠습니다.
    - 하지만 계속 코드를 읽고 작성하면서 점점 익숙해지며 많은 것을 배울 수 있었습니다.
    - 또한 ui 디자인이 정말 어려웠으며, 피그마로 만든 디자인을 그대로 만드는 것은 정말 어렵다는 것을 깨달았습니다.
    

## Obsidian APK

---

https://drive.google.com/file/d/1MoFuL5qmiSVJh0U52YhqiWa2QNzRskNU/view?usp=sharing
