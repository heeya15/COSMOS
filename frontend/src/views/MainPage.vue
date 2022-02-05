<template>
  <div id="main_page">
    <div class="mb-5" id="main_section">
      <div id="carousel-example-generic" class="carousel slide">
        <ol class="carousel-indicators ">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <div class="item active">
            <img class="carousel-item" src="@/assets/main_img1.jpg">
          </div>
          <div class="item">
            <img class="carousel-item" src="@/assets/main_img2.jpg">
            </div>
          </div>
          <a
              class="left carousel-control"
              href="#carousel-example-generic"
              role="button"
              data-slide="prev">
              <!-- 왼쪽 화살표 -->
              <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          </a>
          <!-- 오른쪽 화살표 버튼 -->
          <!-- href는 carousel의 id를 가르킨다. -->
          <a
              class="right carousel-control"
              href="#carousel-example-generic"
              role="button"
              data-slide="next">
              <!-- 오른쪽 화살표 -->
              <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          </a>
      </div>
    </div>
    <b-row>
      <b-col cols="6" class="board_section">
        <h2>board content</h2>
      </b-col>
      <b-col cols="6" class="stduy_section">
        <h2>open study</h2>
      </b-col>
    </b-row>
  </div>
</template>

<script scoped>
  export default {name: 'MainPage'}
</script>

<style>
#main_page {
  height: 90%;
}

#main_secion {
  height: 30%;
}

.carousel-inner>.item>a>img, .carousel-inner>.item>img, .img-responsive, .thumbnail a>img, .thumbnail>img {
  height: 600px;
}

.carousel-item {
  height: 100%;
}

#board_secion {
  height: 30%;
}

#study_secion {
  height: 30%;
}

</style>