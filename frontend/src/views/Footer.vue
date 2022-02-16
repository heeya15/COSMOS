<template>
    <div class="px-5 pt-4" id="footerArea">
        <!-- <div class="text-center siteName mb-5">
            <p id="cosmos">코스모스</p>
            <p>코딩 스터디 모두 함께 스터디</p>
        </div>
        <div class="text-center">
            <a><img class="sitesImg mr-2" id="github" src="https://img.icons8.com/ios-glyphs/30/000000/github.png" alt="git" border="0"></a>
            <a href="https://www.instagram.com/hellossafy/channel/?hl=en"><img class="sitesImg mr-2" id="instagram" src="https://img.icons8.com/fluency/48/000000/instagram-new.png" alt="insta-removebg-preview" border="0"></a>
            <a href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp"><img class="sitesImg"  id="website" src="https://i.ibb.co/bB6Z6Wv/world-wide-web-removebg-preview.png" alt="world-wide-web-removebg-preview" border="0"></a>
        </div>
        <div class="pt-3 pb-3" align="center">
            <div id="copyrightDesc" class="pb-2">본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포 등을 금합니다.</div>
            <div id="copyright">Copyright &copy; 2022 by COSMOS</div>
        </div> -->
        <b-row class="siteMap pb-4">
            <b-col cols="1"></b-col>
            <b-col cols="4">
                <div class="logoArea d-flex">
                    <img id="logo" src="https://i.ibb.co/LJfmXpX/cosmos-logo3.png" alt="cosmos-logo3" border="0">
                    <p class="ml-2 mt-1" id="cosmos">COSMOS</p>
                </div>
                    <p class="mt-1" id="siteDesc">코스모스, 코딩 스터디 모두 함께 스터디</p>
                <div class="mt-1 d-flex justify-content-between">
                    <a class="menu" href="#banner">Home</a>
                    <p> | </p>
                    <a class="menu" href="#rank">Ranking</a>
                    <p> | </p>
                    <a class="menu" href="/signup">Sign Up</a>
                    <p> | </p>
                    <a class="menu" href="/login">Log In</a>
                </div>
                    <!-- <p id="light">비대면 화상 IT 스터디 플랫폼</p> -->
            </b-col>
            <!-- <b-col cols="1" class="mt-4">
                <ul>
                    <li><a class="menu" href="#banner">Home</a></li>
                    <li><a class="menu" href="#rank">Ranking</a></li>
                    <li><a class="menu" href="/signup">Sign Up</a></li>
                    <li><a class="menu" href="/login">Log In</a></li>
                </ul>
            </b-col>
            <b-col cols="1" class="mt-4">
                <ul>
                    <li><a class="menu" href="/signup">Sign Up</a></li>
                    <li><a class="menu" href="/login">Log In</a></li>
                </ul>
            </b-col> -->
            <b-col cols="4"></b-col>
            <b-col cols="2" class="pt-3">
                <a href="https://lab.ssafy.com/s06-webmobile1-sub2/S06P12E103"><img class="sitesImg mr-2" id="github" src="https://img.icons8.com/ios-glyphs/30/000000/github.png" alt="git" border="0"></a>
                <a href="https://www.instagram.com/hellossafy/channel/?hl=en"><img class="sitesImg mr-2" id="instagram" src="https://img.icons8.com/fluency/48/000000/instagram-new.png" alt="insta-removebg-preview" border="0"></a>
                <a href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp"><img class="sitesImg"  id="website" src="https://i.ibb.co/bB6Z6Wv/world-wide-web-removebg-preview.png" alt="world-wide-web-removebg-preview" border="0"></a>
            </b-col>
            <b-col cols="1"></b-col>
        </b-row>
        <div class="pt-3 pb-3" align="center">
            <div id="copyrightDesc" class="pb-2">본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포 등을 금합니다.</div>
            <div id="copyright">Copyright &copy; 2022 by COSMOS</div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'Footer',
}
</script>

<style scoped>
p {
    margin: 0;
}

#footerArea{
    /* background-color: rgb(36, 5, 65); */
    background-color: #393939;
    /* font-family: 'IM_Hyemin-Bold'; */
}

#logo {
    width: 50px;
    height: 50px;
}

#cosmos {
    font-size: 20pt;
    color: #afa2dd;
}

#siteDesc {
    margin: 0;
}

.siteMap {
    color: #fff;
    font-size: 13pt;
}

#copyrightDesc {
    font-size: 10px;
    color: #ccc;
}

#copyright {
    /* color: rgb(133, 133, 133); */
    font-size: 10px;
    color: #fff;
}

.menu {
    margin: 0;
    margin-bottom: 10px;
}

ul {
    list-style-type: none;
}

ul li {
    padding: 0;
}

.sitesImg {
    cursor: pointer;
}

#github {
    width: 50px;
    height: 50px;
    border-radius: 50px;
}

#instagram {
    width: 50px;
    height: 50px;
}

#website {
    width: 40px;
    height: 40px;
}

.menu {
    color: #fff;
}

</style>