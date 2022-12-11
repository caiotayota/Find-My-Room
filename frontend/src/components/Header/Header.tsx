import cover from '../../assets/img/cover-home.jpg';

import './HeaderStyles.css';
import Navbar from '../Navbar/Navbar';

function Header() {
  return (
    <>
      <Navbar />
      <div
        className="moving-clouds"
        style={{ backgroundImage: `url(${cover})` }}
      ></div>
    </>
  );
}

export default Header;
