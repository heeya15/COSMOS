<template>
    <div class="container">
        <div class="container">
            <!-- 검색 -->
            <div class="searchbar mb-4 d-flex justify-content-center">
                <b-form-select class="mx-1" id="selectSearchOption" v-model="searchSelected" :options="searchOpt" @change="headerSel()" ></b-form-select>
                <b-form-input class="mr-2" id="inputSearchOption" placeholder="검색할 키워드를 입력하세요." v-model="word" @keydown.enter="search" autocomplete="off"></b-form-input>
                <b-button class="mr-1" id="userSearchBtn" @click="search">검색</b-button>
                <b-button id="resetSearchBtn" @click="searchInit()">초기화</b-button>
            </div>

            <!-- 목록 -->
            <b-row>
                <b-col>
                <table   
                class="table table-boarded table-hover"
                width="100%"
                >
                <thead>
                    <tr id="tablehead" align="center" style="background-color: #afa2dd">
                        <th>번호</th>
                        <th>이름</th>
                        <th>아이디</th>
                        <th>이메일</th>
                        <th>등록일</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody id="userTable" v-for="(user, idx) in paginatedItems" :key="idx">
                    <tr align="center">
                        <td>{{ 10*(currentPage-1)+(idx+1) }}</td>
                        <td><p>{{ user.userName }}</p></td>
                        <td><p>{{ user.userId }}</p></td>
                        <td><p>{{ user.userEmail }}</p></td>
                        <td><p>{{ user.joinDate | yyyyMMdd }}</p></td>
                        <td><b-button id="userDeleteBtn" @click="userDelete(user.userId)" >삭제</b-button></td>
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
        aria-controls="userTable"
        align="center"
        >
        </b-pagination>
        </div>
    </div>  
</template>

<script>
import http from "@/util/http-common.js";

export default {
    name: 'UserManage',

    data() {
        return {
            users: null,
            perPage: 10,
            currentPage: 1,
            paginatedItems: '',
            rowws: null,
            idSelected: null,
            emailSelected: null,
        
            // filter에 사용될 데이터
            searchOpt: [
                { value: "all", text: "전체"},
                { value: "id", text: "아이디"}, 
                { value: "email", text: "이메일"}
            ],

            searchSelected: "all",
            src: [],     // 초기 userlist를 저장할 배열
            word: "",
            str: null,
        }
    },
    methods: {
        paginate (page_size, page_number) {
            let itemsToParse = this.users
            this.paginatedItems = itemsToParse.slice(page_number * page_size, (page_number + 1) * page_size);
        },
        onPageChanged() {
            this.paginate(10, this.currentPage - 1)
        },

        getUsers() {
            http({
                method: 'get',
                url: '/admin/searchAll',
            })
            .then(res => {
                this.users = res.data
                this.src = res.data
                this.rowws = res.data.length
                this.items = res.data
                if(this.users) {
                    this.paginate(10, 0)
                }
            })
            .catch(err => {
                console.log(err);
            })
        }, 

        userDelete(deleteUserId) {
            if(confirm('정말 삭제하시겠습니까?') == true) {
                http({
                    method: 'get',
                    url: `/admin/remove/${deleteUserId}`,
                })
                .then(() => {
                    this.$router.go();
                })
                .catch(err => {
                    console.log(err);
                })
            } else {    // 삭제 취소
                return;
            }
        },

        searchPaging(){
            this.rowws = this.users.length;
            this.currentPage = 1;
        },

        searchInit(){
            this.idSelected = null;
            this.emailSelected = null;
            this.word = "";
            this.users = this.src;
            this.searchPaging();
        },

        searchAll() {
            this.users = this.src;
            this.searchPaging();
        },

        headerSel() {
            this.word = "";

            if(this.searchSelected == "") {
                this.searchAll();
            }
        },

        search() {
            if(this.searchSelected == "id") {     // 아이디로 찾기
                if(this.word == "") {   // 찾을 키워드를 입력하지 않은 경우
                    alert("찾을 아이디를 입력하세요.");
                } else {
                    this.users = this.src.filter((user) => {
                        if(user.userId.toLowerCase().includes(this.word.toLowerCase())){
                            return user
                        }
                    });
                    this.searchPaging();
                }
            } else if(this.searchSelected == 'email') {     // 이메일로 찾기
                if(this.word == "") {
                    alert("찾을 이메일을 입력해주세요.");
                } else {
                    this.users = this.src.filter((user) => {
                        if(user.userEmail.toLowerCase().includes(this.word.toLowerCase())) {
                            return user
                        }
                    });
                    this.searchPaging();
                }
            } else {
                this.searchAll();
            }
        }
    }, 
    created() {
        this.getUsers()
    },

    computed: {
        rows() {
            return this.rowws
        }
    },

    watch : {
        users : {
            handler() {
                this.paginate(10, 0)
            },
            deep : true
        },
        currentPage(neww) { 
            this.paginate(10, neww - 1)
        }
    },

    filters : {
        yyyyMMdd : function(value) {
            if(value == '') return;

            var js_date = new Date(value);
            var year = js_date.getFullYear();
            var month = js_date.getMonth() + 1;
            var day = js_date.getDate();

            if(month < 10) month = '0' + month;

            if(day < 10) {
                day = '0' + day;
            }

            return year + "-" + month + "-" + day;
        }
    }
}
</script>

<style scoped>
#userTable {
    font-family: "BMJua";
    font-size: 17px;
}

#userDeleteBtn {
    background-color: #dc3545;
    border: none;
}

#selectSearchOption {
    width: auto;
}

#inputSearchOption {
    width: 300px;
}

#userSearchBtn {
    background-color: #afa2dd;
    color: #000;
    border: none;
}

#resetSearchBtn {
    background-color: #dc3545;
    border: none;
}
</style>