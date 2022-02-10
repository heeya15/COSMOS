<template>
  <div id="main_page">
      <!-- <b-carousel
        id="carousel-fade"
        style="text-shadow: 0px 0px 2px #000"
        fade
        indicators
        :interval="3000"
        img-width="1024"
        img-height="480"
      >
        <b-carousel-slide
          img-src="https://i.ibb.co/Vqz7Dcf/main-img1.png.jpg"
        ></b-carousel-slide>
        <b-carousel-slide
          img-src="https://i.ibb.co/1Msjgmz/main-img2.jpg"
        ></b-carousel-slide>
      </b-carousel> -->
      <!-- <div class="circle"></div> -->

      <!-- MainPage Banner Start -->
      <div class="banner">
        <div class="bannerBox">
          <div class="leftBox">
            <img class="cosmos" src="@/assets/main_img7.png" alt="코스모스">
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



      <!-- 게시판 목록 Start -->
      <center>
        <h1 class="text-center mb-5">모집 중인 스터디</h1>
        <div id="board_section">
          <table class="table table-boardered table-hover">
            <col style="width:70%">
            <col style="width:30%">

            <thead align="center">
              <th>🌼 게시글 제목 🌼</th>
              <th>🌼 분류 🌼</th>
            </thead>
            <tbody v-for="(board, idx) in boardList" :key="idx" @click="goBoardDetail(board.boardNo)">
              <td><p class="mx-3">🌷 {{ board.contentTitle }} 🌷</p></td>
              <td align="center"><p>🌷 {{ board.studytypeName }} 🌷</p></td>
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
        <!-- <div class="my-5 p-5" align="center">  
          <div v-if="publicStudyList.length >= 1" >
            <div class="row">
              <div v-for="publicstudy in publicStudyList" :key="publicstudy.publicstudyroomId" class="col-md-4 mb-3 mb-lg-2">
                <div class="hover hover-1 text-white rounded"><img src="https://bootstrapious.com/i/snippets/sn-img-hover/hoverSet-3.jpg" alt="" @mouseover="getPublicStudyMember(publicstudy.publicstudyroomId)">
                  <div class="hover-overlay"></div>
                  <div class="hover-1-content px-5 py-4">
                    <h3 class="hover-1-title text-uppercase font-weight-bold mb-0"><span class="font-weight-light">{{ publicstudy.studyName }}</span></h3>
                    <p class="hover-1-description font-weight-light mb-0">{{ publicstudy.studyType.studytypeName }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div v-else>아직 가입한 스터디가 없습니다.</div>
        </div>
      </div>

      <div> -->
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
            <div v-for="(publicstudy, idx) in publicStudyList" :key="idx" class="px-5 mb-lg-2">
                <div class="hover hover-1 text-white rounded">
                  <img class="studyImg" :src="publicstudy.image" alt="Study Image is missing... :(" @click="goStudyRoom(publicstudy)">
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

export default {
  name: 'MainPage',
  components: { VueSlickCarousel },

  data() {
    return {
      // slide: 0,
      // sliding: null
      // board_no: this.$store.state.boardNo,
      boardItems: null,
      boardList: [],
      // publicStudyList: [{
      //   image: '',
      //   numberOfMember: 0,
      //   publicstudyroomId: '',
      //   studyName: '',
      //   studyRule: '',
      //   studyType: {
      //     studyTypeName: '',
      //     studyTypeNo: '',
      //   },
      //   url: '',
      // }],

      publicStudyList: [],
      currentParticipant: [],


      slickOption: {
        arrows: true,
        dots: true,
        infinite: true, 
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 1,
        swipeToSlide: true,
      },

    }
  },

  methods: {
    // carousel 메소드
    // onSlideStart(slide) {
    //   console.log(slide)
    //   this.sliding = true
    // },

    // onSlideEnd(slide) {
    //   console.log(slide)
    //   this.sliding = false
    // },
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

    // 공개 방 가기
    goStudyRoom(studyroominfo) {
      console.log(">>>>>>>>>>> ", studyroominfo)
    }
  },

  created() {
    this.getBoardItems()
    this.getPublicStudy()
  },

  mounted() {
    setInterval(this.getPublicStudy, 5000);
    console.log("5 second later")
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

#board_section {
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

</style>