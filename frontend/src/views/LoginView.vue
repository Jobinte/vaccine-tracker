<script setup>
import { ref } from "vue";
import API from "../services/api";
import { useRouter } from "vue-router";

const email = ref("");
const password = ref("");
const router = useRouter();

const login = async () => {
  try {
    const response = await API.post("/auth/login", {
      email: email.value,
      password: password.value,
    });

    // Save JWT token in browser
    localStorage.setItem("token", response.data);

    alert("Login successful 🎉");
    router.push("/dashboard");

  } catch (error) {
    alert("Login failed ❌");
  }
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
