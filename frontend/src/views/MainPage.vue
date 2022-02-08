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
            <img src="@/assets/main_img7.png" alt="">
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
        <h1 class="text-center">모집 중인 스터디</h1>
        <div id="board_secion">
          <table class="table table-boardered table-hover">
            <thead>
              <th>제목</th>
              <th style="display: flex; justify-content: flex-end;">분류</th>
            </thead>
            <tbody v-for="(board, idx) in boardList" :key="idx">
              <td><p>{{ board.studyName }}</p></td>
              <td><p style="float: right;">{{ board.studytypeName }}</p></td>
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
      </div>
      <!-- 오픈(공개) 스터디 목록 End -->

      <div class="my-5" align="center">  
        <hr class="line">
      </div>

      





  </div>
    <!-- <b-row>
      <b-col cols="1"></b-col>
      <b-col cols="5" class="board_section">
        <h2>board subTitle</h2>
      </b-col>
      <b-col cols="1"></b-col>
      <b-col cols="5" class="stduy_section">
        <h2>open study</h2>
      </b-col>
    </b-row> -->
</template>

<script scoped>
import http from "@/util/http-common.js";

export default {
  name: 'MainPage',

  data() {
    return {
      // slide: 0,
      // sliding: null
      boardItems: null,
      boardList: [],
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
      this.$router.push({name:'SignUp'})
    },

    // 게시글 리스트 받아오기 안됌, 왜?
    getBoardItems() {
      console.log("is it here?")
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
        

        // var len = this.boardItems.res.data.length > 5 ? 5 : this.boardItems.res.data.length;
        // for(var i=0; i<len; i++) {
        //   this.boardList.push(this.boardItems[i]);
        // }
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  created() {
    this.getBoardItems()
    console.log('와졌나?')
  },
}
</script>

<style scoped>

#main_page {
  height: 90%;
}

#main_secion {
  height: 30%;
}

#board_secion {
  /* display: flex; */
  /* justify-content: center; */
  height: 30%;
  width: 70%;
}

th, td {   
  /* text-align: center; */
  vertical-align : middle !important;
}

#study_secion {
  height: 30%;
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

img {
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

</style>