<template>
  <v-container>
    <div v-if="this.isLoggedIn == true">
      <v-sheet
        class="mx-auto"
        max-width="800"
      >
        <v-slide-group
          v-model="model"
          center-active
        >
          <v-slide-item
            v-for="(user, idx) in users"
            :key="idx"
          >
            <v-card 
              class="d-flex flex-row ma-2"
              max-width="200"
              min-width="140"
              dismissable
            >
              <v-list-item-content 
                class="justify-center"
              >
                <div class="mx-auto text-center">
                  <v-avatar
                    color="brown"
                    class="elevation-5"
                  >
                    <v-img v-if="user.thumnail_path !== undefined" :src="user.thumnail_path[0]"></v-img>
                  </v-avatar>
                  <h3 v-if="user.name !== undefined">{{ user.name[0] }}</h3>
                  <span class="caption mt-1" 
                    v-for="tag in user.tag"
                    :key="tag"
                  >
                    #{{ tag }}
                  </span>
                  <br>
                  <v-btn
                    class="mt-1"
                    rounded
                    color="#fcbf49"
                    dark
                    v-if="user.id !== undefined"
                    @click="follow(user.id[0])"
                    elevation="3"
                  >
                    구독
                  </v-btn>
                </div>
              </v-list-item-content>
            </v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
      <NewsList :news-infos="newsInfos" />
    </div>
    <div v-else>
      <v-list>
        <template v-for="(newsInfo, i) in basicNews">
          <v-list-item
            :key="newsInfo+i"
            @click="move(newsInfo)"
          >
            <v-list-item-avatar rounded >
              <v-img :src="newsInfo.image_path" centered></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title v-html="newsInfo.title"></v-list-item-title>
              <v-list-item-subtitle v-html="newsInfo.company"></v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </template>
      </v-list>
    </div>
  </v-container>
</template>

<script>
import NewsList from '../components/NewsList.vue';
import axios from 'axios';
import { API_BASE_URL } from '@/config';
const API_URL = API_BASE_URL;
const id = localStorage.getItem('id');

export default {
  name: "Main",
  components: { 
    NewsList,
  },
  methods: {
    getData: function () {
      axios.get(`${API_URL}`+'subscribe'+`?id=${id}`)
      .then((res)=>{
        let subscribes = res.data;
        subscribes.forEach(element => {
          let subscriberId = element.id;
          let newsInfo = {
            name: element.name,
            avatar: element.thumbnail_path,
            newsLists: [],
          }
          axios.get(`${API_URL}`+'article/curatorscrap'+`?id=${subscriberId}`)
          .then((res)=> {
            newsInfo.newsLists = res.data;
            this.newsInfos.push(newsInfo)
          })
          .catch((err) => {
            console.log(err)
          })
        });
      })
      .catch((err) => {
        console.log(err)
      })
    },
    isLogged: function () {
      if (
        localStorage.getItem('access-token') === null ||
        localStorage.getItem('access-token') === '' ||
        localStorage['access-token'] === undefined
      ) {
        this.isLoggedIn = false;
        axios.get(`${API_URL}`+'article')
        .then((res)=> {
          this.basicNews = res.data;
        })
        .catch((err) => {
          console.log(err)
        })
      } else {
        this.isLoggedIn = true;
      }
    },
    move(newsInfo){
      this.$router.push({name: 'Article', params: {newsNo: newsInfo.newsNo, newsInfo2: newsInfo} })
    },
    getRecommend(){
      axios.get(`${API_URL}`+'/userrecommend'+`?id=${id}`)
      .then((res)=>{
        this.users = res.data;
      })
      .catch((err)=>{
        console.log(err)
      })
    },
    follow(subscribeid) {
      var frm = new FormData();
      frm.append("id", id);
      frm.append("id2", subscribeid);
      console.log(id, subscribeid);
      axios.post(`${API_URL}`+'subsc', frm, { headers: { 'Content-Type': 'multipart/form-data' }})
      .then(() => {
        this.$router.go(this.$router.currentRoute);
        console.log('구독 성공')
      })
    },
  },
  data: function () {
    return {
      tab: null,
      newsInfos: [],
      isLoggedIn: '',
      basicNews: [],
      users: [],
      model: null,
    }
  },
  created: function () {
    this.isLogged();
    this.isLoggedIn ? this.getData(): this.isLoggedIn;
    this.getRecommend();
  }
}
</script>

<style>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}

</style>