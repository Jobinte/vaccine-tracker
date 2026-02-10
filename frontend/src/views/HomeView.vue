<template>
  <div class="home">
    <div class="card">
      <h1>💉 Vaccine Tracker</h1>
      <p>Manage your vaccination records smartly</p>

      <div class="buttons">
        <router-link to="/login">
          <button class="login">Login</button>
        </router-link>

        <router-link to="/register">
          <button class="register">Register</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style>
.home {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(120deg,#667eea,#764ba2);
  font-family: Arial;
}

.card {
  background: white;
  padding: 60px;
  border-radius: 15px;
  text-align: center;
  box-shadow: 0 10px 30px rgba(0,0,0,0.2);
}

h1 { font-size: 40px; margin-bottom: 10px; }
p { color: gray; margin-bottom: 30px; }

.buttons button {
  padding: 12px 25px;
  margin: 10px;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
}

.login { background:#4CAF50; color:white; }
.register { background:#2196F3; color:white; }
</style>
