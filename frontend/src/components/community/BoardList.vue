<template>
  <div>
    <!-- <h3>게시글</h3> -->
      <b-row>
        <b-col>
          <table  
          class="table table-boarded table-hover"
          width="100%"
          
          >
            <thead>
              <tr>
                <th>번호</th>
                <th>상태</th>
                <th>말머리</th>
                <th>제목</th>
                <th>분류</th>
                <th>작성자</th>
                <th>등록일</th>
              </tr>
            </thead>
            <tbody id="test-table" v-for="(boardItem, idx) in paginatedItems" :key="idx"  @click="goBoardDetail(boardItem.boardNo)">
              <tr>
              <td>{{ 10*(currentPage-1)+(idx+1) }}</td>
            <td v-if="boardItem.contentStatus === true"><p class="boardnum_tag">[진행중]</p></td>
            <td v-else><p class="boardnum_tag">[완료]</p></td>
            <td><span v-if="boardItem.header === false" class="boardnum_tag">[스터디원 구함]</span>
            <span v-else class="boardnum_tag">[스터디 구함]</span></td>
            <td><p class="boardnum_tag">{{ boardItem.contentTitle }}</p></td>
            <td><p class="boardnum_tag">{{ boardItem.studytypeName }}</p></td>
            <td><p class="boardnum_tag">{{ boardItem.user.userName }}</p></td>
            <td><p class="boardnum_tag">{{ makeDate(boardItem.createdAt) }}</p></td>
            </tr>
            </tbody>
          </table>
        </b-col>
      </b-row>

    <b-pagination
      @click="onPageChanged"
      v-model="currentPage"
      :total-rows="rowws"
      :per-page="perPage"
      aria-controls="test-table"
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
      perPage: 10,
      currentPage: 1,
      paginatedItems: '',
      items: [],
      board_no: this.$store.state.boardNo,
      boardNum: this.$store.state.boardNo,
      saveHeader: null,
      boardItems: null,
      rowws: null,
    }
  },
  
  methods: {
    paginate (page_size, page_number) {
    
    let itemsToParse = this.boardItems
    console.log(itemsToParse.slice(0, 5))
    console.log(page_number * page_size, (page_number + 1) * page_size)
    this.paginatedItems = itemsToParse.slice(page_number * page_size, (page_number + 1) * page_size);
 
    },
    onPageChanged() {
      console.log(this.currentPage)
      this.paginate(10, this.currentPage - 1)
    },



    getBoardItems() {
      axios({
        method: 'get',
        url: 'http://i6e103.p.ssafy.io:8080/api/board/searchAll',
        // headers: this.
      })
      .then(res => {
        // console.log(res.data)
        const saveResData = res.data
        saveResData.forEach(item => {
        if (item.header === false) {
          item.header = '[스터디원 구함]'
          } else { 
            item.header = '[스터디 구함]'
          }
        if (item.contentStatus === true) {
          item.contentStatus = '[진행중]'
        } else {
          item.contentStatus = '[완료]'
        }
        })
        this.saveHeader = saveResData
        this.boardItems = res.data
        this.rowws = res.data.length
        this.items = res.data
        console.log('번호 확인용')
        console.log(res.data)
        if (this.boardItems) {
            this.paginate(10, 0)

    }
      })
      .catch(err => {
        console.log(err)
      })
    },
    goBoardDetail(boardItemsIdx) {
      console.log('상세보기로')
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
  },
  watch : {
    boardItems : {
      handler() {
        this.paginate(10, 0)
      },
      deep : true
    },
    currentPage(neww) { 
      this.paginate(10, neww - 1)
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