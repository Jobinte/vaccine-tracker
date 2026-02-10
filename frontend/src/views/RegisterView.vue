<template>
  <div class="auth-container">
    <div class="auth-card">
      <h1>Register 📝</h1>

      <input v-model="email" placeholder="Gmail address" />
      <input v-model="password" type="password" placeholder="Password" />

      <button @click="register">Register</button>

      <p>Already have account? <router-link to="/login">Login</router-link></p>
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
    async register() {
      try {
        const res = await api.post("/auth/register", {
          email: this.email,      // ⭐ MUST be email
          password: this.password // ⭐ MUST be password
        });

        alert(res.data);
        this.$router.push("/login");
      } catch (err) {
        alert("Register failed: " + err.response.data.message);
      }
    },
  },
};
</script>

<style>
.auth-container {
  display:flex;
  justify-content:center;
  align-items:center;
  height:100vh;
  background:#f4f6fb;
}
.auth-card {
  width:320px;
  padding:40px;
  background:white;
  border-radius:12px;
  box-shadow:0 10px 25px rgba(0,0,0,0.1);
  text-align:center;
}
input{
  width:100%;
  padding:10px;
  margin:10px 0;
}
button{
  background:#5b5df1;
  color:white;
  padding:10px;
  width:100%;
  border:none;
  border-radius:6px;
}
</style>
