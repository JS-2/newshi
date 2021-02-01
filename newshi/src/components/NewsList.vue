<template>
  <v-container>
    <v-toolbar
        flat
        dense
    >
      <v-tabs
        v-model="tab"
        center-active
        show-arrows
        align-with-title
      >
        <v-tabs-slider color="grey lighten-4"></v-tabs-slider>
          <v-tab
            v-for="(newsItem, index) in newsItems"
            :key="index"
            style="height: 48px; width: 48px;"
          >
            <v-list-item-avatar>
              <v-img :src="newsItem.avatar"></v-img>
            </v-list-item-avatar>
          </v-tab>
      </v-tabs>
    </v-toolbar>

    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="(newsItem, idx) in newsItems"
        :key="newsItem+idx"
      >
      {{ newsItem.name }} 
      <hr>
        <v-list>
          <template v-for="(newsInfo, i) in newsItem.newsLists">
            <v-list-item
              :key="newsInfo+i"
              @click="move(newsInfo.newsNo)"
            >
                <v-list-item-avatar
                  tile
                >
                  <v-img :src="newsInfo.thumbnail"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title v-html="newsInfo.title"></v-list-item-title>
                    <v-list-item-title v-html="newsInfo.subtitle"></v-list-item-title>
                </v-list-item-content>
            </v-list-item>
          </template>
        </v-list>
      </v-tab-item>
    </v-tabs-items>
  </v-container>
</template>

<script>
export default {
  props: [
    'newsInfos'
  ],
  methods: {
    move(newsNo){
      // this.$router.push(`/article/${newsNo}`)
      this.$router.push({name: 'Article', params: {newsNo: newsNo} })
    },
  },
  data: function () {
    return {
        tab: null,
        newsItems: this.newsInfos,
      }
  }
}
</script>

<style>
.container {
  max-width: 800px;
}
</style>