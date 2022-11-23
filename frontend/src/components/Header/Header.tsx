import cover from '../../assets/img/cover14.jpg';

import './HeaderStyles.css';
import Navbar from '../Navbar/Navbar';
import SearchFilter from '../SearchFilter/SearchFilter';

function Header() {
  return (
    <>
      <Navbar />
      <SearchFilter />
      <div
        className="moving-clouds"
        style={{ backgroundImage: `url(${cover})` }}
      ></div>
    </>
  );
}

export default Header;
