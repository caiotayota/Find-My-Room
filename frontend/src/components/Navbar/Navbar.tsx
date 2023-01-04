import { useEffect, useState } from 'react';
import Menu from '../Menu/Menu';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars, faXmark } from '@fortawesome/free-solid-svg-icons';

import './NavbarStyles.css';

function Navbar() {
  const [menuOpen, setMenuOpen] = useState(false);
  const [nav, setNav] = useState(false);

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
    function closeMenu() {
      let scrollPosition = window.pageYOffset;
      if (scrollPosition > 200) {
        setMenuOpen(false);
      }
    }
    window.addEventListener('scroll', closeMenu);
  });

  const menuToggle = () => {
    setMenuOpen(!menuOpen);
  };

  return (
    <>
      <div className={`navbar ${nav && 'navbar-active'}`}>
        <div>
          <a href="#" aria-label="Homepage">
            <img className="logo" height="65" alt="Logo" />
          </a>
          <Menu menuOpen={menuOpen} />
          <div className="menu-btn">
            <FontAwesomeIcon
              icon={menuOpen ? faXmark : faBars}
              onClick={menuToggle}
            />
          </div>
        </div>
      </div>
    </>
  );
}

export default Navbar;
