<template>
  <v-container>
    <v-row>
      <v-col sm="2">
        <v-sheet rounded="lg">
          <v-card-text>
            <v-chip-group v-model="selected" column multiple>
              <v-chip
                v-for="(company, idx) in companies"
                :key="idx"
                filter
                outlined
              >
                {{ company }}
              </v-chip>
            </v-chip-group>
          </v-card-text>
        </v-sheet>
        <v-btn
          depressed
          rounded
          @click="uncheckAll"
          v-if="isCheckedAtLeastOne"
          class="mt-1"
          >전체해제</v-btn
        >
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
    companies: ["네이버", "NHN"],
    selected: [],
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
    uncheckAll() {
      this.selected = [];
    },
  },
  computed: {
    isCheckedAtLeastOne() {
      return this.selected.length !== 0;
    },
  },
};
</script>
