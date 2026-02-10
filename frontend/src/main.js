import "./assets/style.css";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

// create Vue app
const app = createApp(App);

// use router
app.use(router);

// mount app to index.html
app.mount("#app");
