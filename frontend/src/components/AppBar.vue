<template>
  <v-app-bar app color="white" flat>
    <v-container class="py-0 fill-height">
      <v-spacer></v-spacer>
      <v-responsive max-width="260">
        <v-text-field
          dense
          flat
          hide-details
          rounded
          solo-inverted
        ></v-text-field>
      </v-responsive>
    </v-container>
    <v-avatar
      class="mr-10 d-none d-sm-block"
      color="grey darken-1"
      size="32"
    ></v-avatar>
  </v-app-bar>
</template>
<script>
export default {
  name: "AppBar",

  components: {},

  data: () => ({}),
  methods: {},
};
</script>
