import './MenuStyles.css';

function Menu({ menuOpen }: any) {
  const linkNames = [
    'Rent-a-Room Scheme',
    'Post a Room',
    'Find My Room',
    'Sign-in',
  ];

  return (
    <ul className={`menu ${menuOpen && 'menu-open'}`}>
      {linkNames.map((link, i) => (
        <li key={i}>
          <a href="#">{link}</a>
        </li>
      ))}
      <li>
        <button className="sign-up">Sign-up</button>
      </li>
    </ul>
  );
}

export default Menu;
