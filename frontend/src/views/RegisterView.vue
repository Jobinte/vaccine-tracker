<template>
  <div class="container">
    <h1>Vaccine Tracker - Register</h1>

    <form @submit.prevent="register">
      <input v-model="email" type="email" placeholder="Email" required />
      <input v-model="password" type="password" placeholder="Password" required />
      <button type="submit">Register</button>
    </form>

    <p>{{ message }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const email = ref('')
const password = ref('')
const message = ref('')

const register = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/auth/register', {
      email: email.value,
      password: password.value
    })
    message.value = response.data
  } catch (error) {
    message.value = "Error registering user"
  }
}
</script>

<style>
.container {
  max-width: 400px;
  margin: 100px auto;
  display: flex;
  flex-direction: column;
  gap: 10px;
}
input, button {
  padding: 10px;
  font-size: 16px;
}
</style>
