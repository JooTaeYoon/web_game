<template>
  <div class="body">
    <h1>오목 Game</h1>
    <p>나의 돌 색은 : {{ this.color }}</p>
    <p>현재 : {{ this.turn }}</p>
    <div class="board">
      <div
        class="vline"
        v-for="i in 15"
        :key="'v' + i"
        :style="{
          gridColumn: i,
          gridRow: '1 / 16',
        }"
      ></div>
      <div
        class="hline"
        v-for="i in 15"
        :key="'h' + i"
        :style="{
          gridRow: i,
          gridColumn: '1 /  16',
        }"
      ></div>

      <template v-for="i in 15" :key="'row-' + i">
        <div
          v-for="j in 15"
          :key="'col-' + j"
          :class="[
            'stone',
            stones[i - 1][j - 1],
            stones[i - 1][j - 1] ? 'placed' : '',
          ]"
          :style="{
            gridRow: i,
            gridColumn: j,
            zIndex: 1,
          }"
          @click="handleClick(i - 1, j - 1)"
        ></div>
      </template>
    </div>
    <WinnerModalVue
      @close="showModal = false"
      v-show="showModal"
      :win="winner"
    ></WinnerModalVue>
  </div>
</template>

<script>
// import axios from 'axios';
import SockJs from 'sockjs-client';
import { Client } from '@stomp/stompjs';

import WinnerModalVue from './WinnerModal.vue';

export default {
  components: { WinnerModalVue },

  data() {
    return {
      stones: Array(15)
        .fill()
        .map(() => Array(15).fill(null)),
      client: null,
      turn: 'black',
      sessionId: '',
      color: '',
      showModal: false,
      winner: '',
    };
  },
  mounted() {
    const backendURL = process.env.VUE_APP_BACKEND_URL;
    const socket = new SockJs(`${backendURL}`);
    this.client = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 1000,
      onConnect: () => {
        console.log('connected success');
        this.init();
      },
      onStompError: (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
      },
    });
    this.client.activate();
  },
  methods: {
    init() {
      this.client.publish({
        destination: '/app/api/init',
        headers: { 'content-type': 'application/json' },
      });

      this.client.subscribe(`/user/room/api/init`, (message) => {
        const body = JSON.parse(message.body);
        this.sessionId = body.sessionId;
        this.color = body.color;
        this.turn = body.turn;
        console.log(body);
        localStorage.setItem('sessionId', this.sessionId);
      });

      this.client.subscribe('/room/api/gaming', (message) => {
        const body = JSON.parse(message.body);
        this.turn = body.turn;
        this.placeStone(body.x, body.y, body);
      });
    },

    handleClick(x, y) {
      if (this.stones[x][y] || this.color !== this.turn) return;

      const payload = {
        x,
        y,
        sessionId: this.sessionId,
        color: this.color,
        turn: this.turn,
      };

      this.stones[x][y] = this.color;
      this.arrowCheck(x, y);

      this.client.publish({
        destination: '/app/api/coordinate',
        body: JSON.stringify(payload),
        headers: { 'content-type': 'application/json' },
      });
    },
    placeStone(x, y, body) {
      const $board = document.querySelector('.board');
      if (this.stones[x][y]) return;
      if (this.color !== body.turn) {
        $board.style.pointerEvents = 'none';
      } else {
        $board.style.pointerEvents = 'auto';
      }
      this.stones[x][y] = body.color;
      this.arrowCheck(x, y);
    },
    isBoard(x, y) {
      return x < 0 || x >= 15 || y < 0 || y >= 15;
    },
    winCheck(x, y, i, j) {
      let count = 0;
      let dx = x + i;
      let dy = y + j;

      while (
        !this.isBoard(dx, dy) &&
        this.stones[dx][dy] === this.stones[x][y]
      ) {
        count++;
        dx += i;
        dy += j;
      }
      return count;
    },
    arrowCheck(x, y) {
      const direction = [
        [1, 0],
        [0, -1],
        [1, 1],
        [-1, 1],
      ];
      let count = 0;
      for (const [i, j] of direction) {
        count = 1 + this.winCheck(x, y, i, j) + this.winCheck(x, y, -i, -j);
        if (count >= 5) {
          this.winner = this.turn;
          this.showModal = true;
          console.log('win');
        }
      }
    },
  },
};
</script>

<style>
:root {
  --board-color: #deb887;
  --box-shadow: 0 2px 5px rgba(0, 0, 0, 0.5);
  --size: 480px;
}
</style>

<style scoped>
.body {
  font-family: monospace;
  font-size: 30px;
  font-weight: bold;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.board {
  position: relative;
  margin-top: 20px;
  display: grid;
  grid-template-columns: repeat(15, 1fr);
  grid-template-rows: repeat(15, 1fr);
  width: 1000px;
  height: 1000px;

  background-color: var(--board-color);
  justify-items: center;
  align-items: center;
  padding: 10px;
}
.vline,
.hline {
  background-color: black;
}
.vline {
  width: 2px;
  height: 100%;
}

.hline {
  width: 100%;
  height: 2px;
}
.stone {
  width: 55px;
  height: 55px;
  border-radius: 50%;
  grid-row: 4;
  grid-column: 4;
  background-color: lightgray;
  opacity: 0;
}
.stone:hover {
  opacity: 0.3;
}
.black {
  background-color: black;
  opacity: 1;
}
.white {
  opacity: 1;
  background-color: white;
}
</style>
