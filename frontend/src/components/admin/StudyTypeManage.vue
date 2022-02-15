<template>
    <div class="container">
        <div class="container">
            <!-- 검색 Start -->
            <div class="searchbar mb-4 d-flex justify-content-center">
                <b-form-select class="mx-1" id="selectSearchOption" v-model="searchSelected" :options="searchOpt" @change="headerSel()" ></b-form-select>
                <b-form-input class="mr-2" id="inputSearchOption" placeholder="검색할 키워드를 입력하세요." v-model="word" @keydown.enter="search"></b-form-input>
                <b-button class="mr-1" id="studyTypeSearchBtn" @click="search">검색</b-button>
                <b-button id="resetSearchBtn" @click="searchInit()">초기화</b-button>
            </div>
            <!-- 검색 End -->

            <!-- 목록 Start -->
            <b-row>
                <b-col>
                <table   
                class="table table-boarded table-hover"
                width="100%"
                >
                <thead>
                    <tr id="tablehead" align="center" style="background-color: #afa2dd">
                        <th>번호</th>
                        <th>스터디 분류명</th>
                        <th>수정/삭제</th>
                    </tr>
                </thead>
                <tbody id="studyTypeTable" align="center" v-for="(studytype, idx) in paginatedItems" :key="idx">
                    <td>{{ studytype.studytypeNo }}</td>
                    <td>{{ studytype.studytypeName }}</td>
                    <td>
                        <b-button id="editBtn" @click="editModal(studytype)" v-b-modal.modal-lg>수정</b-button>
                        <b-button class="ml-1" id="deleteBtn" @click="deleteStudyType(studytype.studytypeName)" >삭제</b-button>
                    </td>
                </tbody>
                </table>
                </b-col>
            </b-row>
            <!-- 목록 End -->

            <b-modal ref="myModal" id="modal-lg" hide-header hide-footer size="lg">
                <div style='font-family: "yg-jalnan";'>
                    <h3>스터디 분류 수정</h3>
                
                    <hr>
                    
                    <div class="d-flex justify-content-around" align="center">
                        <span id="modalinput">{{currentItem.studytypeno}}</span>
                        <input style="width: 50%;" v-model="updatestudytypename" id="modalinput" placeholder="수정할 스터디 분류명을 입력해주세요...."/>
                    </div>
                    
                    <hr>
                    
                    <div class="justify-content-center" align="right">
                        <b-button class="m-2" variant="success" @click="updateStudyType">수정</b-button>
                        <b-button class="m-2" @click="$bvModal.hide('modal-lg')">취소</b-button>
                    </div>
                </div>
            </b-modal>
        </div>
        <div class="mx-5" align="right">
            <b-button class="createBtn" v-b-modal.create-modal-lg>추가</b-button>
        </div>
        <b-modal ref="myModal" id="create-modal-lg" hide-header hide-footer size="lg">
            <div style='font-family: "yg-jalnan";'>
                <h3>스터디 분류 추가</h3>
            
                <hr>
                
                <div align="center">
                    <input style="width: 50%;" v-model="createstudytypename" id="modalinput" placeholder="추가할 스터디 분류명을 입력해주세요...."/>
                </div>
                
                <hr>
                
                <div align="right">
                    <b-button class="m-2" style="background-color: AFA2DD;" variant="success" @click="createStudyType">추가</b-button>
                    <b-button class="m-2" @click="$bvModal.hide('create-modal-lg')">취소</b-button>
                </div>
            </div>
        </b-modal>
        <b-pagination
        @click="onPageChanged"
        v-model="currentPage"
        :total-rows="rowws"
        :per-page="perPage"
        aria-controls="studyTypeTable"
        align="center"
        >
        </b-pagination>
    </div>  
</template>

<script>
import http from "@/util/http-common.js";

export default ({
    name: 'StudyTypeManage',

    data() {
        return {
            studytypes: null,
            perPage: 10,
            currentPage: 1,
            paginatedItems: '',
            rowws: null,
            currentItem: {
                studytypeno: '',
                studytypename: '',
            },
            updatestudytypename: '',
            createstudytypename: '',
            categorySelected: null,

            // filter에 사용될 데이터
            searchOpt: [
                { value: "all", text: "전체"},
                { value: "category", text: "스터디 분류명"}, 
            ],

            searchSelected: "all",
            src: [],     // 초기 userlist를 저장할 배열
            word: "",
            str: null,
        }
    },

    methods: {
        paginate (page_size, page_number) {
            let itemsToParse = this.studytypes
            console.log(itemsToParse.slice(0, 5))
            console.log(page_number * page_size, (page_number + 1) * page_size)
            this.paginatedItems = itemsToParse.slice(page_number * page_size, (page_number + 1) * page_size);
        },
        
        onPageChanged() {
            console.log(this.currentPage)
            this.paginate(10, this.currentPage - 1)
        },

        getStudyTypes() {
            http({
                method: 'GET',
                url: '/study/studyType',
            })
            .then(res => {
                this.studytypes = res.data
                this.src = res.data
                this.rowws = res.data.length
                this.items = res.data
                if(this.studytypes) {
                    this.paginate(10, 0)
                }
            })
            .catch(err => {
                console.log(err)
            })
        }, 

        // 스터디 분류명 추가
        createStudyType() {
            http({
                method: 'POST',
                url: `/studytype/register`,
                params: {
                    studytype_name: this.createstudytypename,
                }
            })
            .then(() => {
                this.$refs["myModal"].hide();
                this.getStudyTypes();
            })
            .catch(err => {
                console.log(err);
            })
        },

        // 스터디 분류명 수정 모달창에 정보 넘겨주기
        editModal(item) {
            this.currentItem.studytypeno = item.studytypeNo;
            this.currentItem.studytypename = item.studytypeName;
            console.log(this.currentItem);
        },

        // 스터디 분류명 수정
        updateStudyType() {
            http({
                method: 'PUT',
                url: `/studytype/update`,
                params: {
                    studytype_no: this.currentItem.studytypeno,
                    studytype_name: this.updatestudytypename,
                }
            })
            .then(() => {
                this.$refs["myModal"].hide();
                this.getStudyTypes();
            })
            .catch(err => {
                console.log(err);
            })
        },

        // 스터디 분류명 삭제
        deleteStudyType(studyTypeName) {
            if(confirm('정말 삭제하시겠습니까?') == true) {
                http({
                    method: 'DELETE',
                    url: `/studytype/remove/studyType`,
                    params: {
                        studytype_name: studyTypeName,
                    }
                })
                .then(() => {
                    this.getStudyTypes();
                })
                .catch(err => {
                    console.log(err);
                })
            }
        }, 

        searchPaging(){
            this.rowws = this.studytypes.length;
            this.currentPage = 1;
        },

        searchInit(){
            this.categorySelected = null;
            this.word = "";
            this.studytypes = this.src;
            this.searchPaging();
        },

        searchAll() {
            this.studytypes = this.src;
            this.searchPaging();
        },

        headerSel() {
            this.word = "";

            if(this.searchSelected == "") {
                this.searchAll();
            }
        },

        search() {
            if(this.searchSelected == "category") {     // 분류명으로 찾기
                if(this.word == "") {   // 찾을 키워드를 입력하지 않은 경우
                    alert("찾을 아이디를 입력하세요.");
                } else {
                    this.studytypes = this.src.filter((studytype) => {
                        if(studytype.studytypeName.toLowerCase().includes(this.word.toLowerCase())){
                            return studytype
                        }
                    });
                    this.searchPaging();
                }
            } else {
                this.searchAll();
            }
        },

    },

    created() {
        this.getStudyTypes()
    },

    watch : {
        studytypes : {
            handler() {
                this.paginate(10, 0)
            },
            deep : true
        },
        currentPage(neww) { 
            this.paginate(10, neww - 1)
        }
    },

})
</script>

<style scoped>

.studyTypeTable {
    font-family: "BMJua";
}

#editBtn {
    background-color: #ffc107;
    border: none;
    color: #000;
}

#deleteBtn {
    background-color: #DC3545;
    border: none;
}

#modal {
    font-family: "yg-jalnan";
}

.createBtn {
    color: #000;
    background-color: #AFA2DD;
    border: none;
}

#selectSearchOption {
    width: auto;
}

#inputSearchOption {
    width: 300px;
}

#studyTypeSearchBtn {
    background-color: #afa2dd;
    color: #000;
    border: none;
}

#resetSearchBtn {
    background-color: #dc3545;
    border: none;
}

</style>
