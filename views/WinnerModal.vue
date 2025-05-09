<template>
  <div class="body">
    <div class="modal">
      <slot></slot>
      <p :class="{ black: win === 'black', white: win === 'white' }"></p>
      <button class="btn" @click.self="close">확인</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    win: String,
  },

  methods: {
    close() {
      console.log('xcv');
      this.$emit('close');
    },
  },
};
</script>

<style scoped>
.winnerStone {
  width: 20px;
  height: 20px;
  border-radius: 50%;
}

.black {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: black;
}

.white {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: white;
}

.modal {
  width: 500px;
  height: 500px;
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  z-index: 999;
  align-items: center;
  pointer-events: auto;
}

.btn {
  border-radius: 30px;
  margin-top: 15px;
  font-size: 15x;
  width: 80px;
  height: 45px;
  font-size: 16px;
  transition: 0.3s;
  background-color: #ff6363;
  color: white;
  z-index: 1000;
  border: none;
  cursor: pointer;
}
.body {
  margin: 0;
  padding: 0;
  position: absolute;
  z-index: 0;
}
</style>
