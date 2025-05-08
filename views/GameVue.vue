<template>
  <div class="body">
    <h1>오목 Game</h1>
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
          @click="placeStone(i - 1, j - 1)"
        ></div>
      </template>
    </div>
  </div>
</template>

<script>
// import axios from 'axios';
import SockJs from 'sockjs-client';
import { Client } from '@stomp/stompjs';

export default {
  data() {
    return {
      stones: Array(15)
        .fill()
        .map(() => Array(15).fill(null)),
      client: null,
      turn: 'black',
      sessionId: '',
    };
  },
  mounted() {
    const socket = new SockJs('http://localhost:8080/ws');
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

      this.client.subscribe(`/room/api/subscribe`, (message) => {
        const body = JSON.parse(message.body);
        this.sessionId = body.sessionId;
        localStorage.setItem('sessionId', this.sessionId);
      });
    },
    placeStone(x, y) {
      if (this.stones[x][y]) return;
      this.stones[x][y] = this.turn;
      this.arrowCheck(x, y);
      const payload = {
        x,
        y,
      };

      this.client.publish({
        destination: '/app/api/coordinate',
        body: JSON.stringify(payload),
        headers: { 'content-type': 'application/json' },
      });

      this.turn = this.turn === 'black' ? 'white' : 'black';
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
