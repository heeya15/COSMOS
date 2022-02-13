<template>

  <div id="main_page">
      <!-- 모달 시작-->
      <b-modal ref="my-modal" :id="infoModal.id" hide-footer centered hide-header>
        <center>
          <h4 slot="header" class="card-title">방 입장을 위한 초기 세팅</h4>
        </center>
        <br />
        <b-row>
        <b-col cols="5">초기 장치 설정</b-col>
        <b-col>
          <input type="checkbox" id="mic"  v-model="settings.mic">
          <label for="mic" class="ml-1 mr-4">마이크ON</label>
          <input type="checkbox" id="cam"  v-model="settings.cam">
          <label for="cam" class="ml-1 mr-4">카메라ON</label>
        </b-col>
        </b-row>
        <div class="text-center">
          <button @click="hideModal" class="cancelBtn ml-3 float-right" >취소</button>
          <button @click="goStudyRoom(infoModal.publicstudyroomId, infoModal.studyName , infoModal.numberOfMember )" type="submit" class="enterBtn ml-3 float-right" >입장</button>
        </div>
      </b-modal>
      <!-- 모달 끝 -->
      <!-- MainPage Banner Start -->
      <div class="banner">
        <div class="bannerBox">
          <div class="leftBox">
            <img class="cosmos" src="https://i.ibb.co/nm8jZHr/main-img7.png" alt="main-img7" border="0"><br/>

          </div>
          <div class="rightBox" align="center">
            <div class="wrapper" >
              <div class="subTitle">
                <span>집에서 편하게 코딩하세요!</span>
              </div>
              <div class="title">          
                <span class="topTitle">IT 비대면 스터디</span>
                <span class="bottomTitle">코스모스</span>
              </div>
              <button class="startBtn" @click="start">START</button>
            </div>
          </div>        
        </div>
      </div>
      <!-- MainPage Banner End -->

      <div class="my-5" align="center">  
        <hr class="line">
      </div>

      <!-- 랭킹 Start -->
      <div align="center" style="margin-bottom: 80px;">
        <div id="rank_section">
          <h1 class="text-center mb-5">랭킹</h1>
          <!-- <h1 class="text-center mb-5">{{ counter }}</h1> -->
          
          <div class="text-right">
            <!-- <b-dropdown id="dropdown-left" variant="warning" text="랭킹기준" class="m-2">
              <b-dropdown-item href="#">일 (Day)</b-dropdown-item>
              <b-dropdown-item href="#">주 (Week)</b-dropdown-item>
              <b-dropdown-item href="#">월 (Month)</b-dropdown-item>
            </b-dropdown> -->
            <p class="mt-3" style="font-family: BMJual; color: #828282;">{{ date }}({{ day }}) 06:00 AM UPDATED</p>
          </div>
          <div v-show="shortRank">
            <div class="rolling_box d-flex">
              <ul id ="rolling_box">
                <li class="card_sliding" id ="first"></li>
                <li class="" id ="second"></li>
                <li class="" id ="third"></li>
              </ul>
              <img id="downArrowBtn" src="https://i.ibb.co/p1jKdLj/down-filled-triangular-arrow.png" alt="down-filled-triangular-arrow" border="0" @click="rankClick"/>
            </div>
          </div>
          <div class="rankTable" v-show="longRank">
            <table class="table border table-hover scrollTable">
              <col style="width:100%">
              <thead>
                <tr col-span="2">
                  <th><p></p></th>
                  <th class="rankHeader">
                    <img class="flex-fill" id="exitBtn" src="https://i.ibb.co/GWXqhqv/close.png" alt="close" border="0" @click="rankClick">
                  </th>
                </tr>
              </thead>

              <tbody v-for="(data, idx) in dailyRank" :key="idx">
                <tr>
                  <div v-if="idx==0">
                    <td><img src="https://i.ibb.co/LS0sbGF/medal-gold.png" alt="medal-gold" border="0" style="width: 40px; height: 40px; margin-left: 5px; margin-bottom: 5px;"></td>
                    <td><p class="ml-5 mt-3"> {{ data.userhistoryDayId.user_id }} </p></td>
                  </div>
                  <div v-if="idx==1">
                    <td><img src="https://i.ibb.co/wYypVVB/medal-silver.png" alt="medal-silver" border="0" style="width: 40px; height: 40px; margin-left: 5px; margin-bottom: 5px;"></td>
                    <td><p class="ml-5 mt-3"> {{ data.userhistoryDayId.user_id }} </p></td>
                  </div>
                  <div v-if="idx==2">
                    <td><img src="https://i.ibb.co/rcVSCsd/medal-bronze.png" alt="medal-bronze" border="0" style="width: 40px; height: 40px; margin-left: 5px; margin-bottom: 5px;"></td>
                    <td><p class="ml-5 mt-3"> {{ data.userhistoryDayId.user_id }} </p></td>
                  </div>
                  <div v-if="idx > 2">
                    <td><p class="mx-4 mt-3" style="font-size: 20px;"> {{ idx+1 }} </p></td>
                    <td><p class=" mt-3" style="margin-left: 35px;"> {{ data.userhistoryDayId.user_id }} </p></td>
                  </div>
                </tr>
              </tbody>
          </table>
          </div>
        </div>
      </div>
      <!-- 랭킹 End -->

      <div class="my-5" align="center">  
        <hr class="line">
      </div>

      <!-- 게시판 목록 Start -->
      <center>
        <div id="board_section">
        <div class="d-flex justify-content-between studyHeader">
          <div></div>
          <h1 class="mb-3">모집 중인 스터디</h1>
          <img class="mr-2" id="plusBtn" src="https://i.ibb.co/fFmxj3J/plus.png" alt="plus" border="0" @click="goToBoardList">
        </div>
          <table class="table table-boardered table-hover">
            <col style="width:70%">
            <col style="width:30%">

            <thead align="center">
              <th>🌼 게시글 제목 🌼</th>
              <th>🌼 분류 🌼</th>
            </thead>
            <tbody v-for="(board, idx) in boardList" :key="idx" @click="goBoardDetail(board.boardNo)">
              <!-- <td><p class="mx-3">🌷 {{ board.contentTitle }} 🌷</p></td> -->
              <!-- <td align="center"><p>🌷 {{ board.studytypeName }} 🌷</p></td> -->
                <td><p class="mx-3"> {{ board.contentTitle }} </p></td>
                <td align="center"><p> {{ board.studytypeName }} </p></td>
              <p></p>
            </tbody>
          </table>
        </div>
      </center>
      <!-- 게시판 목록 End -->

      <div class="my-5" align="center">  
        <hr class="line">
      </div>

      <!-- 오픈(공개) 스터디 목록 Start -->
      <div>
        <h1 class="text-center">오픈 스터디</h1>
        <div class="my-5 p-5" align="center">  
          <div v-if="publicStudyList.length >= 1" >
        <!-- <VueSlickCarousel ref="slick" :options="slickOption"> -->
          <VueSlickCarousel ref="slick" 
            :arrows="true"
            :dots="true"
            :infinite="true" 
            :speed="500"
            :slidesToShow="3"
            :slidesToScroll="1"
            :swipeToSlide="true"
            :adaptiveHeight="true"
            :autoplay="true"
            :autoplaySpeed="2000"
          >
              <div v-for="(publicstudy, idx) in publicStudyList" :key="idx" class="px-5 mb-lg-2" @click="info(publicstudy,$event.target)">
                  <div class="hover hover-1 text-white rounded">
                    <img class="studyImg" :src="publicstudy.image" alt="Study Image is missing... :(">
                    <div class="hover-1-number">{{ currentParticipant[idx] }} &#47; {{ publicstudy.numberOfMember }}</div>
                    <div class="hover-1-content px-5 py-4">
                      <h3 class="hover-1-title text-uppercase mb-0"><span :model="publicstudy.studyName">{{ publicstudy.studyName }}</span></h3>
                      <p class="hover-1-description mb-1">{{ publicstudy.studyType.studytypeName }}</p>
                    </div>
                  </div>
                </div>
                <template #prevArrow>
                  <button>
                  </button>
                </template>
                <template #nextArrow>
                  <button>                  
                  </button>
                </template>
              </VueSlickCarousel>
            </div>
          <div v-else>아직 가입한 스터디가 없습니다.</div>
        </div>
      </div>
      <!-- 오픈(공개) 스터디 목록 End -->

      <div class="my-5" align="center">  
        <hr class="line">
      </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'
import Vue from 'vue'
import VueCrontab from 'vue-crontab'
Vue.use(VueCrontab)

import JwtDecode from 'jwt-decode'
import { mapState } from 'vuex'

export default {
  name: 'MainPage',
  components: { VueSlickCarousel },

  data() {
    return {
      boardItems: null,
      boardList: [],

      // 랭킹 변수
      date: '',
      day: '',

      dataCnt: 0,
      dailyRank: [],
      weeklyRank: [],
      monthlyRank: [],
      shortRank: true,
      longRank: false,
      move: 2,
      listCnt: 1,
      rankType: 0,      // 랭킹 시간 기준을 나타내는 수치
      rankingType: '',  // 랭킹 시간(일, 주, 월) 기준을 나타내는 메시지

      publicStudyList: [],
      currentParticipant: [],
      infoModal: {
        id: "info-modal",
        publicstudyroomId:"",
        studyName:"",
        numberOfMember:""
      },

      slickOption: {
        arrows: true,
        dots: true,
        infinite: true, 
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 1,
        swipeToSlide: true,
      },
      // 마이크, 캠 설정.
      settings: {
        mic: false,
        cam: false,
        speaker: null,
      },
      // 강퇴여부
      isBanned: null,
      // 인원수 사용 여부
      count : 0
    }
  },

  methods: {
    getHeader(){
      const token = localStorage.getItem('jwt')
      const header = {
        Authorization: `Bearer ${token}`,
      }
      return header
    },

    start() {
      if (this.$store.state.isLogin){
        this.$router.push({name: 'StudyRoomCreateForm'})
      } else {
        this.$router.push({name: 'SignUp'})
      }
    },

    // 상세보기로
    goBoardDetail(boardItemsIdx) {
      console.log(boardItemsIdx)
      this.$store.dispatch('getBoardNo', boardItemsIdx)
      this.$router.push({ name: 'BoardDetail', query: { pageId: this.currentPage }})
    },

    // 게시판 불러오기
    getBoardItems() {
      http({
        method: 'get',
        url: '/board/searchAll',
      })
      .then(res => {
        this.boardItems = res.data
        console.log(res.data);
        this.boardItems = res.data.sort(function(a, b) {
          return b.boardNo - a.boardNo;
        })

        console.log(">>>>>> 전달 받은 리스트 : ", this.boardItems);

        // var len = this.boardItems.length > 5 ? 5 : this.boardItems.length;
        for(var i=0; i<this.boardItems.length; i++) {
          if (this.boardItems[i].header === false) {
            this.boardList.push(this.boardItems[i]);
            if (this.boardList.length === 5) {
              break
            }
          }
        }
      })
      .catch(err => {
        console.log(err)
      })
    }, 

    // 공개(오픈)스터디 전체 조회
    async getPublicStudy() {
      await http({
        method: 'GET',
        url: '/publicroom/search/searchAll/publicRoom',
      })
      .then(res => {
        console.log(">>>>>>>>>>>>>", res.data);
        this.publicStudyList = res.data
        for(var i=0; i<this.publicStudyList.length; i++) {
        // 오픈 스터디 객체 배열을 탐색하면서 스터디 현재 인원 파악
          this.getPublicStudyMember(this.publicStudyList[i].publicstudyroomId)
        }
      })
      .catch(err => {
        console.log(err)
      })
    },
    
    async getPublicStudyMember(publicstudyroomid) {
      await http({
        method: 'GET',
        url: '/publicroom/search/publicMember',
        params: { publicstudyroom_id: publicstudyroomid }
      })
      .then(res => {
        this.currentParticipant.push(res.data.length == null ? 0 : res.data.length)
      })
      .catch(err => {
        console.log(err)
      })
    },
    async getPublicStudyMemberCount(publicstudyroomid) {
      await http({
        method: 'GET',
        url: '/publicroom/search/publicMember',
        params: { publicstudyroom_id: publicstudyroomid }
      })
      .then(res => {
        this.count  = res.data.length;
      })
      .catch(err => {
        console.log(err)
      })
    },
    // 이전 강퇴 여부 체크
    checkBanned(publicstudyroom_id){
      http({
        method: 'GET',
        url: '/publicroom/bannedCheck',
        params: {publicstudyroom_id: publicstudyroom_id},
        headers: this.getHeader()
      })
      .then(res => {
        console.log('checkbanned 되는지 확인!!!', res)
        this.isBanned = res.data
      })
      .catch(err => {
          console.log(err)
        })     
      },
    
    // 모달 값 셋팅
    async info(publicstudy,button) {
      this.checkBanned(publicstudy.publicstudyroomId)
      this.infoModal.publicstudyroomId = publicstudy.publicstudyroomId;
      this.infoModal.studyName = publicstudy.studyName;
      this.infoModal.numberOfMember = publicstudy.numberOfMember; 
      await this.getPublicStudyMemberCount(this.infoModal.publicstudyroomId); // 해당 스터디룸 멤버 참가자 수 들고옴.
      console.log("모달창 들어옴?")
      console.log(this.count);
      console.log(this.infoModal.numberOfMember);
      if(this.count == this.infoModal.numberOfMember){
          alert("현재 해당 공개 스터디룸에 최대 인원으로 가득 차 있습니다.");
        return;
      }
      this.$root.$emit("bv::show::modal", this.infoModal.id, button);
    },
    hideModal() {
      this.$refs["my-modal"].hide();
    },
    // 공개 방 가기(가면 공개방 멤버로 추가)
    async goStudyRoom(publicstudyroomId, studyName ) {
    
      console.log("공개방 가기 버튼 클릭.")
      console.log(publicstudyroomId, studyName);
    
      var token = localStorage.getItem('jwt')
      var decoded = JwtDecode(token);
      var myId = decoded.sub;

      // 마이크 캠 셋팅
      this.$store.state.audio = this.settings.mic;
      this.$store.state.video = this.settings.cam;
      console.log( this.$store.state.audio)
      console.log( this.$store.state.video)

      this.$store.state.roomUrl = publicstudyroomId;
      this.$store.state.roomName = studyName;
      this.$store.state.participant = myId

      // 강퇴된적 있는 유저면 입장 불가
      if (this.isBanned === true){
        alert('입장이 불가능한 스터디입니다.');
        return;
      } else {
        // 멤버로 추가
        await http({
          method:'POST',
          url:'/publicroom/register/publicMember',
          data: {leader : false, publicstudyroomId: publicstudyroomId},
          headers: this.getHeader()
        })
        .then(res => {
          console.log('>>>>>>>>>>>>>>>>>>>>>>메인에서 공개스터디입장axios',res.data)
          this.$router.push({name: 'PublicStudyRoom'})
        })
        .catch(err => {
          console.log(err)
        })
        console.log(">>>>>>>>>>> ",publicstudyroomId)
      }    
    },

    goToBoardList() {
      this.$router.push({name: 'MainBoard'})
    },

    // 일별 랭킹
    getDailyRank() {
      http({
        method: 'GET', 
        url: '/history/searchAll/day',
      })
      .then(res => {
        var today = new Date()
        var year = today.getFullYear()
        var month = today.getMonth()
        var date = today.getDate()

        month = month.length == 1 ? "0" + month : month
        date = date.length == 1 ? "0" + date : date

        this.date = year + "-" + month + "-" + date
        this.day = today.toString().substring(0,3)
        this.dailyRank = []   // 이전 데이터 비우기
        this.dailyRank = res.data        
      })
      .error(err => {
        console.log(err)
      })
    },

    // 주별 랭킹
    getWeeklyRank() {
      http({
        method: 'GET', 
        url: '/history/searchAll/week',
      })
      .then(res => {
        var today = new Date()
        var year = today.getFullYear()
        var month = today.getMonth()
        var date = today.getDate()

        month = month.length == 1 ? "0" + month : month
        date = date.length == 1 ? "0" + date : date

        this.date = year + "-" + month + "-" + date
        this.day = today.toString().substring(0,3)
        this.weeklyRank = []   // 이전 데이터 비우기
        this.weeklyRank = res.data        
      })
      .error(err => {
        console.log(err)
      })
    },

    // 월별 랭킹
    getmonthlyRank() {
      http({
        method: 'GET', 
        url: '/history/searchAll/month',
      })
      .then(res => {
        var today = new Date()
        var year = today.getFullYear()
        var month = today.getMonth()
        var date = today.getDate()

        month = month.length == 1 ? "0" + month : month
        date = date.length == 1 ? "0" + date : date

        this.date = year + "-" + month + "-" + date
        this.day = today.toString().substring(0,3)
        this.monthlyRank = []   // 이전 데이터 비우기
        this.monthlyRank = res.data        
      })
      .error(err => {
        console.log(err)
      })
    },

    rankClick() {
      this.shortRank = !this.shortRank;
      this.longRank = !this.longRank;
      console.log("shortRank : ", this.shortRank, " longRank: ", this.longRank)
    },

  },
  computed:{
    ...mapState([
      'roomName', 'roomUrl', 'participant', 'audio', 'video'
    ])
  },
  created() {
    this.getBoardItems()
    this.getPublicStudy()
  },

  mounted() {
    // 오픈 스터디 접속 멤버 수 5초마다 새로 불러오기
    // setInterval(this.getPublicStudy, 5000);
    //   console.log("5 second later")
  
    // 매일 오전 6시마다 일별 랭킹 값을 새로 가져옴
    this.$crontab.addJob({
      name: 'dailyrank',
      interval: {
        seconds: '0', 
        minutes:'33', 
        hours: '18',
      },
      job: this.getDailyRank
    })

    // 매주 월요일 오전 6시마다 주별 랭킹 값을 새로 가져옴
    this.$crontab.add({
      name: 'weeklyrank',
      interval: {
        week: '1',
        seconds: '0',
        minutes: '0',
        hours: '6',
      },
      job: this.getWeeklyRank
    })

    // 매월 1일 6시마다 월별랭킹 값을 새로 가져옴
    this.$crontab.add({
      name: 'monthlyrank',
      interval: {
        day: '1',
        seconds: '0',
        minutes: '0',
        hours: '6',
      },
      job: this.getmonthlyRank
    })
  },

  updated() {
    // setInterval(this.getPublicStudy, 5000);
    // console.log("5 second later")
    
    let first = document.getElementById('first'),
        second = document.getElementById('second'),
        third = document.getElementById('third')

    if(this.dataCnt==0 || this.dataCnt == this.dailyRank.length) {
      first.innerHTML = '<img src="https://i.ibb.co/LS0sbGF/medal-gold.png" alt="medal-gold" border="0" style="width: 40px; height: 40px; margin-left: 5px; margin-bottom: 5px;">'
                                      + '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; &nbsp;' + this.dailyRank[0].userhistoryDayId.user_id + '</span>'
      this.dataCnt++
    } else first.innerHTML = '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; ' + this.dataCnt + " " + this.dailyRank[0].userhistoryDayId.user_id + '</span>'
    
    setInterval(() => {
      if(this.move == 2){
          first.classList.remove('card_sliding')
          first.classList.add('card_sliding_after')

          second.classList.remove('card_sliding_after')
          second.classList.add('card_sliding')

          third.classList.remove('card_sliding_after')
          third.classList.remove('card_sliding')

          this.move = 0
      } else if (this.move == 1){
          first.classList.remove('card_sliding_after')
          first.classList.add('card_sliding')

          second.classList.remove('card_sliding_after')
          second.classList.remove('card_sliding')

          third.classList.remove('card_sliding')
          third.classList.add('card_sliding_after')

          this.move = 2
      } else if (this.move == 0) {
          first.classList.remove('card_sliding_after')
          first.classList.remove('card_sliding')

          second.classList.remove('card_sliding')
          second.classList.add('card_sliding_after')

          third.classList.remove('card_sliding_after')
          third.classList.add('card_sliding')

          this.move = 1
      }
      console.log(">>>>>>>>>>>>>>> dataCnt : ", this.dataCnt)

      if(this.dataCnt < (this.dailyRank.length - 1)) {
        if(this.dataCnt==0 || this.dataCnt == this.dailyRank.length) {
          document.getElementById('rolling_box').children[this.listCnt].innerHTML = '<img src="https://i.ibb.co/LS0sbGF/medal-gold.png" alt="medal-gold" border="0" style="width: 40px; height: 40px; margin-left: 5px; margin-bottom: 5px;">'
                                          + '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; &nbsp;' + this.dailyRank[this.dataCnt].userhistoryDayId.user_id + '</span>'
        } else if(this.dataCnt==1) {
          document.getElementById('rolling_box').children[this.listCnt].innerHTML = '<img src="https://i.ibb.co/wYypVVB/medal-silver.png" alt="medal-silver" border="0" style="width: 40px; height: 40px; margin-left: 5px; margin-bottom: 5px;"/>' 
                                                                                + '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; &nbsp;' + this.dailyRank[this.dataCnt].userhistoryDayId.user_id + '</span>'
        } else if(this.dataCnt==2) {
          document.getElementById('rolling_box').children[this.listCnt].innerHTML = '<img src="https://i.ibb.co/rcVSCsd/medal-bronze.png" alt="medal-bronze" border="0" style="width: 40px; height: 40px; margin-left: 5px; margin-bottom: 5px;"/>' 
                                                                                + '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; &nbsp;' + this.dailyRank[this.dataCnt].userhistoryDayId.user_id + '</span>'
        } else {
          document.getElementById('rolling_box').children[this.listCnt].innerHTML = '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; ' + (this.dataCnt+1) + "&nbsp; &nbsp; &nbsp;" + this.dailyRank[this.dataCnt].userhistoryDayId.user_id +'</span>'
        }
        this.dataCnt++
        console.log("증가!!")
      } else if(this.dataCnt == this.dailyRank.length - 1) {
        if(this.dataCnt==2) {
          document.getElementById('rolling_box').children[this.listCnt].innerHTML = '<img src="https://i.ibb.co/rcVSCsd/medal-bronze.png" alt="medal-bronze" border="0" style="width: 40px; height: 40px; margin-left: 5px; margin-bottom: 5px;"/>' 
                                                                                + '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; &nbsp;' + this.dailyRank[this.dataCnt].userhistoryDayId.user_id + '</span>'
        } else {
          document.getElementById('rolling_box').children[this.listCnt].innerHTML = '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; ' + (this.dataCnt+1) + "&nbsp; &nbsp; &nbsp;" + this.dailyRank[this.dataCnt].userhistoryDayId.user_id  +'</span>'
        }
        this.dataCnt = 0
        console.log("리셋!!")
      }

      // if(this.dataCnt < (this.dailyRank.length - 1)) {
      //   document.getElementById('rolling_box').children[this.listCnt].innerHTML = '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; ' + (this.dataCnt+1) + "&nbsp; &nbsp; &nbsp;" + this.dailyRank[this.dataCnt].userhistoryDayId.user_id  +'</span>'
      //   this.dataCnt++;
      // } else if(this.dataCnt == this.dailyRank.length - 1) {
      //   document.getElementById('rolling_box').children[this.listCnt].innerHTML = '<span style="font-size: 20pt; line-height: 60px;"> &nbsp; ' + (this.dataCnt+1) + "&nbsp; &nbsp; &nbsp;" + this.dailyRank[this.dataCnt].userhistoryDayId.user_id  +'</span>'
      //   this.dataCnt = 0
      // }

      if(this.listCnt < 2) {
        this.listCnt++
        } else if (this.listCnt == 2) {
            this.listCnt = 0
        }
      }, 5000);
  },

}
</script>

<style scoped>
#main_page {
  height: 90%;
}

#main_section {
  height: 30%;
}

#rank_section {
  height: 30%;
  width: 70%;
}

#board_section{
  height: 30%;
  width: 50%;
}

th, td {   
  /* text-align: center; */
  vertical-align : middle !important;
}

#study_secion {
  height: 30%;
}

tbody:hover {
  cursor: pointer;
  background-color: #c8c1e4;
}

thead {
  background-color: #afa2dd;
}


/* .banner {
  height: 25%;
} */

/* .carousel-indicators li {
  background-color: transparent !important;
} */

/* .circle {
  z-index: -1;
  position: absolute;
  width: 400px;
  height: 400px; 
  left: -10%;
  top: 40%;
  border-radius: 70%;
  background-color: #E1BFE6;
} */

.banner {
  width: 70%;
  height: 60%;
  background-color: #e0dfdf;
  margin: auto;
  border-radius: 30px;
  position: relative;
}

.banner > .bannerBox {
  width: 100%;
  height: 100%;
  background-color: #fbfbfb;
  border-radius: 30px;
  top: -5%;
  left: 5px;
  box-shadow: 4px 4px 4px 2px rgba(0, 0, 0, 0.25);
}

.leftBox {
  position: absolute;
  width: 50%;
  height: 100%;
  top: -5%;
  left: 0%;
} 

.wrapper {
  position: absolute;
  top: 55%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.startBtn {
  width: 100px;
  height: 30px;
  border-radius: 20px;
  border: 0;
  background-color: #363636;
  color: rgba(255, 255, 255, 0.9);
  font-size: 15pt;
  font-weight: bold;
  cursor: pointer;
}

.rightBox {
  position: absolute;
  width: 70%;
  height: 100%;
  top: -5%;
  left: 35%;
}

.title {
  padding-bottom: 20px;
  line-height: 55px;
}

.title > span {
  display: block;          
  font-size: 3em;
  color: #363636;
}

.title > .topTitle {
  color: #8b2d9e;
  padding-bottom: 18px;
}

.title > .bottomTitle {
  font-size: 65pt;
  padding-bottom: 20px;
}

.subTitle {        
  line-height: 25px;
  padding-bottom: 15px;
  color: #363636;
}

.subTitle > span {
  display: block;          
  font-size: 20px;
}

.cosmos {
  position: absolute;
  width: 60%;
  height: 75%;
  top: 55%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.line {
  width: 80%;
}

/* 오픈 스터디관련 css */
.hover {
  overflow: hidden;
  position: relative;
  padding-bottom: 60%;
}


.hover-1 {
  box-shadow: 4px 4px 4px 2px rgba(0, 0, 0, 0.25);
  border-radius: 10px;
}

.hover-overlay {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 90;
  transition: all 0.4s;
}

.hover img {
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  transition: all 0.3s;
}

.hover-content {
  position: relative;
  z-index: 99;
}

.hover-1 img {
  width: 105%;
  position: absolute;
  top: 0;
  left: -5%;
  transition: all 0.3s;
}

.hover-1-content {
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 99;
  transition: all 0.4s;
  text-align: left;
}

.hover-1-number {
  position: absolute;
  bottom: 0;
  margin-bottom: 47%;
  margin-left: 75%;
  font-size: 15pt;
  background-color: rgba(34, 34, 34, 0.9);
  width: 80px;
  border-radius: 30px;
}

.hover-1 .hover-overlay {
  background: rgba(0, 0, 0, 0.5);
}

.hover-1-description {
  transform: translateY(0.5rem);
  transition: all 0.4s;
  opacity: 0;
}

.hover-1:hover .hover-1-content  {
  bottom: 1rem;
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  text-align: center;
}

.hover-1:hover .hover-1-description {
  opacity: 1;
  transform: none;
}

.hover-1:hover img {
  left: 0;
}

.hover-1:hover .hover-overlay {
  opacity: 0;
}

.studyImg {
  width: 100%;
  height: 100%;
}

/* 오픈 스터디 슬라이드 좌,우 화살표 */
.slick-prev::before{
  content: url(../assets/icon_prev.png);
}
.slick-next::before{
  content: url(../assets/icon_next.png);
}
/* 모달 스타일 */
.black-bg{
  z-index: 2;
  width: 100vw;
  margin-left: calc(-50vw + 50%);
  height: 100vw;
  background: rgba(0,0,0,0.5);
  position: fixed;
  left: 0;
  top: 0;
  padding: 20px;
}
.white-bg{
	margin-top: 10%;
	z-index: 3;
  width: 30%;
  background: white;
  border-radius: 8px;
  padding: 20px;
}

* {
  font-family:'yg-jalnan';
}
.enterBtn {
  border: none;
  border-radius: 8px;
  background-color: #afa2dd;
  height: 40px;
  width: 50px;
}
.cancelBtn {
  border: none;
  border-radius: 8px;
  color: white;
  background-color: #6c757d;
  height: 40px;
  width: 50px;
}
.cancelBtn:hover {
  background-color: #495057;
}
/** 모달 스타일 부분 끝*/

/** 랭킹 스타일 */
.rolling_box{
    width: 100%;
    height: 70px;
    text-align: left;
    padding-left: 15px;
    border: 3px solid #afa2dd;
    /* box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.25); */
    border-radius: 5px;
}

.rolling_box ul {
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: relative;
}

.rolling_box ul li {
  width: 100%;
  height: 100%;
  line-height: 50px;
  transition: .5s;
  position:absolute;
  transition: top .75s;
  top: 100%;
  z-index: 1;
  background-color: #ffffff;
}

.card_sliding{
    top: 0 !important;
    z-index: 100 !important;
} 

.card_sliding_after{
    top: -100% !important;
    z-index: 10 !important;
}

.rolling_box ul li p {
    font-size: 30px;
    line-height: 40px;
    font-weight: bold;
}

.before_slide {
    transform: translateY(100%);
}

.after_slide {
    transform: translateY(0);
}

#downArrowBtn {
  width: 30px;
  height: 30px;
  margin-top: 18px;
  margin-right: 15px;
  cursor: pointer;
}

#exitBtn{ 
  width: 20px;
  height: 20px;
  cursor: pointer;
}

.rankTable {
  height: 300px;
  overflow: auto;
}

.scrollTable {
  width: 100%;
}

.rankHeader {
  text-align: right;
}

.rankTable::-webkit-scrollbar {
  width: 20px;
}

.rankTable::-webkit-scrollbar-track {
  background-color: #e4e4e4;
  border-radius: 100px;
}

.rankTable::-webkit-scrollbar-thumb {
  border-radius: 100px;
  border: 6px solid rgba(0, 0, 0, 0.18);
  border-left: 0;
  border-right: 0;
  background-color: #afa2dd;
}

.rankCategory {
  font-size: 18pt;
  color: #8b2d9e;
}

#plusBtn {
  width: 20px;
  height: 20px;
  margin-top: 20px;
  cursor: pointer;
}

.studyHeader {
  margin-bottom: 20px;
}

</style>