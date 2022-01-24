<template>
  <center>
    <h1>스터디 방 생성</h1>
    <hr>
    <div style="width:600px;" class="p-5">
      <b-row>
        <b-col cols="3">
          <label for="studyName" class="mt-2">스터디 이름</label>
        </b-col>
        <b-col>
          <b-form-input id="studyName"></b-form-input>
        </b-col>
        <hr class="mt-3">
        <b-col cols="3">
          <label for="url" class="mt-2">스터디 url</label>
        </b-col>
        <b-col>
          <b-form-input id="url" v-model="url"></b-form-input>
        </b-col>
        <hr class="mt-3">
        <b-col cols="3">
          <label for="studyCategory" class="mt-2">스터디 분류</label>
        </b-col>
        <b-col>
          <b-form-input id="studyCategory"></b-form-input>
        </b-col>
        <hr class="mt-3">

        <b-col cols="3">
          <label for="studyImg" class="mt-2">대표 이미지</label>
        </b-col>
        <b-col>
          <div class="preview" v-if="input.image">
           <p>업로드된 이미지가 없습니다.</p>
          </div>
          <input accept=".jpg .jpeg .png" type="file" id="studyImg" class="inputfile" />
        </b-col>
        <hr class="mt-3">

        <b-col cols="3">
          <label for="studyType" class="mt-2">스터디 종류</label>
        </b-col>
        <b-col>
          <b-form-input id="studyType"></b-form-input>
        </b-col>
        <hr class="mt-3">
        <b-col cols="3">
          <label for="studyTotalMember" class="mt-2">인원 수</label>
        </b-col>
        <b-col cols="5">
          <b-form-input id="studyTotalMember" placeholder="인원 수를 입력하세요."></b-form-input>
        </b-col>
        <b-col cols="1" class="mt-2">명</b-col>
        <hr class="mt-3">
        <b-col cols="3">
          <label for="studyPassword" class="mt-2">비밀번호</label>
        </b-col>
        <b-col>
          <b-form-input id="studyPassword"></b-form-input>
        </b-col>
        <hr class="mt-3">
        
        <b-col cols="3">초기 장치 설정</b-col>
        <b-col>
          <input type="checkbox" id="mic" value="mic" v-model="settings.mic">
          <label for="mic" class="ms-1 me-5">마이크</label>
          <input type="checkbox" id="cam" value="cam" v-model="settings.cam">
          <label for="mic" class="ms-1 me-5">카메라</label>
          <input type="checkbox" id="speaker" value="speacker" v-model="settings.speaker">
          <label for="mic" class="ms-1 me-5">스피커</label>
        </b-col>
        <hr class="mt-3">

        <b-col cols="3">
          <label for="studyRule" class="mt-2">스터디 규칙</label>
        </b-col>
        <b-col>
          <b-form-textarea id="studyRule" v-model="study_rule" placeholder="스터디 규칙, 공지사항 등 입력" rows="3" max-rows="6"></b-form-textarea>
        </b-col>
        <button class="mt-4" type="button">스터디 생성</button>
      </b-row>
    </div>


  </center>

</template>

<script>
export default {
  name: 'StudyRoomCreateForm',
  data() {
    return {
      url: null,
      image: null,
      number_of_member: null,
      study_rule: null,
      settings: {
        mic: null,
        cam: null,
        speaker: null,
      }
    }
  },
}
// 이미지 업로드 자바스크립트
// var input = document.querySelector('input');
// var preview = document.querySelector('.preview');
// input.style.opacity = 0;
// input.addEventListener('change', updateImageDisplay);
// function updateImageDisplay() {
//   while(preview.firstChild) {
//     preview.removeChild(preview.firstChild);
//   }

//   const curFiles = input.files;
//   if(curFiles.length === 0) {
//     const para = document.createElement('p');
//     para.textContent = 'No files currently selected for upload';
//     preview.appendChild(para);
//   } else {
//     const list = document.createElement('ol');
//     preview.appendChild(list);

//     for(const file of curFiles) {
//       const listItem = document.createElement('li');
//       const para = document.createElement('p');
//       if(validFileType(file)) {
//         para.textContent = `File name ${file.name}, file size ${returnFileSize(file.size)}.`;
//         const image = document.createElement('img');
//         image.src = URL.createObjectURL(file);

//         listItem.appendChild(image);
//         listItem.appendChild(para);
//       } else {
//         para.textContent = `File name ${file.name}: Not a valid file type. Update your selection.`;
//         listItem.appendChild(para);
//       }

//       list.appendChild(listItem);
//     }
//   }
// }
// const fileTypes = [
//   "image/apng",
//   "image/bmp",
//   "image/gif",
//   "image/jpeg",
//   "image/pjpeg",
//   "image/png",
//   "image/svg+xml",
//   "image/tiff",
//   "image/webp",
//   "image/x-icon"
// ];

// function validFileType(file) {
//   return fileTypes.includes(file.type);
// }
// function returnFileSize(number) {
//   if(number < 1024) {
//     return number + 'bytes';
//   } else if(number >= 1024 && number < 1048576) {
//     return (number/1024).toFixed(1) + 'KB';
//   } else if(number >= 1048576) {
//     return (number/1048576).toFixed(1) + 'MB';
//   }
// }



</script>

<style>
button {
  border: none;
  border-radius: 8px; 
  background-color: #DAC7F9;
  height: 40px;
}
button:hover {
  background-color: #b488fc;
}

</style>