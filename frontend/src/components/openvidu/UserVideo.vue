<template>
<div v-if="streamManager">
	<!-- <div class="userVideo_info col-md-3"> -->
	<div class="userVideo_info">
		<div class="userVideo">
			<ov-video :stream-manager="streamManager"/>
		</div>
		<div><p class="video_clientName">{{ clientData }}</p></div>
		<div><p class="video_stopwatch">00:00:00</p></div>
		
		<div class="selectDisplay d-flex justify-content-center">
			<p class="d-flex justify-content-center" style="width:50%">캠 보기</p>
			<p class="d-flex justify-content-center" style="width:50%">화면 보기</p>
		</div>
	</div>
</div>
</template>
<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	data() {
		return {
			count: 0,
			intervalId: null
		};
	},

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
		
	},

	
};
</script>
<style>
@media (max-width: 1500px){
	.userVideo{
		width: 230px !important;
	}
}
@media (max-width: 1400px){
	.userVideo{
		width: 210px !important;
	}
}

@media (max-width: 1300px){
	.userVideo{
		width: 190px !important;
		
	}
	#video-container{
		/* display: flex !important;
		justify-content: center !important; */
	}
}
@media (max-width: 1200px){
	.userVideo{
		width: 290px !important;
		
	}
	#video-container{
		/* display: flex !important;
		justify-content: center !important; */
	}
}
@media (max-width: 1150px){
	.userVideo{
		width: 270px !important;
		
	}
	#video-container{
		/* display: flex !important;
		justify-content: center !important; */
	}
}

.userVideo_info{
	position: relative;
	/* width: 30%; */
	/* height: 65%; */
	
	/* background-color: black; */
	/* padding: 0; */
	margin: 0 30px 70px;
}

.userVideo{
	width: 250px;
	/* height: 180px; */
}

/* .user_autoplay_video{
	position: absolute;
	top: 0;
	left: 0;
} */

.video_stopwatch{
position: absolute;
top: 0px;
right: 0px;
display: inline-block;
background: #111;
padding-left: 5px;
padding-right: 5px;
color: #fff;
font-weight: bold;
border-bottom-right-radius: 4px;
}

.video_clientName{
position: absolute;
top: 0px;
left: 0px;
display: inline-block;
background: #f8f8f8;
padding-left: 5px;
padding-right: 5px;
color: #777777;
font-weight: bold;
border-bottom-right-radius: 4px;
}

.selectDisplay{
width: 100%;
/* margin-top: 5px; */
position: absolute;
left: 0;
bottom: -10%;
}

.selectDisplay p{
	margin: 0 5px;
}

</style>