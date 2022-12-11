import Header from '../components/Header/Header';
import SearchRooms from '../components/SearchRooms/SearchRooms';

function Home() {
  return (
    <>
      <header>
        <Header />
      </header>
      <main>
        <SearchRooms />
      </main>
    </>
  );
}

export default Home;
