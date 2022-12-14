import { useState, useEffect } from 'react';
import './MenuStyles.css';
import RentRoomScheme from '../RentRoomScheme/RentRoomScheme';
import AdForm from '../AdForm/AdForm';
import axios from '../../api/axios';

export let hideSearchBar = false;

function Menu({ menuOpen }: any) {
  const [openModal, setOpenModal] = useState(false);
  const [openModal2, setOpenModal2] = useState(false);
  const [url, setUrl] = useState('#ads');
  const [authenticated, setAuthenticated] = useState<boolean>();

  useEffect(() => {
    localStorage.getItem('accessToken') != null
      ? setAuthenticated(true)
      : setAuthenticated(false);

    axios.interceptors.response.use(
      (response) => {
        return response;
      },
      (error) => {
        if (error.response.status == 401) {
          localStorage.removeItem('accessToken');
          localStorage.removeItem('tokenType');
          localStorage.removeItem('username');
          window.location.href = '/login';
        }
      }
    );
  });

  useEffect(() => {
    if (openModal || openModal2) {
      hideSearchBar = true;
    } else {
      hideSearchBar = false;
    }
  });

  return (
    <>
      <ul className={`menu ${menuOpen && 'menu-open'}`}>
        <a
          key="rent-scheme"
          id={menuOpen ? '' : 'rentRoomScheme'}
          href="#ads"
          onClick={() => setOpenModal(true)}
        >
          Rent-a-Room Scheme
        </a>
        <li key="post-add">
          <a
            href={url}
            onClick={() => {
              if (localStorage.getItem('accessToken') != null) {
                setOpenModal2(true);
              } else {
                setUrl('/login');
              }
            }}
          >
            Post a Room
          </a>
        </li>
        <li key="find" className="hidden">
          <a href="#ads">Find my Room</a>
        </li>
        <li key="login" className={authenticated ? 'hidden' : ''}>
          <a href="/login">Sign In</a>
        </li>
        <li className={authenticated ? 'hidden' : ''}>
          <button className="sign-up">
            <a href="/register">Sign Up</a>
          </button>
        </li>
        <li className={authenticated ? '' : 'hidden'}>
          <button
            className="sign-up"
            onClick={() => localStorage.removeItem('accessToken')}
          >
            <a href="/">Log out</a>
          </button>
        </li>
      </ul>
      <RentRoomScheme open={openModal} onClose={() => setOpenModal(false)} />
      <AdForm open={openModal2} onClose={() => setOpenModal2(false)} />
    </>
  );
}

export default Menu;
