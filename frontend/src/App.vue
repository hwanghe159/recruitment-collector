<template>
  <v-app id="inspire">
    <app-bar />
    <v-main class="grey lighten-3">
      <recruitments />
    </v-main>
  </v-app>
</template>
<script>
import AppBar from "@/components/AppBar";
import Recruitments from "@/components/Recruitments";

export default {
  name: "App",

  components: { AppBar, Recruitments },
};
</script>
