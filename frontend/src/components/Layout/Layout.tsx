import { Outlet } from 'react-router-dom';
import { Helmet } from 'react-helmet';

const Layout = () => {
  return (
    <>
      <Helmet>
        <meta charSet="utf-8" />
        <title>Find My Room</title>
        <meta
          name="keywords"
          content="Rent in Dublin, Room in Dublin, Room, Rent, Find My Room, Daft, Rooms to Rent"
        />
        <meta
          name="description"
          content="Find or advertise a room to rent in Dublin"
        />
      </Helmet>
      <main className="App">
        <Outlet />
      </main>
    </>
  );
};

export default Layout;
