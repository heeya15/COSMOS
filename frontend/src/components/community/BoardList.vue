<template>
  <div class="container">
    <!-- <h3>게시글</h3> -->
    <div class="searchbar mb-4">
      <b-form-select class="mx-1" v-model="stateSelected" :options="stateOpt" style="width: 150px; height: 40px; font-size: 15px;" @change="stateSel()" ></b-form-select>
      <b-form-select class="mr-3" v-model="headerSelected" :options="headerOpt" style="width: 170px; height: 40px; font-size: 15px;" @change="headerSel()" ></b-form-select>
      <b-form-select class="mx-1" v-model="searchSelected" :options="searchOpt" style="width: 150px; height: 40px; font-size: 15px;" @change="headerSel()" ></b-form-select>
      <b-form-input b-form-input class="mr-2" style="width: 400px; height: 40px; font-size: 15px;" placeholder="검색할 제목,분류를 입력하세요." v-model="word" @keydown.enter="searchTitle()"></b-form-input>
      <b-button class="extraBtnTag mr-1" style="width: 40px padding: 0; height: 40px; font-size: 15px;" @click="searchTitle()">검색</b-button>
      <b-button class="extraBtnTag" style="padding: 0; width: 60px; height: 40px; font-size: 15px;" @click="searchInit()">초기화</b-button>
    </div>
      <b-row>
        <b-col>
          <table  
          class="table table-boarded"
          width="100%"
          >
            <thead>
              <tr style="text-align: center; background-color: #afa2dd;">
                <th>번호</th>
                <th>상태</th>
                <th>말머리</th>
                <th>제목</th>
                <th>분류</th>
                <th>작성자 (ID)</th>
                <th>등록일</th>
              </tr>
            </thead>
            <tbody id="test-table" v-for="(boardItem, idx) in paginatedItems" :key="idx"  @click="goBoardDetail(boardItem.boardNo)" style="text-align: center;">
              <tr>
              <!-- 1~10 까지 -->
              <!-- <td >{{ 10*(currentPage-1)+(idx+1) }}</td> -->

              <!-- 역순 -->
              <td class="boardnum_tag">{{ boardItems.length-idx-10*(currentPage-1) }}</td>

              <!-- 게시글 고유 번호 -->
              <!-- <td >{{ boardItem.boardNo }}</td> -->


            <td style="width: 100px" v-if="boardItem.contentStatus === false"><span class="boardnum_tag" style="color: #d5648a;">[진행중]</span></td>
            <td v-else><p class="boardnum_tag">[완료]</p></td>


            <td style="width: 150px;" v-if="boardItem.header === false"><span  class="boardnum_tag" style="color: #d5648a;">[스터디원 구함]</span></td>
            <td style="width: 150px;" v-else><span class="boardnum_tag" style="color: #afa2dd;">[스터디 구함]</span></td>
            <td style="width: 300px;"><p class="content_title_tag">{{ boardItem.contentTitle }}</p></td>
            <td style="width: 150px;"><p class="boardnum_tag">{{ boardItem.studytypeName }}</p></td>
            <td><p class="boardnum_tag">{{ boardItem.user.userName }} ({{ boardItem.user.userId }})</p></td>
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
      @page-click="pageClick"
      
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

    },
    pageClick: function (button, page){
      this.currentPage = page;
      // this.getNoticeListByPage(page);
      console.log(button, '새함수')
      this.paginate(10, this.currentPage - 1)
      this.$store.dispatch('pageClick', this.currentPage)
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

    // 상세보기로
    goBoardDetail(boardItemsIdx) {
      console.log(boardItemsIdx)
      this.$store.dispatch('getBoardNo', boardItemsIdx)
      this.$router.push({ name: 'BoardDetail', query: { pageId: this.currentPage }})
    },

    // 등록일 슬라이싱
    makeDate(datetime) {
      const old = ''+datetime
      return old.substring(0, 10)
    },

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

tbody:hover {
  background-color: #c8c1e4;
}

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
  font-size: 17px;
}

.content_title_tag {
  width: 250px; 
  text-overflow: ellipsis; 
  overflow: hidden; 
  white-space: nowrap;
  position: relative;
  left: 30px;
}

.content_title_tag:hover {
  cursor: pointer;
}

.boardnum_tag:hover {
  cursor: pointer;
}


.btnTag {
  background-color: #afa2dd;
  border: none;
  color: black;
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