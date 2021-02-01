<template>
  <div>
    <ArticleDetail 
      :news-info="newsInfo"
    />
  </div>
</template>

<script>
import ArticleDetail from '../components/ArticleDetail'

export default {
  name: "Article",
  components: { 
    ArticleDetail,
  },
  data: function () {
    return {
      newsInfo: {
          newsNo: 1,
          thumbnail: 'https://imgnews.pstatic.net/image/origin/003/2021/01/27/10315482.jpg?type=nf132_90',
          title: '기아, 지난해 영업이익 2조665억…전년比 2.8%↑(1보)',
          subtitle: '뉴시스',
          content: "[서울=뉴시스] 박주연 기자 = 기아는 지난해 연결 재무제표 기준 영업이익이 전년에 비해 2.8% 증가한 2조665억원으로 잠정 집계됐다고 27일 공시했다. 지난해 매출은 1.8% 증가한 59조1681억원, 순이익은 2.5% 증가한 1조5027억원을 각각 나타냈다. 영업이익률은 전년과 동일한 3.5%였다. 고수익 레저차량(RV) 차종 및 신차 판매 확대에 따른 믹스 개선, 친환경차 판매 확대로 매출이 증가했다. 3분기 품질 비용 발생에도 불구하고, 판매 믹스 개선, 평균 판매 가격 상승, 재고 안정화에 따른 인센티브 축소 등 전반적인 수익성 체질 개선으로 영업이익 역시 증가세를 나타냈다. 지난해 도매 기준 연간판매는 전년 대비 7.6% 감소한 260만6832대를 기록했다. 국내에서 전년 대비 6.2% 증가한 55만 2400대, 해외에서 10.7% 감소한 205만 4432대였다.", 
          news_image_caption: 'newsis제공',
          image_path: "https://imgnews.pstatic.net/image/003/2021/01/27/NISI20210106_0000669186_web_20210106155806_20210127140159904.jpg?type=w647",
          url: "https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=101&oid=003&aid=0010315482",
          article_date: '2021/01/27',
          article_last_date: '2021/01/27',
          article_bot_summary: "기아는 지난해 연결 재무제표 기준 영업이익이 전년에 비해 2.8% 증가한 2조665억원으로 잠정 집계됐다고 27일 공시했다. 지난해 도매 기준 연간판매는 전년 대비 7.6% 감소한 260만6832대를 기록했다. 국내에서 전년 대비 6.2% 증가한 55만 2400대, 해외에서 10.7% 감소한 205만 4432대였다.",
          show: false,
      },
      newsNo: 0,
    }
  },
  created: function() {
    this.newsNo = this.$route.params.newsNo;
    console.log(this.newsNo);
  }
};
</script>

<style>
.container {
  max-width: 800px;
}
.news-header {
  font-size: 12px;
  color: #999;
}
.news-title {
  margin-top: 30px;
  font-size: 35px;
  font-weight: 900;
}
.news-info {
  margin-top: 30px;
}
.news-info > div {
  margin-right: .5rem;
  font-size: 15px;
}
.news-image-caption {
  color: #999;
  font-size: 12px;
  margin-bottom: 20px;
}
</style>