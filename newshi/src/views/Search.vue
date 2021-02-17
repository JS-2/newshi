<template>
  <div>
      <v-container>
          <v-row>
              <v-col>
                  <v-autocomplete solo @change="(event) => search(event)" 
                      :search-input.sync="search_word"
                      label="#태그, 큐레이터를 입력해주세요." 
                      :items="autocomp_value">
                  </v-autocomplete>
              </v-col>
          </v-row>
          <v-row>
              <v-col>
                  <v-card>
                  <router-view :key="$route.fullPath"></router-view>
                  </v-card>
              </v-col>
          </v-row>
      </v-container>
  </div>
</template>

<script>
import axios from 'axios';
import { API_BASE_URL } from '@/config';

export default {
    data() {
        return {
            search_word: '',
            autocomp_value: [],
            curator_search_flag: false,
        }
    }, 
    methods: {
        search(event) {
            if(event.charAt(0) == '#') {
                this.$router.push('/search/hashtag/' + event.substring(1));
            } else this.$router.push('/search/curator/' + event);
        }
    },
    watch: {
    search_word: function() {
        if(this.search_word == undefined) return;
        else if (this.search_word == '') {
            this.autocomp_value = [];
            this.curator_search_flag = false;
        }
        else if (this.search_word.charAt(0) == '#') {
            console.log('해시태그들 axios')
            this.autocomp_value = [
        '#속보',
        '#정치',
        '#경제',
        '#사회',
        '#생활/문화',
        '#세계/국제',
        '#IT/과학',
        '#오피니언',
      ];
        } else{
            // 큐레이터에 접근하는 axios
            axios.get(API_BASE_URL + 'search/people', 
            { params: { keyword: this.search_word } },
            ).then((response) => { 
                let resData = response.data;
                console.log(resData);
                let arr = [];
                for(let i = 0; i < resData.length; i++) {
                    if(resData[i]['name'] != undefined) {
                        arr.push(resData[i]['name']);
                    }
                }
                console.log(arr);
                this.autocomp_value = arr;
            })
            
        }
        },
    },
}
</script>

<style>

</style>