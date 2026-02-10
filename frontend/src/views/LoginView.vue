<script>
import api from "../services/api";

export default {
  data() {
    return {
      email: "",
      password: "",
      error: "",
    };
  },

  methods: {
    async login() {
      try {
        const res = await api.post("/auth/login", {
          email: this.email,
          password: this.password,
        });

        // ⭐ SAVE TOKEN
        localStorage.setItem("token", res.data);

        // ⭐ GO TO DASHBOARD
        this.$router.push("/dashboard");
      } catch (err) {
        this.error = "Invalid email or password";
      }
    },
  },
};
</script>


<template>
  <div class="container">
    <h1>Login</h1>

    <input v-model="email" placeholder="Email" />
    <input v-model="password" type="password" placeholder="Password" />

    <button @click="login">Login</button>
  </div>
</template>
