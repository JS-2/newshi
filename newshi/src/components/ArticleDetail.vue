<template>
  <v-container>
    <!-- news header -->      
    <header>
      <div class="news-title text-center">
        {{ news.title }}
      </div>
      <div class="news-header text-center">
        {{ news.article_date }}
      </div>
      <div class="news-info d-flex justify-content-start">
        <div>신문사</div>
        <div>기자명</div>
        <div>
          <a :href="news.url">원본보기</a>
        </div>
        <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon medium>mdi-bookmark</v-icon>
        </v-btn>
      </div>
    </header>
    <!-- main image -->
    <template>
      <div
        v-touch="{
          left: () => swipe('Left'),
          right: () => swipe('Right'),
        }"
      >
        <v-subheader>Swipe Direction: {{ swipeDirection }}</v-subheader>
    <section>
      <div class="news-image">
        <v-img
          :src="news.image_path"
          max-width:="700"
          max-height="480"
        >
        </v-img>
        <div class="news-image-caption text-center">
          {{ news.news_image_caption }}
        </div>
      </div>
    </section> 
    <!-- news body -->
    <section class="my-2" v-if="swipeDirection === 'Right'">
      <div class="news-body-text">
        <div class="article-bot-summary">
          <p>핵심 요약</p>
          <p>{{ news.article_bot_summary }}</p>
        </div>
        <div>
          <span class="my-auto">본문 보기</span>
            <v-btn
              icon
              @click="news.show = !news.show"
            >
              <v-icon>{{ news.show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
            </v-btn>
        </div>
        <v-expand-transition>
          <div v-show="news.show">
            <div class="article-content">
              <p>{{ news.content }}</p>
            </div>
          </div>
        </v-expand-transition>
      </div>
    </section>
    </div>
    </template>
  </v-container>
</template>

<script>
export default {
  name: "ArticleDetail",
  props: [
    'newsInfo',
  ],
  methods: {
      swipe (direction) {
        this.swipeDirection = direction
      },
    },
  data: function () {
    return {
      news: this.newsInfo,
      swipeDirection: 'Right',
    }
  }
}
</script>

<style>

</style>