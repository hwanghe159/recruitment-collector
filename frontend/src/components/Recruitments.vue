<template>
  <v-container>
    <v-row>
      <v-col sm="2">
        <v-sheet rounded="lg">
          <v-list color="transparent">
            <v-list-item-group v-model="selectedCompany" color="dark">
              <v-list-item link>
                <v-list-item-content>
                  <v-list-item-title> 전체보기 </v-list-item-title>
                </v-list-item-content>
              </v-list-item>

              <v-divider class="my-2"></v-divider>
              <v-list-item v-for="(company, idx) in companies" :key="idx" link>
                <v-list-item-content>
                  <v-list-item-title> {{ company }} </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-sheet>
      </v-col>
      <v-col sm="10">
        <v-sheet min-height="70vh" rounded="lg">
          <v-list-item
            v-for="(recruitment, idx) in recruitments"
            :key="idx"
            link
            @click="goTo(recruitment.url)"
          >
            <v-list-item-content>
              <v-list-item-title>
                {{ recruitment.title }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "Recruitments",

  components: {},

  data: () => ({
    selectedCompany: 1,
    companies: ["네이버", "NHN"],
    recruitments: [
      {
        title:
          "네이버 예약 플랫폼 프론트엔드/백엔드 개발(채용연계형 인턴십 2020.12)",
        url:
          "https://recruit.navercorp.com/naver/job/detail/developer?annoId=20004943&classId=&jobId=&entTypeCd=&searchTxt=&searchSysComCd=",
      },
    ],
  }),
  methods: {
    goTo(url) {
      window.open(url, "_blank");
    },
  },
};
</script>
