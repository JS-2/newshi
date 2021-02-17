<template>
  <v-container>
    <v-row class="link-btn">
      <v-col style="padding: 0px">
        <v-btn class="top-btn pr-2" text @click="copyUrl">
          <v-icon color="deep-purple accent-4">
            mdi-content-copy
          </v-icon>
          URL 복사
        </v-btn>
        <v-btn class="top-btn pl-2" text @click="kakaoCopy">
          <v-img
            max-height="24"
            max-width="24"
            src="@/assets/images/kakaolink_btn_small.png"
          ></v-img>
          카카오톡 공유
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col style="padding-top: 0px; padding-bottom: 0px">
        <v-textarea
          class="url-text"
          auto-grow
          rows="1"
          outlined
          disabled
          :value="url"
        >
        </v-textarea>
      </v-col>
    </v-row>
    <v-divider></v-divider>
    <v-row style="margin-top : 5px">
      <v-col>
        <v-btn style="margin-left: 13px"
          ><a :href="url" style="color: black; text-decoration: none"
            >해당 포스트로 이동</a
          ></v-btn
        >
      </v-col>
      <v-divider vertical></v-divider>
      <v-col>
        <v-btn style="margin-left: 13px" @click="goMain"
          >메인페이지로 이동</v-btn
        >
      </v-col>
    </v-row>
    <v-divider style="margin-top: 15px"></v-divider>
  </v-container>
</template>

<script>
export default {
  props: {
    postNo: String,
    postName: String,
    tags: String,
  },
  data() {
    return {
      url: '',
      id: '',
    };
  },
  methods: {
    copyUrl() {
      this.$copyText(this.url).then(
        function(e) {
          alert('복사 되었습니다.');
          console.log(e);
        },
        function(e) {
          alert('복사에 실패했습니다.');
          console.log(e);
        }
      );
    },
    kakaoCopy() {
      // let desc = prompt('설명을 작성해주세요.', `${this.id} 포스트입니다.`);
      // console.log(desc);
      window.Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
          title: this.postName,
          description: this.tags,
          imageUrl: 'https://newha.s3.us-east-2.amazonaws.com/logo.jpg',
          link: {
            webUrl: this.url,
            mobileWebUrl: this.url,
          },
        },
        buttons: [
          {
            title: '해당 포스트로 이동',
            link: {
              webUrl: this.url,
            },
          },
        ],
      });
    },
    goMain() {
      this.$router.push('/');
    },
  },
  created() {
    this.id = localStorage['id'];
    // this.url = API_BASE_URL + `${this.id}/${this.postNo}`;
    this.url = `http://i4a307.p.ssafy.io/${this.id}/${this.postNo}`;
  },
};
</script>

<style>
.link-btn {
  margin-top: 20%;
}
.top-btn {
  padding-right: 0;
}
</style>
