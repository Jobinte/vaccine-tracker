<template>
  <div class="auth-container">
    <div class="auth-card">
      <h1>Login 🔐</h1>

      <input v-model="email" placeholder="Email" />
      <input v-model="password" type="password" placeholder="Password" />

      <button @click="login">Login</button>
    </div>
  </div>
</template>

<script>
import api from "../services/api";

export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    async login() {
      try {
        const res = await api.post("/auth/login", {
          email: this.email,
          password: this.password,
        });

        localStorage.setItem("token", res.data);
        this.$router.push("/dashboard");
      } catch (err) {
        alert("Login failed");
      }
    },
  },
};
</script>
