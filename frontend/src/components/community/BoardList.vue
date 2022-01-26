<template>
  <div>
    <!-- <h3>게시글</h3> -->
    <b-row>
      <b-col>
        <div class="table_title">
          <p>번호</p>
          <p>상태</p>
          <p>제목</p>
          <p>분류</p>
          <p>작성자</p>
          <p>등록일</p>
        </div>
        <div class="table_content" v-for="(boardItem, idx) in boardItems" :key="boardItem.idx"  @click="goBoardDetail(boardItem.boardNo)">
          <p>{{ idx }}</p>
          <!-- <p class="boardnum_tag" >{{ boardItem.boardNo }}</p> -->
          <p v-if="boardItem.contentStatus === false" class="boardnum_tag">진행중</p>
          <p v-else class="boardnum_tag">완료</p>
          <span v-if="boardItem.header === false" class="boardnum_tag">[스터디원 구함]</span>
          <span v-else class="boardnum_tag">[스터디 구함]</span>
          <p class="boardnum_tag">{{ boardItem.contentTitle }}</p>
          <p class="boardnum_tag">{{ boardItem.studytypeName }}</p>
          <p class="boardnum_tag">{{ boardItem.user.userName }}</p>
          <p class="boardnum_tag">{{ makeDate(boardItem.createdAt) }}</p>
        </div>
      </b-col>
    </b-row>
    <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        first-text="First"
        prev-text="Prev"
        next-text="Next"
        last-text="Last"
        align="center"
      ></b-pagination>
  </div>  
</template>

<script>
import axios from 'axios'

export default {
  name: 'BoardList',
  data() {
    return {
      boardItems: null,
    }
  },
  methods: {
    getBoardItems() {
      axios({
        method: 'get',
        url: 'http://localhost:8080/api/board/searchAll',
        // headers: this.
      })
      .then(res => {
        console.log(res)
        this.boardItems = res.data
      })
      .catch(err => {
        console.log(err)
      })
    },
    goBoardDetail(boardItemsIdx) {
      console.log(boardItemsIdx)
      this.$store.dispatch('getBoardNo', boardItemsIdx)
      this.$router.push({ name: 'BoardDetail'})
    },
    makeDate(datetime) {
      const old = ''+datetime
      return old.substring(0, 10)
    },
  },
  created() {
    this.getBoardItems()
  }
}
</script>

<style scoped>

.table_content {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

.boardnum_tag:hover {
  cursor: pointer;
}

p {
  color: black !important;
  font-size: 1rem !important;
}

.table_title {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
</style>