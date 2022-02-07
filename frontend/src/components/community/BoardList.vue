<template>
  <div class="container">
    <!-- <h3>게시글</h3> -->
    <div class="searchbar mb-4">
      <b-form-select class="mx-1" v-model="stateSelected" :options="stateOpt" style="width: 150px; height: 40px; font-size: 15px;" @change="stateSel()" ></b-form-select>
      <b-form-select class="mr-3" v-model="headerSelected" :options="headerOpt" style="width: 170px; height: 40px; font-size: 15px;" @change="headerSel()" ></b-form-select>
      <b-form-select class="mx-1" v-model="searchSelected" :options="searchOpt" style="width: 150px; height: 40px; font-size: 15px;" @change="headerSel()" ></b-form-select>
      <b-form-input class="mr-2" style="width: 400px; height: 40px; font-size: 15px;" placeholder="검색할 제목,분류를 입력하세요." v-model="word"></b-form-input>
      <b-button class="extraBtnTag mr-1" style="width: 40px padding: 0; height: 40px; font-size: 15px;" @click="searchTitle()">검색</b-button>
      <b-button class="extraBtnTag" style="padding: 0; width: 60px; height: 40px; font-size: 15px;" @click="searchInit()">초기화</b-button>
    </div>
      <b-row>
        <b-col>
          <table  
          class="table table-boarded table-hover"
          width="100%"
          >
            <thead>
              <tr style="text-align: center; background-color: #afa2dd;">
                <th>번호</th>
                <th>상태</th>
                <th>말머리</th>
                <th>제목</th>
                <th>분류</th>
                <th>작성자</th>
                <th>등록일</th>
              </tr>
            </thead>
            <tbody id="test-table" v-for="(boardItem, idx) in paginatedItems" :key="idx"  @click="goBoardDetail(boardItem.boardNo)" style="text-align: center;">
              <tr>
              <td>{{ 10*(currentPage-1)+(idx+1) }}</td>

            <div>
              <td v-if="boardItem.contentStatus === false"><p class="boardnum_tag" style="color: #d5648a;">[진행중]</p></td>
              <td v-else><p class="boardnum_tag">[완료]</p></td>
            </div>

            <td><span v-if="boardItem.header === false" class="boardnum_tag" style="color: #d5648a;">[스터디원 구함]</span>
            <span v-else class="boardnum_tag" style="color: #afa2dd;">[스터디 구함]</span></td>
            <td><p class="boardnum_tag" >{{ boardItem.contentTitle }}</p></td>
            <td><p class="boardnum_tag">{{ boardItem.studytypeName }}</p></td>
            <td><p class="boardnum_tag">{{ boardItem.user.userName }}</p></td>
            <td><p class="boardnum_tag">{{ makeDate(boardItem.createdAt) }}</p></td>
            </tr>
            </tbody>
          </table>
        </b-col>
      </b-row>

      <div class="create_boardform mx-5">
        <b-button class="btnTag" @click="goCreateForm">글생성</b-button>
      </div>

    <b-pagination
      @click="onPageChanged"
      v-model="currentPage"
      :total-rows="rowws"
      :per-page="perPage"
      aria-controls="test-table"
      align="center"
    >
    </b-pagination>

    
  </div>

</template>

<script>
// import http from 'http'
import http from "@/util/http-common.js";

export default {
  name: 'BoardList',
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      paginatedItems: '',
      board_no: this.$store.state.boardNo,
      boardNum: this.$store.state.boardNo,
      saveHeader: null,
      boardItems: null,
      rowws: null,
      stateSelected: null,
      headerSelected: null,
      searchSelected: "title",
      
      // filter 사용될 데이터들
      stateOpt: [
        { value: null, text: '상태 선택' },
        { value: false, text: '진행중' },
        { value: true, text: '완료' },
      ],
      headerOpt: [
        { value: null, text: '말머리 선택' },
        { value: false, text: '스터디원 구함' },
        { value: true, text: '스터디 구함' },
      ],
      searchOpt: [
        { value: "title", text: '제목' },
        { value: "type", text: '분류' },
      ],

      src: [], // 초기 boardlist를 저장할 배열
      word: "",
      str: null,
    }
  },
  
  methods: {
    paginate (page_size, page_number) {
    
    let itemsToParse = this.boardItems
    console.log(itemsToParse.slice(0, 5))
    console.log(page_number * page_size, (page_number + 1) * page_size)
    this.paginatedItems = itemsToParse.slice(page_number * page_size, (page_number + 1) * page_size);
    console.log(this.page_number, '위쪽 확인')

    },
    onPageChanged() {
      console.log(this.currentPage, '여기는?')
      // this.currentPage = 
      this.paginate(10, this.currentPage - 1)
      console.log('작동하나?')
    },

    goCreateForm() {
      this.$router.push({name: 'BoardForm'})
    },

    getBoardItems() {
      http({
        method: 'get',
        url: '/board/searchAll',

      })
      .then(res => {
        this.boardItems = res.data
        this.src = res.data
        this.rowws = res.data.length
        this.items = res.data
        console.log('번호 확인용')
        console.log(res.data)
        if (this.boardItems) {
            this.paginate(10, 0)
        }
        this.boardItems = res.data.sort(function(a, b) {
          return b.boardNo - a.boardNo;
        })
        
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
    // pickSelect(event) {
    //   console.log(event.target.value)
    // }
    searchPaging(){
      this.rowws = this.boardItems.length;
      this.currentPage = 1;
    },
    searchInit(){
      this.headerSelected = null;
      this.stateSelected = null;
      this.word = "";
      this.boardItems = this.src;
      this.searchPaging();
    },
    searchAll(){
      this.boardItems = this.src;
      this.searchPaging();
    },
    headerSel(){
      this.word ="";

      if(this.headerSelected == null){ // 말머리 선택을 안했을 때
        if(this.stateSelected == null){ // 상태 선택을 안했을 때
          this.searchAll(); // 전체 목록 불러오기
        } else{ // 상태 선택을 했을 때
            this.boardItems = this.src.filter((boardItem) => { // 상태에 해당하는 게시글 불러오기
            return boardItem.contentStatus == this.stateSelected;
          });
          this.searchPaging();
        }
      } else{ // 말머리 선택을 했을 때
        if(this.stateSelected == null){ // 상태 선택이 안 되어 있을 때
          this.boardItems = this.src.filter((boardItem) => { // 말머리에 해당하는 게시글 불러오기
            return boardItem.header == this.headerSelected;
          });
          this.searchPaging();
        } else{ // 상태 선택이 되어 있을 때
          this.boardItems = this.src.filter((boardItem) => { // 말머리와 상태에 해당하는 게시글 불러오기
            return boardItem.header == this.headerSelected && boardItem.contentStatus == this.stateSelected;
          });
          this.searchPaging();
        }
      }

    },

    stateSel(){
      this.word ="";

      if(this.stateSelected == null){ // 상태 선택을 안했을 때
        if(this.headerSelected == null){ // 말머리 선택을 안했을 때
          this.searchAll(); // 전체 목록 불러오기
        } else{ // 말머리 선택을 했을 때
          this.boardItems = this.src.filter((boardItem) => { // 말머리에 해당하는 게시글 불러오기
            return boardItem.header == this.headerSelected;
          });
          this.searchPaging();
        }
      } else{ // 상태 선택을 했을 때
        if(this.headerSelected == null){ // 말머리 선택이 안 되어 있을 때
          this.boardItems = this.src.filter((boardItem) => { // 상태에 해당하는 게시글 불러오기
            return boardItem.contentStatus == this.stateSelected;
          });
          this.searchPaging();
        } else{ // 말머리 선택이 되어 있을 때
          this.boardItems = this.src.filter((boardItem) => {  // 말머리와 상태에 해당하는 게시글 불러오기
            return boardItem.header == this.headerSelected && boardItem.contentStatus == this.stateSelected;
          });
          this.searchPaging();
        }
      }

    },
    searchTitle(){
      if(this.searchSelected == "title"){ // 제목이 선택 되었을 때
        if (this.word == "") { // 아무 것도 입력 되지 않았을 때
          alert("제목을 입력해주세요.")
        } else {
          if(this.headerSelected == null && this.stateSelected == null){
              this.boardItems = this.src.filter((boardItem) => {
                if(boardItem.contentTitle.toLowerCase().includes(this.word.toLowerCase())){
                  return boardItem
                }
              });
              this.searchPaging();
          }else{
            this.boardItems = this.src.filter((boardItem) => {
              if(boardItem.contentTitle.toLowerCase().includes(this.word.toLowerCase())){
                if(this.headerSelected != null && this.stateSelected != null){ 
                  return (boardItem.header == this.headerSelected && boardItem.contentStatus == this.stateSelected)
                }
                else if(this.headerSelected != null){
                  return boardItem.header == this.headerSelected
                }
                else if(this.stateSelected != null){
                  return boardItem.contentStatus == this.stateSelected
                }
              }
            });
            this.searchPaging();
          }
        }
      }else if(this.searchSelected == "type"){ // 분류가 선택 되었을 때
        if (this.word == "") { // 아무 것도 입력 되지 않았을 때
          alert("분류를 입력해주세요.")
        } else {
          if(this.headerSelected == null && this.stateSelected == null){
            this.boardItems = this.src.filter((boardItem) => {
              if(boardItem.studytypeName!=null && boardItem.studytypeName.toLowerCase().includes(this.word.toLowerCase())){
                console.log(boardItem.studytypeName)
                return boardItem
              }
            });
            this.searchPaging();  
          }else{
            this.boardItems = this.src.filter((boardItem) => {
              if(boardItem.studytypeName!=null && boardItem.studytypeName.toLowerCase().includes(this.word.toLowerCase())){
                if(this.headerSelected != null && this.stateSelected != null) 
                  return (boardItem.header == this.headerSelected && boardItem.contentStatus == this.stateSelected)
                else if(this.headerSelected != null)
                  return boardItem.header == this.headerSelected
                else if(this.stateSelected != null)
                  return boardItem.contentStatus == this.stateSelected
              }
            });
            this.searchPaging();
          }
        }
      }


      
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
/* ul .page-item{
  width: 1100px;
} */

.searchbar {
  display: flex;
  justify-content: center;
}


.create_boardform {
  display: flex;
  justify-content: right;
}

/* 게시판 폰트 */
#test-table {
  font-family: BMJua;
  /* font-weight: normal; */
  font-size: 17px;
}


.boardnum_tag:hover {
  cursor: pointer;
}


.btnTag {
  background-color: #afa2dd;
  border: none;
}

.btnTag:hover {
  background-color: #c8c1e4;
}

.extraBtnTag {
  background-color: #afa2dd;
  border: none;
  color: black;
}

.extraBtnTag:hover {
  background-color: #c8c1e4;
}


</style>