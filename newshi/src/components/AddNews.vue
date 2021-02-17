<template>
  <v-dialog v-model="addDialog" fullscreen v-if="isLogged">  
    <v-overlay :value="overlay"> 
      <v-progress-circular indeterminate size="60">
      </v-progress-circular></v-overlay>
    <template v-slot:activator="{ on, attrs }">
      <v-btn color="#fcbf49" fixed bottom right fab v-bind="attrs" v-on="on">
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
    </template>
    <v-card max-height="300px">
      <v-toolbar color="orange lighten-4">
        <v-btn icon @click="addDialog = !addDialog">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-spacer />
        <v-toolbar-title>기사 등록</v-toolbar-title>
        <v-spacer />
      </v-toolbar>

      <v-form class="pa-6" @submit.prevent="registURL">
        <ValidationProvider name="url" rules="url" v-slot="{ errors }">
          <v-text-field
            v-model="url"
            :error-messages="errors"
            required
            placeholder="URL을 등록해주세요"
            autocapitalize="off"
          ></v-text-field>
        </ValidationProvider>
        <v-btn color="orange lighten-4" @click="registURL">
          저장
        </v-btn>
      </v-form>
      <NewsForm
        v-for="(news, index) in post"
        :key="index"
        :num="index"
        :news="news"
        @remove="removeURL"
        @save="saveOther"
      ></NewsForm>
      <v-form class="pa-6" @submit.prevent="registPost">
        <v-text-field
          v-model="postName"
          required
          placeholder="포스트 제목을 작성해주세요"
        ></v-text-field>
        <v-btn color="orange lighten-4" @click="registPost"
          >등록<v-icon right>mdi-cloud-upload</v-icon>
        </v-btn>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>
import { ValidationProvider } from 'vee-validate';
import { extend } from 'vee-validate';
import * as rules from 'vee-validate/dist/rules';
import NewsForm from '@/components/NewsForm.vue';
import { saveArticle } from '@/api/article.js';
Object.keys(rules).forEach((rule) => {
  extend(rule, rules[rule]);
});
extend('url', {
  message: 'URL 형식이 아닙니다. 다시 한번 확인해주세요.',
  validate: (value) => {
    return /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!]))?/i.test(
      value
    );
  },
});
export default {
  components: {
    ValidationProvider,
    NewsForm,
  },
  data() {
    return {
      addDialog: null,
      url: '',
      post: [],
      postName: '',
      overlay: false,
    };
  },
  methods: {
    registURL() {
      if (this.url === '') return;
      let news = {
        url: this.url,
        summary: '',
        tags: [],
      };
      this.post.push(news);
      this.url = '';
    },
    registPost() {
      if (this.postName === '' || this.postName == null) {
        alert('포스트 제목을 작성해주세요');
        return;
      }
      for (let i = 0; i < this.post.length; i++) {
        let news = {
          url: this.post[i].url,
          summary: this.post[i].summary,
          tags: this.post[i].tags,
          id: localStorage['id'],
          name: this.postName,
        };
        this.post.splice(i, 1, news);
      }
      console.log(this.post);
      this.overlay = true;
      saveArticle(
        this.post,
        (response) => {
          if (response.data.message === 'success') {
            this.overlay = false;
            alert('포스트 생성에 성공하셨습니다.');
            let no = response.data.postNo;
            this.addDialog = false;
            this.$router.push({
              name: 'Link',
              params: {
                postNo: no,
                postName: this.postName,
                tags: this.post[0].tags,
              },
            });
          } else {
            alert('포스트 생성에 실패하셨습니다.');
            this.overlay = false;
          }
        },
        (error) => {
          console.error(error);
          alert('포스트 생성 중 에러가 발생했습니다.');
          this.overlay = false;
        }
      );
    },
    removeURL(index) {
      this.post.splice(index, 1);
      console.log(this.post);
      console.log(index);
    },
    saveOther(summary, tags, index) {
      let news = {
        url: this.post[index].url,
        summary: summary,
        tags: tags,
      };
      this.post.splice(index, 1, news);
      console.log(this.post[index]);
    },
  },
  computed: {
    isLogged() {
      if (
        localStorage['access-token'] === undefined ||
        localStorage['access-token'] === null ||
        localStorage['access-token'] === ''
      )
        return false;
      else return true;
    },
  },
};
</script>

<style></style>
