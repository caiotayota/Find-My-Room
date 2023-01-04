import room01 from '../assets/img/rooms/room01_small.webp';
import room02 from '../assets/img/rooms/room02_small.webp';
import room03 from '../assets/img/rooms/room03_small.webp';
import room04 from '../assets/img/rooms/room04_small.webp';
import room05 from '../assets/img/rooms/room05_small.webp';
import room06 from '../assets/img/rooms/room06_small.webp';
import room07 from '../assets/img/rooms/room07_small.webp';
import room08 from '../assets/img/rooms/room08_small.webp';
import room09 from '../assets/img/rooms/room09_small.webp';
import room10 from '../assets/img/rooms/room10_small.webp';
import room11 from '../assets/img/rooms/room11_small.webp';
import room12 from '../assets/img/rooms/room12_small.webp';

const rooms = [
  room01,
  room02,
  room03,
  room04,
  room05,
  room06,
  room07,
  room08,
  room09,
  room10,
  room11,
  room12,
];

function getRandomRoom() {
  let min = Math.ceil(0);
  let max = Math.floor(11);
  let randomIndex = Math.floor(Math.random() * (max - min + 1) + min);
  console.log(randomIndex);
  return rooms[randomIndex];
}

export default getRandomRoom;