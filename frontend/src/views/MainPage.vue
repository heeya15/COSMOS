<template>
  <div id="main_page">
    <div class="mb-5" id="main_section">
      <b-carousel
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
      </b-carousel>
    </div>


    <b-row>
      <b-col cols="1"></b-col>
      <b-col cols="5" class="board_section">
        <h2>board content</h2>
      </b-col>
      <b-col cols="1"></b-col>
      <b-col cols="5" class="stduy_section">
        <h2>open study</h2>
      </b-col>
    </b-row>
  </div>
</template>

<script scoped>
  export default {
    name: 'MainPage',
  
    data() {
      return {
        slide: 0,
        sliding: null
      }
    },

    methods: {
      onSlideStart(slide) {
        console.log(slide)
        this.sliding = true
      },
      onSlideEnd(slide) {
        console.log(slide)
        this.sliding = false
      }
    }
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
  height: 30%;
}

#study_secion {
  height: 30%;
}

/* .carousel-indicators li {
  background-color: transparent !important;
} */
</style>