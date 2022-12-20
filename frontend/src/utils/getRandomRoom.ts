import room01 from '../assets/img/rooms/room01.jpg';
import room02 from '../assets/img/rooms/room02.jpg';
import room03 from '../assets/img/rooms/room03.jpg';
import room04 from '../assets/img/rooms/room04.jpg';
import room05 from '../assets/img/rooms/room05.jpeg';
import room06 from '../assets/img/rooms/room06.jpeg';
import room07 from '../assets/img/rooms/room07.jpeg';
import room08 from '../assets/img/rooms/room08.jpeg';
import room09 from '../assets/img/rooms/room09.jpeg';
import room10 from '../assets/img/rooms/room10.jpg';
import room11 from '../assets/img/rooms/room11.jpg';
import room12 from '../assets/img/rooms/room12.jpg';

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