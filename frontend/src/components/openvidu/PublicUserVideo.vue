<template>
<div v-if="streamManager">
	<!-- <div class="userVideo_info col-md-3"> -->
	<div class="userVideo_info">
		<div class="userVideo">
			<ov-video :stream-manager="streamManager"/>
		</div>
		<div><p class="video_clientName">{{ clientData }}</p></div>
		<div class="video_stopwatch d-flex">
				<div class="timer_icon" v-if="this.streamManager.stream.connection.role=='PUBLISHER'" >
					<b-icon v-if="!play" icon="play-fill" @click="startTimer()"></b-icon>
					<b-icon v-else icon="pause-fill" @click="stopTimer()"></b-icon>
				</div>
				<p class="mb-1 ml-1">{{ hours }} : {{ minutes }} : {{ seconds }}</p>
		</div>
	</div>
</div>
</template>
<script>
import "@/assets/style/PrivateStudyRoom/video.css"
import OvVideo from './OvVideo';
import http from "@/util/http-common.js";
export default {
	name: 'UserVideo',

	data() {
		return {
			count: 0,
			intervalId: null,

			// 타이머
			timer: null,
			time: 0,
			play: false,
			start_time:null
		};
	},

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
		session: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},

		hours(){
			const hours = Math.floor(this.time / 3600)
			return this.padTime(hours)
		},
		minutes() {
			// const minutes = Math.floor(this.time / 60)
			const minutes = Math.floor((this.time - (this.hours * 3600)) / 60)
			return this.padTime(minutes)
		},
		seconds() {
			const seconds = this.time - ((this.hours * 3600) + (this.minutes * 60))
			return this.padTime(seconds)
		},
	},
	created(){
		// 타이머 receive
		this.session.on('signal:study-timer', (event) => {	
			var receiveTime = event.data.split("&$");
			if(this.clientData == receiveTime[0]){ // 이 세션의 유저ID와 signal 보낸 유저ID가 같으면
				this.time = Number(receiveTime[1]); // 받은 signal 시간으로 설정
			}
		})
		
	},
	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},

		startTimer() {
			this.start_time = this.hours+":" +this.minutes +":"+this.seconds;
			console.log("타이머 눌러짐?");
			console.log(this.start_time);
			// 방에 들어온 시간 측정을 위해 rest 요청. // params로 보내주면됨
			http({
				method: 'POST',
				url: `/history/register/public/starttime`,
				headers: this.getUserToken(),
				params:{start_time:this.start_time},
			})
			.then( res => {
				console.log(res);
			})
			.catch(err => {
				console.log(err)
			});		

			//1000ms = 1 second
			this.timer = setInterval(() => this.countup(), 1000)
			this.play = true;
		},
		stopTimer: function() {
			clearInterval(this.timer)
			this.timer = null
			this.play = false;
		},
		padTime: function(time){
			return (time < 10 ? '0' : '') + time
		},
		countup: function() {
			this.time++
		},
		
		sendTimer(){
			// 타이머 send
			this.session.signal({
				data: this.clientData + "&$" + this.time, // 보내는 유저ID, 시간 데이터
				// data: this.time,
				to: [],
				type: 'study-timer',
			})
			.then(() => {
				console.log("timer send");
			})
			.catch(error => {
				console.error(error);
			})
		},
	},
	watch: {
		time(){
			this.sendTimer(); // 시간 변경될 때마다 signal send
		},
	},
	
};
</script>
