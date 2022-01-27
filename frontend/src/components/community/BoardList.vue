<template>
  <div>
    <!-- <h3>게시글</h3> -->
    <b-row>
      <b-col>
        <div class="table_title">
          <p>번호</p>
          <p>상태</p>
          <p>말머리</p>
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
      :total-rows="rowws"
      :per-page="perPage"
      first-text="First"
      prev-text="Prev"
      next-text="Next"
      last-text="Last"
      align="center"
    ></b-pagination>
    
    <!-- <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination> -->

    <!-- <b-table
    id="my-table"
    :items="items"
    :per-page="perPage"
    :current-page="currentPage"
    :fields="fields"
    ></b-table> -->
    </div>

   
</template>

<script>
import axios from 'axios'

export default {
  name: 'BoardList',
  data() {
    return {
      boardItems: null,
      perPage: 10,
      currentPage: 1,
      rowws: null,
      items: [],
      // fields: [
      //   {
      //     key: "boardNo",
      //     label: "번호",
      //   },
      //   {
      //     key: "contentStatus",
      //     label: "상태",
      //   },
      //   {
      //     key: "header",
      //     label: "말머리",
      //   },
      //   {
      //     key: "contentTitle",
      //     label: "제목",
      //   },
      //   {
      //     key: "studyName",
      //     label: "스터디 이름",
      //   },
      //   {
      //     key: "studytypeName",
      //     label: "분류",
      //   },
      // ],
    }
  },
  methods: {
    getBoardItems() {
      axios({
        method: 'get',
        url: 'http://i6e103.p.ssafy.io:8080/api/board/searchAll',
        // headers: this.
      })
      .then(res => {
        console.log(res.data)
        this.boardItems = res.data
        this.rowws = res.data.length
        this.items = res.data
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
  },
  computed: {
    rows() {
      return this.rowws
    }
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