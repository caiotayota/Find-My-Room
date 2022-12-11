import { useEffect, useState } from 'react';
import Menu from '../Menu/Menu';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';

import './NavbarStyles.css';

function Navbar() {
  const [menuOpen, setMenuOpen] = useState(false);
  const [nav, setNav] = useState(false);
  const [logo, setLogo] = useState(false);

  useEffect(() => {
    function activateNav() {
      let scrollPosition = window.pageYOffset;
      if (scrollPosition > 200) {
        setNav(true);
      } else if (scrollPosition < 10) {
        setNav(false);
      }
    }
    window.addEventListener('scroll', activateNav);
  });

  useEffect(() => {
    function activateLogo() {
      let scrollPosition = window.pageYOffset;
      if (scrollPosition > 200) {
        setLogo(true);
      } else if (scrollPosition < 10) {
        setLogo(false);
      }
    }
    window.addEventListener('scroll', activateLogo);
  });

  const menuToggle = () => {
    setMenuOpen(!menuOpen);
  };

  return (
    <>
      <div className={`navbar ${nav && 'navbar-active'}`}>
        <div>
          <a href="#">
            <img className={`logo ${logo && 'logo-active'}`} height="65" />
          </a>
          <Menu menuOpen={menuOpen} />
          <div className="menu-btn">
            <FontAwesomeIcon icon={faBars} onClick={menuToggle} />
          </div>
        </div>
      </div>
    </>
  );
}

export default Navbar;
